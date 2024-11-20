package br.edu.ifsp.dsw1.model.observer;

import br.edu.ifsp.dsw1.model.entity.FlightData;
import br.edu.ifsp.dsw1.model.entity.FlightDataCollection;
import br.edu.ifsp.dsw1.model.entity.FlightDataSingleton;
import br.edu.ifsp.dsw1.model.flightstates.State;

import java.util.List;
import java.util.stream.Collectors;

public abstract class Totem implements FlightDataObserver {

    // Método que filtra os voos com base no estado atual de cada voo.
    @Override
    public void update(FlightData flightData) {
        FlightDataCollection flightCollection = FlightDataSingleton.getInstance();
        
        // Filtra os voos diretamente com base no estado
        List<FlightData> filteredFlights = flightCollection.getAllFligthts().stream()
            .filter(f -> isStateDesired(f.getState()))
            .collect(Collectors.toList());

        System.out.println(getClass().getSimpleName() + " recebeu voos atualizados: " + filteredFlights);
    }

    // Método que retorna a lista de voos filtrados
    public List<FlightData> getFilteredFlights() {
        FlightDataCollection flightCollection = FlightDataSingleton.getInstance();
        
        return flightCollection.getAllFligthts().stream()
            .filter(f -> isStateDesired(f.getState()))
            .collect(Collectors.toList());
    }

    // Método abstrato que será implementado pelas subclasses para verificar o estado desejado
    protected abstract boolean isStateDesired(State state);
    
}
