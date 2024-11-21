package br.edu.ifsp.dsw1.controller;

import br.edu.ifsp.dsw1.model.entity.FlightData;
import br.edu.ifsp.dsw1.model.entity.FlightDataCollection;
import br.edu.ifsp.dsw1.model.entity.FlightDataSingleton;
import br.edu.ifsp.dsw1.model.flightstates.Arriving;

import java.io.IOException;
import java.net.URLEncoder;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/cadastrarVoo.do")  // Mapeia este servlet para a URL "/cadastrarVoo.do"
public class CadastroVooController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Definindo as páginas para redirecionamento após ações específicas
    private static final String ADMIN_PAGE = "admin.jsp";  // Página de administração
    private static final String CADASTRO_VOO_PAGE = "cadastrarVoo.jsp";  // Página de cadastro de voo

    // Obtendo a coleção de voos a partir do Singleton
    private static final FlightDataCollection COLLECTION = FlightDataSingleton.getInstance();

    // Método POST para processar as requisições de cadastro de voo
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);  // Chama o método processRequest para lidar com a requisição
    }

    // Método GET para exibir o formulário de cadastro de voo
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Redireciona para a página de cadastro de voo
        request.getRequestDispatcher(CADASTRO_VOO_PAGE).forward(request, response);
    }

    // Método principal para processar a requisição
    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");  // Obtém o parâmetro "action" da requisição

        // Switch que lida com diferentes ações baseadas no valor de "action"
        switch (action) {
            case "cadastrarVoo":  // Caso a ação seja cadastrar voo
                cadastrarVoo(request, response);  // Chama o método para cadastrar o voo
                break;

            default:  // Se a ação não for reconhecida, retorna erro
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Ação desconhecida: " + action);
        }
    }

    // Método para cadastrar o voo
    private void cadastrarVoo(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            // Recupera o número do voo a partir da requisição e converte para Long
            Long number = parseLongParameter(request, "number");

            // Verifica se já existe um voo com o mesmo número
            boolean flightExists = COLLECTION.getAllFligthts().stream()
                    .anyMatch(f -> f.getFlightNumber().equals(number));

            if (flightExists) {
                // Se o voo já existir, define uma mensagem de erro e redireciona de volta para a página de cadastro
                request.setAttribute("errorMessageCadastro", "Já existe um voo com este número.");
                request.getRequestDispatcher(CADASTRO_VOO_PAGE).forward(request, response);
            } else {
                // Caso contrário, pega os parâmetros da companhia aérea e horário de chegada
                String company = request.getParameter("company").trim();
                String time = request.getParameter("time").trim();

                // Cria um novo objeto FlightData com os dados do voo
                FlightData flight = new FlightData(number, company, time);

                // Define o estado inicial do voo como "Arriving" (em chegada)
                flight.setState(Arriving.getInstance());

                // Insere o voo na coleção
                COLLECTION.insertFlight(flight);

                // Redireciona para a página de administração após o cadastro
                response.sendRedirect(ADMIN_PAGE);
            }

        } catch (NumberFormatException e) {
            // Caso o número do voo seja inválido, exibe mensagem de erro
            String errorMessage = "Dados inválidos: O número do voo deve ser um número válido.";
            response.sendRedirect(CADASTRO_VOO_PAGE + "?error=true&message=" + URLEncoder.encode(errorMessage, "UTF-8"));
        } catch (IllegalArgumentException e) {
            // Caso algum outro erro de dados seja encontrado, exibe a mensagem de erro
            String errorMessage = "Dados inválidos: " + e.getMessage();
            response.sendRedirect(CADASTRO_VOO_PAGE + "?error=true&message=" + URLEncoder.encode(errorMessage, "UTF-8"));
        }
    }

    // Método auxiliar para garantir que o parâmetro numérico é válido
    private Long parseLongParameter(HttpServletRequest request, String paramName) {
        String param = request.getParameter(paramName);  // Recupera o valor do parâmetro

        try {
            // Tenta converter o valor para Long
            return Long.parseLong(param);
        } catch (NumberFormatException e) {
            // Se ocorrer um erro de conversão, lança uma exceção com uma mensagem explicativa
            throw new IllegalArgumentException("O parâmetro '" + paramName + "' deve ser um número válido.");
        }
    }
}
