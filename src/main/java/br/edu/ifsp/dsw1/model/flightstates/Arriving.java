package br.edu.ifsp.dsw1.model.flightstates;

import br.edu.ifsp.dsw1.model.entity.FlightData;

public class Arriving implements State {
	private static Arriving instance = null;
	
	private Arriving() { }
	
	public static Arriving getInstance() {
		if (instance == null) {
			instance = new Arriving();
		}
		return instance;
	}

	@Override
	public void change(FlightData flight) {
		flight.setState(Boarding.getInstance());
	}

}
