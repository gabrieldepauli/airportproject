package br.edu.ifsp.dsw1.controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final String HOME_PAGE = "admin.jsp"; // página que será criada para as funções do admin (ainda não existe)
    private static final String LOGIN_PAGE = "login.jsp";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (authenticate(username, password)) {
            HttpSession session = request.getSession();
            session.setAttribute("user", username);

            response.sendRedirect(HOME_PAGE);
        } else {
            response.sendRedirect(LOGIN_PAGE + "?error=true");
        }
    }

    // Método que criado para autenticar o usuário e a senha
    private boolean authenticate(String username, String password) {
        return "admin".equals(username) && "admin".equals(password);
    }

    // Método criado para encerrar a sessão do usuário (logout) e remover as suas permissões
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        
        response.sendRedirect(LOGIN_PAGE);
    }
}
