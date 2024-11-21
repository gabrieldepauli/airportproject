<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" %>
<%
    // Verifica se o usuário já está logado
    if (session.getAttribute("user") != null) {
        // Se já estiver logado, redireciona para a página administrativa
        response.sendRedirect("admin.jsp");
        return;  // Interrompe a execução do código
    }
%>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0"> <!-- Torna a página responsiva, ajustando-se a diferentes tamanhos de tela -->
    <title>Login</title>
    <link rel="icon" href="images/profile_image.png" type="image/png"> <!-- Define o ícone da página -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"> <!-- Carrega o CSS do Bootstrap -->
    <link rel="stylesheet" href="css/style.css">
</head>
<body>

    <div class="login-container">
    <h2 class="text-center">Login</h2> <!-- Título centralizado da página, indicando que é a página de login -->

	    <!-- Exibe a mensagem de erro se o parâmetro 'error' for passado na URL -->
	    <%
	        // Recupera o parâmetro 'error' da URL
	        String error = request.getParameter("error");
	        // Verifica se o parâmetro 'error' existe e se é igual a "true", indicando erro no login
	        if (error != null && error.equals("true")) {
	    %>
	        <!-- Se houver um erro, exibe uma mensagem de alerta em vermelho com o texto 'Nome de usuário ou senha inválidos' -->
	        <div class="alert alert-danger" role="alert">
	            <strong>Erro!</strong> Nome de usuário ou senha inválidos.
	        </div>
	    <%
	        }
	    %>
	
	    <!-- Formulário de login -->
	    <form action="login.do" method="POST"> <!-- O formulário envia dados para o servlet login.do usando o método POST -->
	        <div class="form-group">
	            <label for="username">Nome de Usuário</label> <!-- Rótulo para o campo de nome de usuário -->
	            <input type="text" class="form-control" id="username" name="username" required> <!-- Campo de texto para o nome de usuário -->
	        </div>
	        <div class="form-group">
	            <label for="password">Senha</label> <!-- Rótulo para o campo de senha -->
	            <input type="password" class="form-control" id="password" name="password" required> <!-- Campo de senha, ocultando o texto digitado -->
	        </div>
	        <button type="submit" class="btn btn-primary btn-block">Entrar</button> <!-- Botão de envio do formulário estilizado com Bootstrap -->
	        
	        <!-- Link para voltar ao menu principal -->
	        <div class="text-center mt-3">
	            <br><a href="index.jsp" class="btn btn-secondary">Voltar ao Menu</a> <!-- Botão para voltar à página inicial -->
	        </div>
	    </form>
	    
	</div>


</body>
</html> 
