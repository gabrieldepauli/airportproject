package br.edu.ifsp.dsw1.model.observer;

import java.util.List;
import java.util.stream.Collectors;
import br.edu.ifsp.dsw1.model.entity.FlightData;
import br.edu.ifsp.dsw1.model.entity.FlightDataCollection;
import br.edu.ifsp.dsw1.model.entity.FlightDataSingleton;
import br.edu.ifsp.dsw1.model.flightstates.Boarding;

public class SalaDeEmbarqueTotem extends Totem {

	private FlightDataCollection flightData = FlightDataSingleton.getInstance();
	
	private boolean registered = false;
	
	// Sobrescreve o método getFlights() para filtrar voos no estado Boarding
    @Override
    public List<FlightData> getFlights() {
    	if (registered == false) { // Faz a verificação se o totem já está registrado
    		flightData.register(this); // Se não estiver registrado, ele registra o totem como observer
    		registered = true; // O boolean de registrado agora passa a ser TRUE
        }
    	
    	// Cria uma lista de voos com todos os voos
    	List<FlightData> listaVoos = super.getFlights().stream()
                .filter(flight -> flight.getState() instanceof Boarding)
                .collect(Collectors.toList());
    	
    	// Chama a função que exclui o Observer
    	unregisterObserver(listaVoos);
    	
    	// Retorna a lista de Voos
    	return listaVoos;
    }
    
    // Este método reage às atualizações dos voos (Observer - update)
    @Override
    public void update(FlightData flight) {
        if (flight.getState() instanceof Boarding) {
        	// Mensagem que será exibida no console quando o Voo for atualizado
            System.out.println("Voo de número: " + flight.getFlightNumber() + 
                               " atualizado para o estado: " + flight.getState().getClass().getSimpleName());
        }
    }
    
    // Este método é responsável por excluir o Observer
    private void unregisterObserver(List<FlightData> listaVoos) {
        if (listaVoos.isEmpty() && registered == true) {
            flightData.unregister(this); // Exclui o registro deste totem como observer
            registered = false; // O boolean de registrado agora passa a ser FALSE
        }
    }
    
}
