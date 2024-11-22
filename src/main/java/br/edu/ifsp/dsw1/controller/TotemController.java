package br.edu.ifsp.dsw1.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import br.edu.ifsp.dsw1.model.observer.SalaDeEmbarqueTotem;
import br.edu.ifsp.dsw1.model.observer.SalaDeDesembarqueTotem;
import br.edu.ifsp.dsw1.model.observer.Hall1Totem;
import br.edu.ifsp.dsw1.model.observer.Hall2Totem;

// Anotação para mapear a URL "/totem.do" para este servlet
@WebServlet("/totem.do")
public class TotemController extends HttpServlet{
    private static final long serialVersionUID = 1L;

    // Método para processar requisições GET
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response); // Chama o método para processar a requisição
    }

    // Método para processar requisições POST
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response); // Chama o método para processar a requisição
    }

    // Método comum para processar tanto GET quanto POST
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        // Obtém o parâmetro "action" da requisição para decidir qual ação tomar
        String action = request.getParameter("action");
        String page = null;
        
        // Se o parâmetro "action" não for fornecido, retorna erro
        if (action == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Parâmetro 'action' não fornecido.");
            return; // Interrompe a execução do código
        }
        
        switch(action) {
        	case "getSalaDesembarque":
        		page = handleSalaDesembarque(request, response); // Chama o método para exibir informações da Sala de Desembarque
        		break;
        	case "getSalaEmbarque":
        		page = handleSalaEmbarque(request, response); // Chama o método para exibir informações da Sala de Embarque
        		break;
        	case "getHall1":
        		page = handleHall1(request, response); // Chama o método para exibir informações do Hall 1
        		break;
        	case "getHall2":
        		page = handleHall2(request, response); // Chama o método para exibir informações do Hall 2
        		break;
        		
        	default:  // Se a ação não for reconhecida, retorna erro
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Ação desconhecida: " + action);
        }
        
        // Se uma página foi determinada, redireciona para ela
        if (page != null) {
            request.getRequestDispatcher(page).forward(request, response);
        }
        
    }

    // Método para lidar com a Sala de Desembarque
    protected String handleSalaDesembarque(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        // Cria uma instância de SalaDeDesembarqueTotem para obter as informações dos voos
        SalaDeDesembarqueTotem salaDeDesembarqueTotem = new SalaDeDesembarqueTotem();

        // Define a lista de voos que estão chegando como atributo da requisição
        request.setAttribute("flightsArriving", salaDeDesembarqueTotem.getFlights());

        // Retorna o nome da página que exibirá as informações da Sala de Desembarque
        return "salaDeDesembarque.jsp";
    }

    // Método para lidar com a Sala de Embarque
    protected String handleSalaEmbarque(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        // Cria uma instância de SalaDeEmbarqueTotem para obter as informações dos voos
        SalaDeEmbarqueTotem salaDeEmbarqueTotem = new SalaDeEmbarqueTotem();

        // Define a lista de voos que estão embarcando como atributo da requisição
        request.setAttribute("flightsBoarding", salaDeEmbarqueTotem.getFlights());

        // Retorna o nome da página que exibirá as informações da Sala de Embarque
        return "salaDeEmbarque.jsp";
    }

    // Método para lidar com o Hall 1
    protected String handleHall1(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        // Cria uma instância de Hall1Totem para obter as informações dos voos
        Hall1Totem hall1Totem = new Hall1Totem();

        // Define a lista de voos que estão saindo como atributo da requisição
        request.setAttribute("flightsTakingOff", hall1Totem.getFlights());

        // Retorna o nome da página que exibirá as informações do Hall 1
        return "hall1.jsp";
    }

    // Método para lidar com o Hall 2
    protected String handleHall2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        // Cria uma instância de Hall2Totem para obter as informações dos voos
        Hall2Totem hall2Totem = new Hall2Totem();

        // Define a lista de voos que já decolaram como atributo da requisição
        request.setAttribute("flightsTookOff", hall2Totem.getFlights());

        // Retorna o nome da página que exibirá as informações do Hall 2
        return "hall2.jsp";
    }
}

