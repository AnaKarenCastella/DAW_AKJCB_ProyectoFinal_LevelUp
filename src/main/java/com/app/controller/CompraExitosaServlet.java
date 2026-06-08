package com.app.controller;

import com.app.dao.PedidoDAO;
import com.app.model.Pedido;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/compraExitosa")
public class CompraExitosaServlet extends HttpServlet {

    private PedidoDAO pedidoDAO = new PedidoDAO();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        String idPedidoStr = request.getParameter("idPedido");

        if (idPedidoStr == null) {
            response.sendRedirect(
                    request.getContextPath() + "/tienda"
            );
            return;
        }

        int idPedido = Integer.parseInt(idPedidoStr);

        Pedido pedido = pedidoDAO.obtenerPorId(idPedido);

        if (pedido == null) {
            response.sendRedirect(
                    request.getContextPath() + "/tienda"
            );
            return;
        }

        request.setAttribute("pedido", pedido);

        request.getRequestDispatcher(
                "/WEB-INF/compra-exitosa.jsp"
        ).forward(request, response);
    }
}