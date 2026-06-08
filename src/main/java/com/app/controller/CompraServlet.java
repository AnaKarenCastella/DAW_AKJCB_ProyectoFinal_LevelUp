package com.app.controller;

import com.app.dao.CarritoDAO;
import com.app.dao.DetalleCarritoDAO;
import com.app.dao.DetallePedidoDAO;
import com.app.dao.PedidoDAO;
import com.app.model.Carrito;
import com.app.model.DetalleCarrito;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/comprar")
public class CompraServlet extends HttpServlet {

    private CarritoDAO carritoDAO = new CarritoDAO();
    private DetalleCarritoDAO detalleCarritoDAO = new DetalleCarritoDAO();
    private PedidoDAO pedidoDAO = new PedidoDAO();
    private DetallePedidoDAO detallePedidoDAO = new DetallePedidoDAO();

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession sesion = request.getSession(false);

        if (sesion == null || sesion.getAttribute("idUsuario") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        int idUsuario = (Integer) sesion.getAttribute("idUsuario");

        Carrito carrito = carritoDAO.obtenerPorUsuario(idUsuario);

        if (carrito == null || carrito.getTotal() <= 0) {
            response.sendRedirect(request.getContextPath() + "/carrito");
            return;
        }

        List<DetalleCarrito> detalles =
                detalleCarritoDAO.obtenerPorCarrito(carrito.getIdCarrito());

        if (detalles == null || detalles.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/carrito");
            return;
        }

        int idPedido =
                pedidoDAO.crearPedido(idUsuario, carrito.getTotal());

        for (DetalleCarrito detalle : detalles) {

            double precioUnitario =
                    detalle.getSubtotal() / detalle.getCantidad();

            detallePedidoDAO.agregarDetalle(
                    idPedido,
                    detalle.getRawgId(),
                    detalle.getCantidad(),
                    precioUnitario
            );
        }

        pedidoDAO.actualizarEstado(idPedido, "CONFIRMADO");

        detalleCarritoDAO.vaciarCarrito(carrito.getIdCarrito());
        carritoDAO.actualizarTotal(carrito.getIdCarrito(), 0);

        response.sendRedirect(
                request.getContextPath()
                        + "/compraExitosa?idPedido="
                        + idPedido
        );
    }
}