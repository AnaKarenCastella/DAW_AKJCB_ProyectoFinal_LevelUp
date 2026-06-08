package com.app.controller;

import com.app.dao.UsuarioDAO;
import com.app.model.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;

@WebServlet("/registro")
public class RegistroServlet extends HttpServlet {

    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/registro.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nombre = request.getParameter("nombre");
        String correo = request.getParameter("correo");
        String password = request.getParameter("password");
        String telefono = request.getParameter("telefono");

        Usuario usuarioExistente = usuarioDAO.buscarPorCorreo(correo);

        if (usuarioExistente != null) {
            request.setAttribute("error", "Ya existe una cuenta registrada con ese correo.");
            request.getRequestDispatcher("/WEB-INF/registro.jsp")
                    .forward(request, response);
            return;
        }

        String passwordHash =
                BCrypt.hashpw(password, BCrypt.gensalt());

        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setCorreo(correo);
        usuario.setPassword(passwordHash);
        usuario.setRol("CLIENTE");
        usuario.setTelefono(telefono);

        usuarioDAO.insertar(usuario);

        request.setAttribute("mensaje", "Registro exitoso. Ahora puedes iniciar sesión.");
        request.getRequestDispatcher("/WEB-INF/login.jsp")
                .forward(request, response);
    }
}