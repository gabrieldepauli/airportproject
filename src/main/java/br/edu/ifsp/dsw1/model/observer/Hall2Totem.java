package br.edu.ifsp.dsw1.model.observer;

import br.edu.ifsp.dsw1.model.flightstates.TookOff;
import java.util.List;
import br.edu.ifsp.dsw1.model.entity.FlightData;
import br.edu.ifsp.dsw1.model.entity.FlightDataCollection;
import br.edu.ifsp.dsw1.model.entity.FlightDataSingleton;
import br.edu.ifsp.dsw1.model.entity.FightDataFinishVooSingleton;

// Não está funcionando
public class Hall2Totem extends Totem {
    
	private boolean registered = false;
    private FlightDataCollection flightData = FlightDataSingleton.getInstance();
    private FightDataFinishVooSingleton finishedFlights = FightDataFinishVooSingleton.getInstance();
    
 // Sobrescreve o método getFlights() para filtrar voos no estado TookOff
    @Override
    public List<FlightData> getFlights() {
    	if (!registered) { // verifica se este totem ja esta registrado como um observer
    		flightData.register(this); // registra como observer
    		registered = true;
        }
    	
    	List<FlightData> listaVoos = finishedFlights.getAllFinishedFlights();
    	
    	if (listaVoos.isEmpty() && registered) { //verifica se ja tiver registrado e puder ser unregister
            flightData.unregister(this); // Exclui o registro deste totem como observer
            registered = false;
        }
    	
    	return listaVoos;
    }
    
	// Este método agora apenas reage às atualizações dos voos
    @Override
    public void update(FlightData flight) {
    	if (flight.getState() instanceof TookOff) {
            System.out.println("Voo atualizado: " + flight.getFlightNumber() + 
                               " para o estado: " + flight.getState().getClass().getSimpleName());
            
            finishedFlights.addFinishedFlight(flight);
        }
    }
    
}
