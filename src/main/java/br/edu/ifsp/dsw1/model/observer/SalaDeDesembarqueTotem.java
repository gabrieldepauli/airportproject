package br.edu.ifsp.dsw1.model.observer;

import br.edu.ifsp.dsw1.model.flightstates.Arriving;
import br.edu.ifsp.dsw1.model.flightstates.State;

public class SalaDeDesembarqueTotem extends Totem {

    // Implementação do filtro para voos no estado Arriving (chegando)
    @Override
    protected boolean isStateDesired(State state) {
        return state instanceof Arriving; // Verifica se o estado do voo é Arriving
    }
}
