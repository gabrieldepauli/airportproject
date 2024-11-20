package br.edu.ifsp.dsw1.model.observer;

import br.edu.ifsp.dsw1.model.flightstates.Boarding;
import br.edu.ifsp.dsw1.model.flightstates.State;

public class SalaDeEmbarqueTotem extends Totem {

    // Implementação do filtro para voos no estado Boarding (embarque)
    @Override
    protected boolean isStateDesired(State state) {
        return state instanceof Boarding; // Verifica se o estado do voo é Boarding
    }
}
