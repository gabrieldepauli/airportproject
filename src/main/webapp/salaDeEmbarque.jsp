<%@page import="br.edu.ifsp.dsw1.model.entity.FlightData"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0"> <!-- Torna a página responsiva, ajustando-se a diferentes tamanhos de tela -->
    <meta http-equiv="refresh" content="2"> <!-- Recarrega a página automaticamente a cada 2 segundos -->
    <title>Sala de Embarque</title>
    <link rel="icon" href="images/profile_image.png" type="image/png"> <!-- Define um ícone para a página -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"> <!-- Carrega a folha de estilos do Bootstrap -->
</head>
<body>
    <div class="container mt-5"> <!-- Cria um contêiner com uma margem superior de 5 unidades -->
        <h1 class="text-center mb-4">Voo Embarcando - Sala de Embarque</h1> <!-- Exibe o título "Voo Embarcando - Sala de Embarque" -->
        <hr> <!-- Insere uma linha horizontal para separar o título da tabela -->

        <div class="table-responsive"> <!-- Torna a tabela responsiva, permitindo que ela seja rolada horizontalmente em telas menores -->
            <table class="table table-bordered table-striped"> <!-- Cria uma tabela com bordas e linhas alternadas (striped) usando o Bootstrap -->
                <thead class="thead-dark"> <!-- Define o cabeçalho da tabela com fundo escuro -->
                    <tr>
                        <th class="text-center">Número do Voo</th> <!-- Cabeçalho para o número do voo -->
                        <th class="text-center">Companhia</th> <!-- Cabeçalho para a companhia aérea -->
                        <th class="text-center">Horário</th> <!-- Cabeçalho para o horário do voo -->
                        <th class="text-center">Estado Atual</th> <!-- Cabeçalho para o estado atual do voo -->
                    </tr>
                </thead>
                <tbody> <!-- Início do corpo da tabela onde os dados dos voos serão inseridos dinamicamente -->
                    <%
                    // Obtém a lista de voos a partir do atributo "flightsBoarding" da requisição
                    List<FlightData> lista = (List<FlightData>) request.getAttribute("flightsBoarding"); 
                    if (lista != null && !lista.isEmpty()) { // Verifica se a lista não é nula e se não está vazia
                        for (FlightData f : lista) { // Itera sobre cada objeto FlightData na lista
                            // Obtém o nome da classe do estado atual do voo
                            String stateInPortuguese = f.getState().getClass().getSimpleName(); 

                            // Se o estado for "Boarding", traduz para "Embarcando"
                            if ("Boarding".equals(stateInPortuguese)) {
                                stateInPortuguese = "Embarcando"; 
                            }
                    %>
                    <tr> <!-- Começo de uma nova linha da tabela para cada voo -->
                        <td class="text-center"><%= f.getFlightNumber() %></td> <!-- Exibe o número do voo -->
                        <td class="text-center"><%= f.getCompany() %></td> <!-- Exibe a companhia aérea -->
                        <td class="text-center"><%= f.getTime() %></td> <!-- Exibe o horário do voo -->
                        <td class="text-center"><%= stateInPortuguese %></td> <!-- Exibe o estado atual do voo (ex: "Embarcando") -->
                    </tr>
                    <%  
                        }
                    } else { // Caso não haja voos na lista (lista vazia ou nula)
                    %>
                    <tr>
                        <td colspan="5" class="text-center">Nenhum voo encontrado no sistema</td> <!-- Exibe uma mensagem informando que não há voos disponíveis -->
                    </tr>
                    <% 
                    }
                    %>
                </tbody>
            </table>
        </div>

        <!-- Botão para voltar à página inicial -->
        <div class="text-center mt-4">
            <a href="index.jsp" class="btn btn-secondary">Voltar</a> <!-- Link para a página "index.jsp" -->
        </div>
    </div>

    <!-- Scripts para carregar o jQuery, Popper.js e Bootstrap, necessários para alguns componentes interativos do Bootstrap -->
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</body>
</html>