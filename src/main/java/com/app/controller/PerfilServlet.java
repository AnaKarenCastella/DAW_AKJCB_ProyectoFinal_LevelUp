package com.app.controller;

import com.app.model.Usuario;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/perfil")
public class PerfilServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession sesion = request.getSession(false);

        if (sesion == null || sesion.getAttribute("usuario") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        Usuario usuario = (Usuario) sesion.getAttribute("usuario");

        request.setAttribute("usuario", usuario);

        request.getRequestDispatcher("/WEB-INF/perfil.jsp")
                .forward(request, response);
    }
}