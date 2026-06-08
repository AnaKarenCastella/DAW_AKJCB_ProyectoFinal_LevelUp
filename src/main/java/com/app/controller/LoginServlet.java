package com.app.controller;

import com.app.dao.UsuarioDAO;
import com.app.model.Usuario;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/login.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String correo = request.getParameter("correo");
        String password = request.getParameter("password");

        Usuario usuario = usuarioDAO.buscarPorCorreo(correo);

        boolean loginCorrecto = false;

        if (usuario != null && usuario.getPassword() != null) {

            String passwordGuardada = usuario.getPassword();

            if (passwordGuardada.startsWith("$2a$")
                    || passwordGuardada.startsWith("$2b$")
                    || passwordGuardada.startsWith("$2y$")) {

                loginCorrecto = BCrypt.checkpw(password, passwordGuardada);
            }
        }

        if (loginCorrecto) {

            HttpSession sesion = request.getSession();
            sesion.setAttribute("usuario", usuario);
            sesion.setAttribute("idUsuario", usuario.getIdUsuario());
            sesion.setAttribute("rol", usuario.getRol());

            response.sendRedirect(request.getContextPath() + "/tienda");

        } else {

            request.setAttribute("error", "Correo o contraseña incorrectos.");
            request.getRequestDispatcher("/WEB-INF/login.jsp")
                    .forward(request, response);
        }

        try {
        } catch (Exception e) {
            System.err.println("Error en LoginServlet: " + e.getMessage());
            request.setAttribute("error", "Ocurrió un error al iniciar sesión.");
            request.getRequestDispatcher("/WEB-INF/login.jsp")
                    .forward(request, response);
        }
    }
}