package com.app.controller;

import com.app.dao.ResenaDAO;
import com.app.model.Resena;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/guardarResena")
public class ResenaServlet extends HttpServlet {

    private ResenaDAO resenaDAO = new ResenaDAO();

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        try {

            int rawgId =
                    Integer.parseInt(
                            request.getParameter("rawgId")
                    );

            String nombre =
                    request.getParameter("nombreUsuario");

            int calificacion =
                    Integer.parseInt(
                            request.getParameter("calificacion")
                    );

            String comentario =
                    request.getParameter("comentario");

            Resena resena = new Resena();

            resena.setRawgId(rawgId);
            resena.setNombreUsuario(nombre);
            resena.setCalificacion(calificacion);
            resena.setComentario(comentario);

            resenaDAO.insertar(resena);

            response.sendRedirect(
                    request.getContextPath()
                            + "/detalleJuego?id="
                            + rawgId
            );

        } catch (Exception e) {

            e.printStackTrace();

            response.sendRedirect(
                    request.getContextPath()
                            + "/tienda"
            );
        }
    }
}