package br.edu.ifsp.dsw1.model.entity;

/**
 * Singleton responsável por garantir que a classe FlightDataCollection tenha apenas uma única instância.
 * 
 * A principal intenção deste singleton é evitar a criação de múltiplas instâncias da coleção de voos, garantindo que todos
 * os componentes que necessitem acessar os dados da coleção compartilhem a mesma instância.
 */
public class FlightDataSingleton {

	// Instância única da coleção FlightDataCollection, que será utilizada por todas as classes
    private static final FlightDataCollection instance;

    // Bloco estático que inicializa a instância da coleção quando a classe é carregada
    static {
        // Criação da instância da coleção de voos
        instance = new FlightDataCollection();
    }

    // Construtor privado para impedir que a classe seja instanciada diretamente
    private FlightDataSingleton() {}

    // Método para obter a instância única da coleção de voos.
    public static FlightDataCollection getInstance() {
        // Retorna a instância da coleção de voos, garantindo que ela seja única.
        return instance;
    }
    
}