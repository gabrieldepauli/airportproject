<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/style.css">
</head>
<body>

    <div class="login-container">
        <h2 class="text-center">Login</h2>
		
		<!-- Caso insira login e senha errados, esse método ira gerar a mensagem de erro na tela -->
        <%
            String error = request.getParameter("error");
            if (error != null && error.equals("true")) {
        %>
            <div class="alert alert-danger" role="alert">
                <strong>Erro!</strong> Nome de usuário ou senha inválidos.
            </div>
        <%
            }
        %>

        <form action="login" method="POST">
            <div class="form-group">
                <label for="username">Nome de Usuário</label>
                <input type="text" class="form-control" id="username" name="username" required>
            </div>
            <div class="form-group">
                <label for="password">Senha</label>
                <input type="password" class="form-control" id="password" name="password" required>
            </div>
            <button type="submit" class="btn btn-primary btn-block">Entrar</button>
            
            <div class="text-center mt-3">
                <br><a href="index.jsp" class="btn btn-secondary">Voltar ao Menu</a>
            </div>
        </form>
        
    </div>

</body>
</html>