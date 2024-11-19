package br.edu.ifsp.dsw1.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import br.edu.ifsp.dsw1.model.entity.FlightData;
import br.edu.ifsp.dsw1.model.entity.FlightDataCollection;
import br.edu.ifsp.dsw1.model.entity.FlightDataSingleton;

/*
 * FrontController (Servlet) criado para fazer atualização do estado do Voo
 * O método handleShowFlights obtém e mostra os voos que estão registrados
 * O método handleUpdateFlightState faz o update de Estado do voo
 */


@WebServlet("/updateVoo.do")
public class AtualizarVooController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        String page;
        switch (action) {
            case "showFlights":
                page = handleShowFlights(request);
                break;

            case "updateFlightState":
                page = handleUpdateFlightState(request);
                break;

            default:
                page = "admin.jsp";
                break;
        }

        request.getRequestDispatcher(page).forward(request, response);
    }

    private String handleShowFlights(HttpServletRequest request) {
        // Obtém a coleção com os voos
        FlightDataCollection flightCollection = FlightDataSingleton.getInstance();
        List<FlightData> flights = flightCollection.getAllFligthts();

        // Define a lista de voos como atributo
        request.setAttribute("flights", flights);

        return "atualizarEstado.jsp";
    }

    private String handleUpdateFlightState(HttpServletRequest request) {
        FlightDataCollection collection = FlightDataSingleton.getInstance();
        Long number = Long.parseLong(request.getParameter("numeroVoo"));
        
        // Procura o voo
        FlightData flight = collection.getAllFligthts().stream().filter(f -> f.getFlightNumber().equals(number)).findFirst().orElse(null);

        // Atualiza o estado do voo
        collection.updateFlight(flight.getFlightNumber());

        // Recarrega a lista de voos atualizada para a próxima página
        List<FlightData> updatedFlights = collection.getAllFligthts();
        request.setAttribute("flights", updatedFlights);

        return "atualizarEstado.jsp";  // Redireciona para a página de atualização com a lista de voos atualizada

    }

}
