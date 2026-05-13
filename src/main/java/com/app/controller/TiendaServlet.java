package com.app.controller;

import com.app.model.Videojuego;
import com.app.service.RawgService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/tienda")
public class TiendaServlet extends HttpServlet {

    private RawgService rawgService = new RawgService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String busqueda = request.getParameter("busqueda");

        List<Videojuego> lista;

        if (busqueda != null && !busqueda.trim().isEmpty()) {
            lista = rawgService.buscarJuegos(busqueda);
            request.setAttribute("busqueda", busqueda);
        } else {
            lista = rawgService.obtenerJuegos();
        }

        request.setAttribute("lista", lista);

        request.getRequestDispatcher("/WEB-INF/tienda.jsp")
                .forward(request, response);
    }
}