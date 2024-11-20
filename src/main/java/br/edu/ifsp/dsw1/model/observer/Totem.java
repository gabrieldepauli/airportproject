package br.edu.ifsp.dsw1.model.observer;

import br.edu.ifsp.dsw1.model.entity.FlightData;
import br.edu.ifsp.dsw1.model.entity.FlightDataCollection;
import br.edu.ifsp.dsw1.model.entity.FlightDataSingleton;
import br.edu.ifsp.dsw1.model.flightstates.State;

import java.util.List;
import java.util.stream.Collectors;

public abstract class Totem implements FlightDataObserver {

    // O método update agora não depende de um voo específico, pois o totem deve se atualizar com todos os voos
    @Override
    public void update(FlightData flightData) {
        FlightDataCollection flightCollection = FlightDataSingleton.getInstance();
        
        // Filtra todos os voos baseados no estado desejado
        List<FlightData> filteredFlights = flightCollection.getAllFligthts().stream()
            .filter(f -> isStateDesired(f.getState())) // Chama o método que define o estado desejado
            .collect(Collectors.toList());

        // Exibe os voos filtrados
        System.out.println(getClass().getSimpleName() + " recebeu voos atualizados: " + filteredFlights);
    }

    // Retorna a lista de voos filtrados com base no estado desejado
    public List<FlightData> getFilteredFlights() {
        FlightDataCollection flightCollection = FlightDataSingleton.getInstance();
        
        return flightCollection.getAllFligthts().stream()
            .filter(f -> isStateDesired(f.getState())) // Chama o método que define o estado desejado
            .collect(Collectors.toList());
    }

    // Método abstrato que será implementado nas subclasses para verificar o estado desejado
    protected abstract boolean isStateDesired(State state);
}
