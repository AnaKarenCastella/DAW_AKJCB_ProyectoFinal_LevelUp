package com.app.controller;

import com.app.model.Videojuego;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/tienda")
public class TiendaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Videojuego> lista = new ArrayList<>();

        Videojuego v1 = new Videojuego();
        v1.setTitulo("Minecraft");
        v1.setDescripcion("Juego de aventura y construcción.");
        v1.setPrecio(499.00);
        v1.setStock(20);
        lista.add(v1);

        Videojuego v2 = new Videojuego();
        v2.setTitulo("Cyberpunk 2077");
        v2.setDescripcion("RPG de mundo abierto futurista.");
        v2.setPrecio(899.00);
        v2.setStock(10);
        lista.add(v2);

        Videojuego v3 = new Videojuego();
        v3.setTitulo("Elden Ring");
        v3.setDescripcion("Aventura de fantasía y acción.");
        v3.setPrecio(999.00);
        v3.setStock(8);
        lista.add(v3);

        request.setAttribute("lista", lista);

        request.getRequestDispatcher("/WEB-INF/tienda.jsp")
                .forward(request, response);
    }
}