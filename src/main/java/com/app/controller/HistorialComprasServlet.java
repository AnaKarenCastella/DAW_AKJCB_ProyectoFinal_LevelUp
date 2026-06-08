package com.app.controller;

import com.app.dao.PedidoDAO;
import com.app.model.Pedido;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/historialCompras")
public class HistorialComprasServlet extends HttpServlet {

    private PedidoDAO pedidoDAO = new PedidoDAO();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession sesion = request.getSession(false);

        if (sesion == null || sesion.getAttribute("idUsuario") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        int idUsuario = (Integer) sesion.getAttribute("idUsuario");

        List<Pedido> pedidos =
                pedidoDAO.obtenerPedidosPorCliente(idUsuario);

        request.setAttribute("pedidos", pedidos);

        request.getRequestDispatcher("/WEB-INF/historial-compras.jsp")
                .forward(request, response);
    }
}