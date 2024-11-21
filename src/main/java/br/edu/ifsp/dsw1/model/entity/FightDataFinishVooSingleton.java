package br.edu.ifsp.dsw1.model.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Singleton responsável por manter uma lista de voos que já terminaram.
 * A lista de voos "terminados" (por exemplo, voos que atingiram o estado TookOff) é compartilhada por todas as partes do código
 * que precisem acessar ou modificar essa informação. A implementação do Singleton garante que essa lista seja mantida de forma
 * centralizada e acessível de qualquer lugar do programa.
 */
public class FightDataFinishVooSingleton {
	// Lista que contém os voos que já foram finalizados (TookOff)
    private final List<FlightData> finishedFlights = new ArrayList<>();

    // Classe estática interna que cria a instância do Singleton
    private static class SingletonHelper {
    	// A instância do Singleton é criada apenas quando for getInstance() for chamado
        private static final FightDataFinishVooSingleton INSTANCE = new FightDataFinishVooSingleton();
    }

 // Construtor privado para impedir que a classe seja instanciada diretamente
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
