<%@page import="br.edu.ifsp.dsw1.model.entity.FlightData"%> 
<%@page import="java.util.List"%> 
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0"> <!-- Torna a página responsiva, ajustando-se a diferentes tamanhos de tela -->
    <meta http-equiv="refresh" content="2"> <!-- Recarrega a página automaticamente a cada 2 segundos -->
    <title>Sala de Desembarque</title> 
    <link rel="icon" href="images/profile_image.png" type="image/png"> <!-- Define o ícone da página -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"> <!-- Carrega o CSS do Bootstrap -->
</head>
<body>
    <div class="container mt-5"> <!-- Cria um contêiner com margem superior de 5 unidades, utilizando o layout do Bootstrap -->
        <h1 class="text-center mb-4">Voo Chegando - Sala de Desembarque</h1> <!-- Exibe o título "Voo Chegando - Sala de Desembarque" -->
        <hr> <!-- Linha horizontal para separar o título da tabela -->

        <div class="table-responsive"> <!-- Torna a tabela responsiva para que seja rolável em telas menores -->
            <table class="table table-bordered table-striped"> <!-- Cria uma tabela com bordas e alternância de cores nas linhas -->
                <thead class="thead-dark"> <!-- Cabeçalho da tabela com fundo escuro -->
                    <tr>
                        <th class="text-center">Número do Voo</th> <!-- Cabeçalho da coluna para o número do voo -->
                        <th class="text-center">Companhia</th> <!-- Cabeçalho da coluna para a companhia aérea -->
                        <th class="text-center">Horário</th> <!-- Cabeçalho da coluna para o horário do voo -->
                        <th class="text-center">Estado Atual</th> <!-- Cabeçalho da coluna para o estado atual do voo -->
                    </tr>
                </thead>
                <tbody> <!-- Corpo da tabela onde os dados dos voos serão inseridos dinamicamente -->
                    <%
                    // Recupera a lista de voos do atributo "flightsArriving" da requisição
                    List<FlightData> lista = (List<FlightData>) request.getAttribute("flightsArriving"); 
                    if (lista != null && !lista.isEmpty()) { // Verifica se a lista não está nula e se contém elementos
                        for (FlightData f : lista) { // Itera sobre cada objeto FlightData na lista
                            // Obtém o nome da classe do estado atual do voo
                            String stateInPortuguese = f.getState().getClass().getSimpleName();

                            // Se o estado for "Arriving", traduz para "Chegando"
                            if ("Arriving".equals(stateInPortuguese)) {
                                stateInPortuguese = "Chegando"; 
                            }
                    %>
                    <tr> <!-- Cada voo será exibido em uma nova linha da tabela -->
                        <td class="text-center"><%= f.getFlightNumber() %></td> <!-- Exibe o número do voo -->
                        <td class="text-center"><%= f.getCompany() %></td> <!-- Exibe a companhia aérea -->
                        <td class="text-center"><%= f.getTime() %></td> <!-- Exibe o horário de chegada do voo -->
                        <td class="text-center"><%= stateInPortuguese %></td> <!-- Exibe o estado atual do voo, como "Chegando" -->
                    </tr>
                    <%  
                        }
                    } else { // Caso a lista de voos esteja vazia ou nula
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

        <!-- Botão de voltar para a página inicial -->
        <div class="text-center mt-4">
            <a href="index.jsp" class="btn btn-secondary">Voltar</a> <!-- Link para voltar à página "index.jsp" -->
        </div>
    </div>

    <!-- Scripts para carregar o jQuery, Popper.js e Bootstrap, necessários para alguns componentes interativos do Bootstrap -->
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</body>
</html>
