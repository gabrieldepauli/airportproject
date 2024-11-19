package br.edu.ifsp.dsw1.controller;

import br.edu.ifsp.dsw1.model.entity.FlightData;
import br.edu.ifsp.dsw1.model.entity.FlightDataCollection;
import br.edu.ifsp.dsw1.model.entity.FlightDataSingleton;
import br.edu.ifsp.dsw1.model.flightstates.Arriving;

import java.io.IOException;
import java.net.URLEncoder;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/cadastrarVoo.do")
public class CadastroVooController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final String ADMIN_PAGE = "admin.jsp";
    private static final String CADASTRO_VOO_PAGE = "cadastrarVoo.jsp";

    private static final FlightDataCollection COLLECTION = FlightDataSingleton.getInstance();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(CADASTRO_VOO_PAGE).forward(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Ação não especificada");
            return;
        }

        switch (action) {
            case "cadastrarVoo":
                cadastrarVoo(request, response);
                break;

            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Ação desconhecida: " + action);
        }
    }

    private void cadastrarVoo(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        
    	try {
            Long number = parseLongParameter(request, "number");
            
            boolean flightExists = COLLECTION.getAllFligthts().stream()
                    .anyMatch(f -> f.getFlightNumber().equals(number));
            
            if (flightExists) {
                request.setAttribute("errorMessageCadastro", "Já existe um voo com este número.");
                request.getRequestDispatcher(CADASTRO_VOO_PAGE).forward(request, response);
            } else {
                String company = request.getParameter("company").trim();
                String time = request.getParameter("time").trim();

                FlightData flight = new FlightData(number, company, time);
                flight.setState(Arriving.getInstance());

                COLLECTION.insertFlight(flight);

                response.sendRedirect(ADMIN_PAGE);
            }
            
        } catch (NumberFormatException e) {
            String errorMessage = "Dados inválidos: O número do voo deve ser um número válido.";

            response.sendRedirect(CADASTRO_VOO_PAGE + "?error=true&message=" + URLEncoder.encode(errorMessage, "UTF-8"));
        } catch (IllegalArgumentException e) {
            String errorMessage = "Dados inválidos: " + e.getMessage();

            response.sendRedirect(CADASTRO_VOO_PAGE + "?error=true&message=" + URLEncoder.encode(errorMessage, "UTF-8"));
        }
    	
    }


    // Método para garantir que o parâmetro numérico é válido
    private Long parseLongParameter(HttpServletRequest request, String paramName) {
        String param = request.getParameter(paramName);

        try {
            return Long.parseLong(param);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("O parâmetro '" + paramName + "' deve ser um número válido.");
        }
    }
}
