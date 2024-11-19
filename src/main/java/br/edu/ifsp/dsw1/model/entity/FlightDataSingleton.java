package br.edu.ifsp.dsw1.model.entity;

// O singleton foi criado para que seja criada uma única instância da coleção
public class FlightDataSingleton {

    private static final FlightDataCollection instance;

    // Bloco estático que inicializa a instância
    static {
        instance = new FlightDataCollection();
    }

    private FlightDataSingleton() {
    }

    // Método criado para ter acesso à instância
    public static FlightDataCollection getInstance() {
        return instance;
    }
}