package br.edu.ifsp.dsw1.model.observer;

import br.edu.ifsp.dsw1.model.flightstates.TakingOff;
import br.edu.ifsp.dsw1.model.flightstates.State;

public class Hall1Totem extends Totem {

    // Implementação do filtro para voos no estado TakingOff (decolando)
    @Override
    protected boolean isStateDesired(State state) {
        return state instanceof TakingOff; // Verifica se o estado do voo é TakingOff
    }
}
