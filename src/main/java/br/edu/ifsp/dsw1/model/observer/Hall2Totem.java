package br.edu.ifsp.dsw1.model.observer;

import br.edu.ifsp.dsw1.model.flightstates.TookOff;
import br.edu.ifsp.dsw1.model.flightstates.State;

public class Hall2Totem extends Totem {

    // Implementação do filtro para voos no estado TookOff (decolado)
    @Override
    protected boolean isStateDesired(State state) {
        return state instanceof TookOff; // Verifica se o estado do voo é TookOff
    }
}
