package br.edu.ifsp.dsw1.controller;

import br.edu.ifsp.dsw1.model.entity.FlightData;
import br.edu.ifsp.dsw1.model.entity.FlightDataCollection;
import br.edu.ifsp.dsw1.model.flightstates.Arriving;

import java.io.IOException;
import java.net.URLEncoder;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/cadastraVoo")
public class CadastroVooController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final FlightDataCollection COLLECTION = new FlightDataCollection();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    	try {
            Long number = parseLongParameter(request, "number");
            String company = getStringParameter(request, "company");
            String time = getStringParameter(request, "time");

            FlightData flight = new FlightData(number, company, time);
            flight.setState(Arriving.getIntance());

            COLLECTION.insertFlight(flight);

            response.sendRedirect("admin.jsp");
        } catch (IllegalArgumentException e) {
        	response.sendRedirect("cadastrarVoo.jsp?error=true&message=" + URLEncoder.encode("Dados inválidos: " + e.getMessage(), "UTF-8"));
        }
    	
    }
    
    // Método criado para validar o parâmetro numérico (Number do Voo)
    private Long parseLongParameter(HttpServletRequest request, String paramName) {
        String param = request.getParameter(paramName);
        
        try {
            return Long.parseLong(param);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("O parâmetro '" + paramName + "' deve ser um número válido.");
        }
        
    }

    private String getStringParameter(HttpServletRequest request, String paramName) {
        return request.getParameter(paramName).trim();
    }

}
