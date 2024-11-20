package br.edu.ifsp.dsw1.controller;

import br.edu.ifsp.dsw1.model.observer.Hall1Totem;
import br.edu.ifsp.dsw1.model.observer.Hall2Totem;
import br.edu.ifsp.dsw1.model.observer.SalaDeEmbarqueTotem;
import br.edu.ifsp.dsw1.model.observer.Totem;
import br.edu.ifsp.dsw1.model.observer.SalaDeDesembarqueTotem;
import br.edu.ifsp.dsw1.model.entity.FlightData;
import br.edu.ifsp.dsw1.model.entity.FlightDataSingleton;
import br.edu.ifsp.dsw1.model.entity.FlightDataCollection;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class TotemController extends HttpServlet {

    private FlightDataCollection flightCollection;

    public TotemController() {
        this.flightCollection = FlightDataSingleton.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String totemType = request.getParameter("totem");

        // Cria o totem baseado no tipo
        Totem totem = getTotemByType(totemType);

        if (totem != null) {
            flightCollection.register(totem);
            List<FlightData> filteredFlights = totem.getFilteredFlights();

            request.setAttribute("filteredFlights", filteredFlights);
            request.getRequestDispatcher("/displayFlights.jsp").forward(request, response); // Exemplo de redirecionamento para a JSP
        } else {
            response.getWriter().println("Totem não encontrado!");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String totemType = request.getParameter("totem");

        // Cria o totem baseado no tipo
        Totem totem = getTotemByType(totemType);

        if (totem != null) {
            flightCollection.register(totem);
            List<FlightData> filteredFlights = totem.getFilteredFlights();

            response.getWriter().println("Totem atualizado com os voos filtrados: " + filteredFlights);
        } else {
            response.getWriter().println("Totem não encontrado!");
        }
    }

    // Método que mapeia o tipo de totem para a classe correspondente
    private Totem getTotemByType(String totemType) {
        if (totemType == null) {
            return null;
        }

        switch (totemType.toLowerCase()) {
            case "hall1":
                return new Hall1Totem();
            case "hall2":
                return new Hall2Totem();
            case "salaembarque":
                return new SalaDeEmbarqueTotem();
            case "saladesembarque":
                return new SalaDeDesembarqueTotem();
            default:
                return null;
        }
    }
}
