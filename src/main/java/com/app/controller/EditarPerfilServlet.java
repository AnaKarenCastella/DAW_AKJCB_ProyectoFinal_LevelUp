package com.app.controller;

import com.app.dao.UsuarioDAO;
import com.app.model.Usuario;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/editarPerfil")
public class EditarPerfilServlet extends HttpServlet {

    private UsuarioDAO usuarioDAO;

    @Override
    public void init() {
        usuarioDAO = new UsuarioDAO();
    }

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

        request.getRequestDispatcher("/WEB-INF/editarPerfil.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession sesion = request.getSession(false);

        if (sesion == null || sesion.getAttribute("usuario") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        Usuario usuarioSesion = (Usuario) sesion.getAttribute("usuario");

        String nombre = request.getParameter("nombre");
        String correo = request.getParameter("correo");
        String telefono = request.getParameter("telefono");

        usuarioSesion.setNombre(nombre);
        usuarioSesion.setCorreo(correo);
        usuarioSesion.setTelefono(telefono);

        usuarioDAO.actualizarPerfil(usuarioSesion);

        // Actualiza la sesión para que cambie el navbar
        sesion.setAttribute("usuario", usuarioSesion);

        response.sendRedirect(request.getContextPath() + "/perfil");
    }
}