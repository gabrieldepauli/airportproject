package br.edu.ifsp.dsw1.model.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FightDataFinishVooSingleton {

    private final List<FlightData> finishedFlights = new ArrayList<>();

    // Classe estática interna que cria a instância do Singleton
    private static class SingletonHelper {
        private static final FightDataFinishVooSingleton INSTANCE = new FightDataFinishVooSingleton();
    }

    // Contrutor vazio privado
    private FightDataFinishVooSingleton() {}

    // Método que retorna a instância única da classe
    public static FightDataFinishVooSingleton getInstance() {
        return SingletonHelper.INSTANCE;
    }

    // Adiciona os voos no que estão no estado tookOff
    public void addFinishedFlight(FlightData flight) {
        if (!finishedFlights.contains(flight)) {
            finishedFlights.add(flight);
        }
    }

    // Retorna uma lista imutável de voos que terminaram
    public List<FlightData> getAllFinishedFlights() {
        return Collections.unmodifiableList(finishedFlights);
    }
}
