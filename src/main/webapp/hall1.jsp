<%@page import="br.edu.ifsp.dsw1.model.entity.FlightData"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Hall 1</title>
    <link rel="icon" href="images/profile_image.png" type="image/png">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-5">
        <h1 class="text-center mb-4">Voo Decolando - Hall 1</h1>
        <hr>

        <div class="table-responsive">
            <table class="table table-bordered table-striped">
                <thead class="thead-dark">
                    <tr>
                        <th class="text-center">Número do Voo</th>
                        <th class="text-center">Companhia</th>
                        <th class="text-center">Horário</th>
                        <th class="text-center">Estado Atual</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                    // Corrigido para acessar "flightsTakingOff" no request
                    List<FlightData> lista = (List<FlightData>) request.getAttribute("flightsTakingOff");
                    if (lista != null && !lista.isEmpty()) {
                        for (FlightData f : lista) {
                            String stateInPortuguese = f.getState().getClass().getSimpleName();

                            if ("TakingOff".equals(stateInPortuguese)) {
                                stateInPortuguese = "Decolando";
                            }
                    %>
                    <tr>
                        <td class="text-center"><%= f.getFlightNumber() %></td>
                        <td class="text-center"><%= f.getCompany() %></td>
                        <td class="text-center"><%= f.getTime() %></td>
                        <td class="text-center"><%= stateInPortuguese %></td>
                    </tr>
                    <%  
                        }
                    } else {
                    %>
                    <tr>
                        <td colspan="5" class="text-center">Nenhum voo encontrado no sistema</td>
                    </tr>
                    <% 
                    }
                    %>
                </tbody>
            </table>
        </div>

        <div class="text-center mt-4">
            <a href="index.jsp" class="btn btn-secondary">Voltar</a>
        </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</body>
</html>
