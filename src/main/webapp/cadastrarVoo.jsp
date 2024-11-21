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
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cadastro de Voos</title>
    <link rel="icon" href="images/profile_image.png" type="image/png">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/style.css">
</head>
<body>

<% 
    String error = request.getParameter("error");
    if ("true".equals(error)) {
        String message = request.getParameter("message");
%>
    <div class="alert alert-danger text-center" role="alert">
        <%= message != null ? message : "Os dados inseridos são inválidos. Por favor, tente novamente." %>
    </div>
<% 
    }
%>

<div class="login-container">
    <h2 class="text-center">Cadastro de Voos</h2>
    <form action="cadastrarVoo.do" method="POST">
	    <input type="hidden" name="action" value="cadastrarVoo">
	    
	    <div class="form-group">
	        <label for="numeroVoo">Número do Voo</label>
	        <input type="text" class="form-control" id="numeroVoo" name="number" required>
	    </div>
	    <div class="form-group">
	        <label for="companhiaAerea">Companhia Aérea</label>
	        <input type="text" class="form-control" id="companhiaAerea" name="company" required>
	    </div>
	    <div class="form-group">
	        <label for="horarioChegada">Horário de Chegada</label>
	        <input type="datetime-local" class="form-control" id="horarioChegada" name="time" required>
	    </div>
	    <button type="submit" class="btn btn-success btn-block">Cadastrar Voo</button>
	    
	    <div class="text-center mt-3">
	        <br><a href="admin.jsp" class="btn btn-secondary">Voltar à página de Administrador</a>
	    </div>
	</form>


</div>

</body>
</html>
