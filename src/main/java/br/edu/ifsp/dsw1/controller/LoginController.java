package br.edu.ifsp.dsw1.controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login.do")  // Define o mapeamento da URL para este servlet como "/login.do"
public class LoginController extends HttpServlet { 
    private static final long serialVersionUID = 1L;

    // Método para lidar com requisições POST
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);  // Chama o método para processar a requisição
    }

    // Método para lidar com requisições GET
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Verifica se o usuário já está logado
        HttpSession session = request.getSession(false);  // Obtém a sessão existente, sem criá-la se não existir
        if (session != null && session.getAttribute("user") != null) {
            // Se o usuário já estiver logado, redireciona para a página de administração
            response.sendRedirect("admin.jsp");
        } else {
            // Se o usuário não estiver logado, exibe a página de login
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    // Método que processa a requisição dependendo da ação solicitada
    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");  // Obtém o parâmetro "action" da requisição

        if (action == null) {
            action = "login";  // Se não houver ação, assume a ação "login" como padrão
        }

        // Switch para processar as diferentes ações (login ou logout)
        switch (action) {
            case "login":
                handleLogin(request, response);  // Chama o método para processar o login
                break;

            case "logout":
                handleLogout(request, response);  // Chama o método para processar o logout
                break;

            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Ação não reconhecida");  // Retorna erro se a ação não for reconhecida
        }
    }

    // Método para realizar o login
    private void handleLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");  // Obtém o nome de usuário da requisição
        String password = request.getParameter("password");  // Obtém a senha da requisição

        // Chama o método de autenticação para verificar se o usuário e a senha são válidos
        if (authenticate(username, password)) {
            HttpSession session = request.getSession();  // Cria uma nova sessão ou obtém a existente
            session.setAttribute("user", username);  // Armazena o nome de usuário na sessão

            response.sendRedirect("admin.jsp");  // Redireciona para a página de administração após login bem-sucedido
        } else {
            response.sendRedirect("login.do?error=true");  // Redireciona de volta para a página de login com erro
        }
    }

    // Método para realizar o logout e encerrar a sessão
    private void handleLogout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);  // Obtém a sessão existente, sem criá-la se não existir
        
        if (session != null) {
            session.invalidate();  // Invalida a sessão para encerrar o login do usuário
        }
        
        response.sendRedirect("index.jsp");  // Redireciona para a página inicial após o logout
    }

    // Método para autenticar o usuário com base no nome de usuário e senha fornecidos
    private boolean authenticate(String username, String password) {
        // Verifica se o nome de usuário e a senha são ambos "admin"
        return "admin".equals(username) && "admin".equals(password);
    }
}
