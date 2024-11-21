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

@WebServlet("/updateVoo.do") // Define o caminho da URL que será mapeado para este servlet
public class AtualizarVooController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Método GET para processar as requisições
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);  // Chama o método processRequest para lidar com a requisição
    }

    // Método POST para processar as requisições
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);  // Chama o método processRequest para lidar com a requisição
    }

    // Método principal para processar as requisições
    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");  // Recupera o parâmetro "action" da requisição
        String page = null;  // Variável para armazenar a página para redirecionamento

        // Se o parâmetro "action" não for fornecido, retorna erro
        if (action == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Parâmetro 'action' não fornecido.");
            return; // Interrompe a execução do código
        }
        
        // Switch que decide a ação com base no valor do parâmetro "action"
        switch (action) {
            case "showFlights":  // Exibe a lista de voos
                page = handleShowFlights(request);
                break;

            case "updateFlightState":  // Atualiza o estado de um voo
                page = handleUpdateFlightState(request);
                break;

            default: // Se a ação não for reconhecida, retorna erro 404
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Ação desconhecida: " + action);
        }

        // Se uma página foi determinada, redireciona para ela
        if (page != null) {
            request.getRequestDispatcher(page).forward(request, response);
        }

    }

    // Método para exibir a lista de voos
    private String handleShowFlights(HttpServletRequest request) {
        // Obtém a coleção de voos do Singleton
        FlightDataCollection flightCollection = FlightDataSingleton.getInstance();
        List<FlightData> flights = flightCollection.getAllFligthts();  // Recupera todos os voos

        // Define a lista de voos como um atributo da requisição
        request.setAttribute("flights", flights);
 
        // Retorna o nome da página JSP que exibirá os voos
        return "atualizarEstado.jsp";
    }

    // Método para atualizar o estado de um voo
    private String handleUpdateFlightState(HttpServletRequest request) {
        // Obtém a coleção de voos do Singleton
        FlightDataCollection collection = FlightDataSingleton.getInstance();
        Long number = Long.parseLong(request.getParameter("numeroVoo"));  // Recupera o número do voo do parâmetro da requisição
        
        // Procura o voo na coleção pelo número do voo
        FlightData flight = collection.getAllFligthts().stream()
            .filter(f -> f.getFlightNumber().equals(number)) // Filtra o voo que tem o número correspondente
            .findFirst()  // Pega o primeiro (e único) voo encontrado
            .orElse(null); // Caso não encontre, retorna null

        // Se o voo for encontrado, atualiza o estado do voo
        if (flight != null) {
            collection.updateFlight(flight.getFlightNumber());  // Atualiza o estado do voo no sistema
        }

        // Recarrega a lista de voos atualizada
        List<FlightData> updatedFlights = collection.getAllFligthts();
        request.setAttribute("flights", updatedFlights);  // Define a lista de voos atualizada como atributo da requisição

        // Retorna o nome da página JSP para exibir a lista de voos atualizada
        return "atualizarEstado.jsp";  
    }

}
