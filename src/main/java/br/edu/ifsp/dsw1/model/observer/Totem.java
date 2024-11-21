package br.edu.ifsp.dsw1.model.observer;

import br.edu.ifsp.dsw1.model.entity.FlightData;
import br.edu.ifsp.dsw1.model.entity.FlightDataCollection;
import br.edu.ifsp.dsw1.model.entity.FlightDataSingleton;
import java.util.List;

public abstract class Totem implements FlightDataObserver {

	private final FlightDataCollection flightData = FlightDataSingleton.getInstance();

    // Método para retornar todos os voos armazenados
    public List<FlightData> getFlights() {
        return flightData.getAllFligthts();
    }
    
}
