package com.app.controller;

import com.app.model.Videojuego;
import com.app.service.RawgService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/detalleJuego")
public class DetalleJuegoServlet extends HttpServlet {

    private RawgService rawgService = new RawgService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idParam = request.getParameter("id");

        if (idParam == null || idParam.trim().isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/tienda");
            return;
        }

        try {
            int id = Integer.parseInt(idParam);

            Videojuego juego = rawgService.obtenerJuegoPorId(id);

            if (juego == null) {
                response.sendRedirect(request.getContextPath() + "/tienda");
                return;
            }

            request.setAttribute("juego", juego);

            request.getRequestDispatcher("/WEB-INF/detalle-juego.jsp")
                    .forward(request, response);

        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/tienda");
        }
    }
}