package br.edu.ifsp.dsw1.model.observer;

import br.edu.ifsp.dsw1.model.flightstates.TookOff;
import java.util.List;
import br.edu.ifsp.dsw1.model.entity.FlightData;
import br.edu.ifsp.dsw1.model.entity.FlightDataCollection;
import br.edu.ifsp.dsw1.model.entity.FlightDataSingleton;
import br.edu.ifsp.dsw1.model.entity.FightDataFinishVooSingleton;

public class Hall2Totem extends Totem {
    
	private FlightDataCollection flightData = FlightDataSingleton.getInstance();
    private FightDataFinishVooSingleton finishedFlights = FightDataFinishVooSingleton.getInstance();
	
	private boolean registered = false;
	private boolean excluded = false;
    
	// Sobrescreve o método getFlights() para filtrar voos no estado TookOff
    @Override
    public List<FlightData> getFlights() {
    	if (registered == false) { // // Faz a verificação se o totem já está registrado
    		flightData.register(this); // Se não estiver registrado, ele registra o totem como observer
    		
    		registered = true; // O boolean de registrado agora passa a ser TRUE
        }
    	
    	// Cria uma lista de voos com todos os voos que foram escluídos
    	List<FlightData> listaVoos = finishedFlights.getAllFinishedFlights();
    	
    	// Chama a função que exclui o Observer
    	unregisterObserver();
    	
    	// Retorna a lista de Voos
    	return listaVoos;
    }
    
	// Este método reage às atualizações dos voos (Observer - update)
    @Override
    public void update(FlightData flight) {
    	if (flight.getState() instanceof TookOff) {
    		// Mensagem que será exibida no console quando o Voo for atualizado
            System.out.println("Voo de número: " + flight.getFlightNumber() + 
                               " atualizado para o estado: " + flight.getState().getClass().getSimpleName());
            
         // Quando o voo for atualizado para o TookOf ele chama a função do FightDataFinishVooSingleton para adiciona a lista de voos excluídos
            finishedFlights.addFinishedFlight(flight); 
            excluded = true; // Modifica o atributo para true, declarando que agora o código pode ser excluído
        }
    }
    
    // Este método é responsável por excluir o Observer
    private void unregisterObserver() {
        if (excluded == true && registered == true) {
            flightData.unregister(this); // Exclui o registro deste totem como observer
            registered = false; // O boolean de registrado agora passa a ser FALSE
            excluded = false; // O boolean de excluido agora passa a ser FALSE
        }
    }
    
}
