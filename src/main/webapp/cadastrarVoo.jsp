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
    <title>Cadastro de Voos</title>
    <link rel="icon" href="images/profile_image.png" type="image/png"> <!-- Define o ícone da página -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"> <!-- Carrega o CSS do Bootstrap -->
    <link rel="stylesheet" href="css/style.css"> <!-- Carrega o CSS -->
</head>
<body>

	<% 
	    // Verifica se a URL contém um parâmetro "error" com o valor "true"
	    String error = request.getParameter("error");
	    if ("true".equals(error)) {
	        // Se o parâmetro "error" for igual a "true", exibe uma mensagem de erro.
	        String message = request.getParameter("message");
	%>
	    <!-- Exibe uma caixa de alerta de erro (Bootstrap) se a operação falhou -->
	    <div class="alert alert-danger text-center" role="alert">
	        <%= message != null ? message : "Os dados inseridos são inválidos. Por favor, tente novamente." %>
	        <!-- Se a mensagem for nula, mostra uma mensagem padrão. Caso contrário, mostra a mensagem do parâmetro -->
	    </div>
	<% 
	    }
	%>
	
	<!-- Formulário de cadastro de voo -->
	<div class="login-container">
	    <h2 class="text-center">Cadastro de Voos</h2> <!-- Título centralizado da página -->
	    <form action="cadastrarVoo.do" method="POST"> <!-- Formulário que envia dados para o controlador cadastrarVoo.do -->
	        <input type="hidden" name="action" value="cadastrarVoo"> <!-- Campo oculto com a ação que será executada no servidor -->
	        
	        <!-- Campo para inserir o número do voo -->
	        <div class="form-group">
	            <label for="numeroVoo">Número do Voo</label> <!-- Rótulo para o campo -->
	            <input type="text" class="form-control" id="numeroVoo" name="number" required> <!-- Campo de texto para o número do voo, obrigatório -->
	        </div>
	
	        <!-- Campo para inserir a companhia aérea -->
	        <div class="form-group">
	            <label for="companhiaAerea">Companhia Aérea</label> <!-- Rótulo para o campo -->
	            <input type="text" class="form-control" id="companhiaAerea" name="company" required> <!-- Campo de texto para a companhia, obrigatório -->
	        </div>
	
	        <!-- Campo para inserir o horário de chegada -->
	        <div class="form-group">
	            <label for="horarioChegada">Horário de Chegada</label> <!-- Rótulo para o campo -->
	            <input type="datetime-local" class="form-control" id="horarioChegada" name="time" required> <!-- Campo de data e hora, obrigatório -->
	        </div>
	
	        <!-- Botão para submeter o formulário -->
	        <button type="submit" class="btn btn-success btn-block">Cadastrar Voo</button> <!-- Botão estilizado com o Bootstrap para enviar o formulário -->
	
	        <!-- Link para voltar à página administrativa -->
	        <div class="text-center mt-3">
	            <br><a href="admin.jsp" class="btn btn-secondary">Voltar à página de Administrador</a> <!-- Botão de voltar à página de administrador -->
	        </div>
	    </form>
	</div>

</body>
</html>
