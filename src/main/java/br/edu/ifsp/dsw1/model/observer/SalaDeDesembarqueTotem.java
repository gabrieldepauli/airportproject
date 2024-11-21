package br.edu.ifsp.dsw1.model.observer;

import java.util.List;
import java.util.stream.Collectors;

import br.edu.ifsp.dsw1.model.entity.FlightData;
import br.edu.ifsp.dsw1.model.entity.FlightDataCollection;
import br.edu.ifsp.dsw1.model.entity.FlightDataSingleton;
import br.edu.ifsp.dsw1.model.flightstates.Arriving;

public class SalaDeDesembarqueTotem extends Totem {

	private FlightDataCollection flightData = FlightDataSingleton.getInstance();
	
	private boolean registered = false;
	
	// Sobrescreve o método getFlights() para filtrar voos no estado Arriving
    @Override
    public List<FlightData> getFlights() {
    	if (!registered) { // Verifica se este totem ja esta registrado como um observer
    		flightData.register(this); // Se não estiver registrado, ele registra como observer
    		registered = true; 
        }
    	
    	// Pega a lista de voos no estado Arriving
    	List<FlightData> listaVoos = super.getFlights().stream()
                .filter(flight -> flight.getState() instanceof Arriving)
                .collect(Collectors.toList());
    	
    	if (listaVoos.isEmpty() && registered) { //Verifica se ja tiver registrado e puder ser unregister
            flightData.unregister(this); // Exclui o registro deste totem como observer
            registered = false;
        }
    	
    	return listaVoos;
    }
    
    // Atualiza o totem sempre que há uma mudança em algum voo
    @Override
    public void update(FlightData flight) {
        if (flight.getState() instanceof Arriving) {
            System.out.println("Voo atualizado: " + flight.getFlightNumber() + 
                               " para o estado: " + flight.getState().getClass().getSimpleName());
        }
    }
}
