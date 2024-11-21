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
    <title>Administração</title>
    <link rel="icon" href="images/profile_image.png" type="image/png"> <!-- Define o ícone da página -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"> <!-- Carrega o CSS do Bootstrap -->
    <link rel="stylesheet" href="style.css"> <!-- Carrega o CSS -->
</head>
<body>

    <div class="container"> <!-- Cria um container para centralizar e organizar o conteúdo da página -->
    <h2 class="text-center mt-5">Bem-vindo ao Painel Administrativo</h2> <!-- Exibe o título centralizado com margem superior de 5 -->

	    <div class="text-center mt-4"> <!-- Cria uma seção centralizada com margem superior de 4 -->
	        <!-- Exibe uma mensagem informando o nome do usuário logado, obtido da sessão -->
	        <p>Você está logado como <strong><%= session.getAttribute("user") %></strong></p>
	
	        <div class="card mt-4"> <!-- Cria um cartão com margem superior de 4 -->
	            <div class="card-header bg-secondary text-white"> <!-- Cabeçalho do cartão com fundo cinza e texto branco -->
	                <h4 class="mb-0 text-center">Menu do Administrador</h4> <!-- Título do cartão, centralizado -->
	            </div>
	            <div class="card-body"> <!-- Corpo do cartão -->
	                <ul class="list-unstyled"> <!-- Lista sem formatação para os itens do menu -->
	                    <!-- Link para cadastrar um novo voo -->
	                    <li><a href="cadastrarVoo.do?action=cadastrarVoo" class="btn btn-primary btn-block mb-2">Cadastrar Voo</a></li>
	                    <!-- Link para acessar a página de atualização de voo -->
	                    <li><a href="updateVoo.do?action=showFlights" class="btn btn-warning btn-block">Atualizar Voo</a></li>
	                </ul>
	            </div>
	        </div>
	
	        <!-- Formulário para realizar o logout -->
	        <form action="login.do?action=logout" method="POST" class="mt-4"> <!-- Formulário que envia uma requisição POST para a ação de logout -->
	            <button type="submit" class="btn btn-danger">Logout</button> <!-- Botão de logout estilizado com a classe btn-danger (vermelho) -->
	        </form>
	
	        <!-- Link para voltar ao menu principal -->
	        <a href="index.jsp" class="btn btn-secondary mt-3">Voltar ao Menu</a> <!-- Link que leva de volta à página inicial -->
	    </div>
    
	</div>

</body>
</html>
