<%@page import="br.edu.ifsp.dsw1.model.entity.FlightData"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="true"%>
<%
    // Verifica se o usuário está logado
    if (session.getAttribute("user") == null) {
        // Se não estiver logado, redireciona para a página de login
        response.sendRedirect("login.do");
        return;  // Interrompe a execução do código
    }
%>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0"> <!-- Torna a página responsiva, ajustando-se a diferentes tamanhos de tela -->
    <title>Atualizar Voo</title>
    <link rel="icon" href="images/profile_image.png" type="image/png"> <!-- Define o ícone da página -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"> <!-- Carrega o CSS do Bootstrap -->
</head>
<body>
    <div class="container mt-5"> <!-- Cria um container com uma margem superior de 5 -->
        <h1 class="text-center mb-4">Atualizar Estado do Voo</h1> <!-- Cabeçalho centralizado da página -->
        <hr> <!-- Linha horizontal para separar o título do conteúdo -->

        <div class="table-responsive"> <!-- Torna a tabela responsiva (adaptável para diferentes tamanhos de tela) -->
            <table class="table table-bordered table-striped"> <!-- Cria uma tabela com bordas e linhas alternadas -->
                <thead class="thead-dark"> <!-- Define o cabeçalho da tabela com fundo escuro -->
                    <tr>
                        <th class="text-center">Número do Voo</th> <!-- Coluna para o número do voo -->
                        <th class="text-center">Companhia</th> <!-- Coluna para a companhia aérea -->
                        <th class="text-center">Horário</th> <!-- Coluna para o horário do voo -->
                        <th class="text-center">Estado Atual</th> <!-- Coluna para o estado atual do voo -->
                        <th class="text-center">Ação</th> <!-- Coluna para o botão de ação -->
                    </tr>
                </thead>
                <tbody>
                    <%
                    // Obtém a lista de voos passada pelo controlador através do atributo "flights"
                    List<FlightData> lista = (List<FlightData>) request.getAttribute("flights");
                    if (lista != null && !lista.isEmpty()) { // Verifica se a lista não está vazia
                        // Itera sobre cada voo da lista
                        for (FlightData f : lista) {
                            // Obtém o nome do estado do voo e o traduz para o português
                            String stateInPortuguese = f.getState().getClass().getSimpleName();

                            // Tradução do estado do voo para português
                            if ("Arriving".equals(stateInPortuguese)) {
                                stateInPortuguese = "Chegando";
                            } else if ("Boarding".equals(stateInPortuguese)) {
                                stateInPortuguese = "Embarcando";
                            } else if ("TakingOff".equals(stateInPortuguese)) {
                                stateInPortuguese = "Decolando";
                            } else if ("TookOff".equals(stateInPortuguese)) {
                                stateInPortuguese = "Decolado";
                            }
                    %>

                    <!-- Exibe os dados do voo em uma nova linha da tabela -->
                    <tr>
                        <td class="text-center"><%= f.getFlightNumber() %></td> <!-- Exibe o número do voo -->
                        <td class="text-center"><%= f.getCompany() %></td> <!-- Exibe a companhia aérea -->
                        <td class="text-center"><%= f.getTime() %></td> <!-- Exibe o horário do voo -->
                        <td class="text-center"><%= stateInPortuguese %></td> <!-- Exibe o estado atual do voo -->
                        <td class="text-center"> 
                            <!-- Formulário para atualizar o estado do voo -->
                            <form action="updateVoo.do" method="post">
                                <input type="hidden" name="numeroVoo" value="<%= f.getFlightNumber() %>"> <!-- Passa o número do voo para a próxima página -->
                                <input type="hidden" name="action" value="updateFlightState"> <!-- Ação que será executada no controlador -->
                                <button type="submit" class="btn btn-primary btn-sm">Atualizar</button> <!-- Botão para enviar o formulário -->
                            </form>
                        </td>
                    </tr>
                    <%  
                        }
                    } else { // Caso a lista de voos esteja vazia ou nula
                    %>
                    <tr>
                        <td colspan="5" class="text-center">Nenhum voo encontrado no sistema</td> <!-- Exibe uma mensagem caso não haja voos -->
                    </tr>
                    <% 
                    }
                    %>
                </tbody>
            </table>
        </div>

        <div class="text-center mt-4"> <!-- Cria um container com margem superior para o botão -->
            <a href="admin.jsp" class="btn btn-secondary">Voltar</a> <!-- Botão que redireciona o usuário para a página admin.jsp -->
        </div>
    </div>

    <!-- Scripts necessários para o funcionamento do Bootstrap -->
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</body>
</html>
