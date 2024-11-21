package br.edu.ifsp.dsw1.model.observer;

import br.edu.ifsp.dsw1.model.flightstates.TakingOff;
import java.util.List;
import java.util.stream.Collectors;
import br.edu.ifsp.dsw1.model.entity.FlightData;
import br.edu.ifsp.dsw1.model.entity.FlightDataCollection;
import br.edu.ifsp.dsw1.model.entity.FlightDataSingleton;

public class Hall1Totem extends Totem {

	private FlightDataCollection flightData = FlightDataSingleton.getInstance();
	
	private boolean registered = false;
	
	// Sobrescreve o método getFlights() para filtrar voos no estado TakingOff
    @Override
    public List<FlightData> getFlights() {
    	if (!registered) { // verifica se este totem ja esta registrado como um observer
    		flightData.register(this); // registra como observer
    		registered = true;
        }
    	
    	List<FlightData> listaVoos = super.getFlights().stream()
                .filter(flight -> flight.getState() instanceof TakingOff)
                .collect(Collectors.toList());
    	
    	if (listaVoos.isEmpty() && registered) { //verifica se ja tiver registrado e puder ser unregister
            flightData.unregister(this); // Exclui o registro deste totem como observer
            
            registered = false;
        }
    	
    	return listaVoos;
    }
    
    // Atualiza o totem sempre que há uma mudança em algum voo
    @Override
    public void update(FlightData flight) {
        if (flight.getState() instanceof TakingOff) {
            System.out.println("Voo atualizado: " + flight.getFlightNumber() + 
                               " para o novo estado: " + flight.getState().getClass().getSimpleName());
        }
    }
    
}
