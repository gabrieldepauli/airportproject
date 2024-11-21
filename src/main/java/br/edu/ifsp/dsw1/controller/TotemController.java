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

@WebServlet("/totem.do")
public class TotemController extends HttpServlet{
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String action = request.getParameter("action");
        String page = null;
        
        if(action.equals("getSalaDesembarque")) {
        	page = handleSalaDesembarque(request, response);
        }else if(action.equals("getSalaEmbarque")) {
        	page = handleSalaEmbarque(request, response);
        }else if(action.equals("getHall1")) {
        	page = handleHall1(request, response);
        }else if(action.equals("getHall2")) {
        	page = handleHall2(request, response);
        }
        
        RequestDispatcher dispatcher = request.getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }

    protected String handleSalaDesembarque(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Criando o totem de desembarque
        SalaDeDesembarqueTotem salaDeDesembarqueTotem = new SalaDeDesembarqueTotem();

        // Adicionando os voos no request
        request.setAttribute("flightsArriving", salaDeDesembarqueTotem.getFlights());

        return "salaDeDesembarque.jsp";
    }

    protected String handleSalaEmbarque(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Criando o totem de embarque
        SalaDeEmbarqueTotem salaDeEmbarqueTotem = new SalaDeEmbarqueTotem();

        // Adicionando os voos no request
        request.setAttribute("flightsBoarding", salaDeEmbarqueTotem.getFlights());

        return "salaDeEmbarque.jsp";
    }

    protected String handleHall1(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Criando o totem para Hall 1
        Hall1Totem hall1Totem = new Hall1Totem();

        // Adicionando os voos no request
        request.setAttribute("flightsTakingOff", hall1Totem.getFlights());

        return "hall1.jsp";
    }

    protected String handleHall2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Criando o totem para Hall 2
        Hall2Totem hall2Totem = new Hall2Totem();

        // Adicionando os voos no request
        request.setAttribute("flightsTookOff", hall2Totem.getFlights());

        return "hall2.jsp";
    }
}