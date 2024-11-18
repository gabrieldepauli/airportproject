<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Administração</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="style.css">
</head>
<body>

    <div class="container">
        <h2 class="text-center mt-5">Bem-vindo ao Painel Administrativo</h2>

        <div class="text-center mt-4">
            <p>Você está logado como <strong><%= session.getAttribute("user") %></strong></p>

            <div class="card mt-4">
                <div class="card-header bg-secondary text-white">
                    <h4 class="mb-0 text-center">Menu do Administrador</h4>
                </div>
                <div class="card-body">
                    <ul class="list-unstyled">
                        <li><a href="cadastrarVoo.do?action=cadastrarVoo" class="btn btn-primary btn-block mb-2">Cadastrar Voo</a></li>
                        <li><a href="atualizarVoo.jsp" class="btn btn-warning btn-block">Atualizar Voo</a></li>
                    </ul>
                </div>
            </div>

            <form action="login.do?action=logout" method="POST" class="mt-4">
                <button type="submit" class="btn btn-danger">Logout</button>
            </form>
            
            <a href="index.jsp" class="btn btn-secondary mt-3">Voltar ao Menu</a>
        </div>
        
    </div>

</body>
</html>
