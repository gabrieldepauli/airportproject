<%@page import="br.edu.ifsp.dsw1.model.entity.FlightData"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0"> <!-- Torna a página responsiva, ajustando-se a diferentes tamanhos de tela -->
    <meta http-equiv="refresh" content="2"> <!-- Recarrega a página automaticamente a cada 2 segundos -->
    <title>Hall 2</title>
    <link rel="icon" href="images/profile_image.png" type="image/png"> <!-- Define um ícone para a página -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"> <!-- Carrega a folha de estilos do Bootstrap -->
</head>
<body>
    <div class="container mt-5"> <!-- Cria um container com margem superior de 5 para centralizar o conteúdo -->
        <h1 class="text-center mb-4">Voo Decolado - Hall 2</h1> <!-- Título da página centralizado com margem inferior de 4 -->
        <hr> <!-- Linha horizontal para separar o título do conteúdo -->

        <div class="table-responsive"> <!-- Torna a tabela responsiva (adaptável a diferentes tamanhos de tela) -->
            <table class="table table-bordered table-striped"> <!-- Cria uma tabela com bordas e linhas alternadas coloridas -->
                <thead class="thead-dark"> <!-- Define o estilo escuro para o cabeçalho da tabela -->
                    <tr>
                        <th class="text-center">Número do Voo</th> <!-- Cabeçalho da coluna de número do voo -->
                        <th class="text-center">Companhia</th> <!-- Cabeçalho da coluna de companhia aérea -->
                        <th class="text-center">Horário</th> <!-- Cabeçalho da coluna de horário de partida -->
                        <th class="text-center">Estado Atual</th> <!-- Cabeçalho da coluna do estado do voo -->
                    </tr>
                </thead>
                <tbody>
                    <%
                    // Recupera a lista de voos que já decolaram do atributo "flightsTookOff" da requisição
                    List<FlightData> lista = (List<FlightData>) request.getAttribute("flightsTookOff"); 
                    if (lista != null && !lista.isEmpty()) { // Verifica se a lista de voos não está vazia
                        for (FlightData f : lista) { // Itera sobre cada voo na lista
                            String stateInPortuguese = f.getState().getClass().getSimpleName(); // Obtém o nome da classe do estado do voo

                            // Se o estado do voo for "TookOff", substitui por "Decolado" para exibição em português
                            if ("TookOff".equals(stateInPortuguese)) {
                                stateInPortuguese = "Decolado"; 
                            }
                    %>
                    <tr> <!-- Exibe os dados de cada voo em uma linha da tabela -->
                        <td class="text-center"><%= f.getFlightNumber() %></td> <!-- Exibe o número do voo -->
                        <td class="text-center"><%= f.getCompany() %></td> <!-- Exibe a companhia aérea -->
                        <td class="text-center"><%= f.getTime() %></td> <!-- Exibe o horário do voo -->
                        <td class="text-center"><%= stateInPortuguese %></td> <!-- Exibe o estado atual do voo -->
                    </tr>
                    <%  
                        }
                    } else { // Caso a lista de voos esteja vazia ou nula
                    %>
                    <tr>
                        <td colspan="5" class="text-center">Nenhum voo encontrado no sistema</td> <!-- Exibe uma mensagem dizendo que não há voos -->
                    </tr>
                    <% 
                    }
                    %>
                </tbody>
            </table>
        </div>

        <div class="text-center mt-4"> <!-- Cria uma área com margem superior de 4 para o botão -->
            <a href="index.jsp" class="btn btn-secondary">Voltar</a> <!-- Botão que leva o usuário de volta para a página principal -->
        </div>
    </div>
    
	<!-- Scripts para carregar o jQuery, Popper.js e Bootstrap, necessários para alguns componentes interativos do Bootstrap -->
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</body>
</html>
