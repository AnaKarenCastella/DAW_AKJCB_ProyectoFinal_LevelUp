package com.app.controller;

import com.app.dao.ResenaDAO;
import com.app.model.Resena;
import com.app.model.Videojuego;
import com.app.service.RawgService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/detalleJuego")
public class DetalleJuegoServlet extends HttpServlet {

    private RawgService rawgService = new RawgService();
    private ResenaDAO resenaDAO = new ResenaDAO();

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

            List<Resena> resenas =
                    resenaDAO.obtenerPorRawgId(id);

            double promedio =
                    resenaDAO.obtenerPromedioPorRawgId(id);

            request.setAttribute("juego", juego);
            request.setAttribute("resenas", resenas);
            request.setAttribute("promedioResenas", promedio);

            request.getRequestDispatcher("/WEB-INF/detalle-juego.jsp")
                    .forward(request, response);

        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/tienda");
        }
    }
}