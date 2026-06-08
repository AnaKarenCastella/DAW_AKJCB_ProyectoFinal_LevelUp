package com.app.controller;

import com.app.dao.CarritoDAO;
import com.app.dao.DetalleCarritoDAO;
import com.app.model.Carrito;
import com.app.model.DetalleCarrito;
import com.app.model.Videojuego;
import com.app.service.RawgService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/carrito")
public class CarritoServlet extends HttpServlet {

    private CarritoDAO carritoDAO = new CarritoDAO();
    private DetalleCarritoDAO detalleCarritoDAO = new DetalleCarritoDAO();
    private RawgService rawgService = new RawgService();

    private static final int USUARIO_TEMPORAL = 1;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Carrito carrito = obtenerOCrearCarrito(USUARIO_TEMPORAL);

        List<DetalleCarrito> detalles =
                detalleCarritoDAO.obtenerPorCarrito(carrito.getIdCarrito());

        List<Videojuego> juegosCarrito = new ArrayList<>();

        for (DetalleCarrito detalle : detalles) {
            Videojuego juego = rawgService.obtenerJuegoPorId(detalle.getRawgId());

            if (juego != null) {
                juego.setPrecio(detalle.getSubtotal());
                juego.setStock(detalle.getCantidad());
                juego.setIdJuego(detalle.getIdDetalleCarrito());
                juegosCarrito.add(juego);
            }
        }

        request.setAttribute("carrito", carrito);
        request.setAttribute("detalles", detalles);
        request.setAttribute("juegosCarrito", juegosCarrito);

        request.getRequestDispatcher("/WEB-INF/carrito.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");

        if (accion == null) {
            response.sendRedirect(request.getContextPath() + "/carrito");
            return;
        }

        switch (accion) {
            case "agregar":
                agregarProducto(request, response);
                break;

            case "eliminar":
                eliminarProducto(request, response);
                break;

            case "vaciar":
                vaciarCarrito(request, response);
                break;

            default:
                response.sendRedirect(request.getContextPath() + "/carrito");
                break;
        }
    }

    private void agregarProducto(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        int rawgId = Integer.parseInt(request.getParameter("idJuego"));

        Carrito carrito = obtenerOCrearCarrito(USUARIO_TEMPORAL);

        Videojuego juego = rawgService.obtenerJuegoPorId(rawgId);

        if (juego != null) {
            int cantidad = 1;
            double subtotal = juego.getPrecio() * cantidad;

            detalleCarritoDAO.agregarProducto(
                    carrito.getIdCarrito(),
                    rawgId,
                    cantidad,
                    subtotal
            );

            recalcularTotal(carrito.getIdCarrito());
        }

        response.sendRedirect(request.getContextPath() + "/carrito");
    }

    private void eliminarProducto(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        int idDetalleCarrito =
                Integer.parseInt(request.getParameter("idDetalleCarrito"));

        detalleCarritoDAO.eliminarDetalle(idDetalleCarrito);

        Carrito carrito = obtenerOCrearCarrito(USUARIO_TEMPORAL);

        recalcularTotal(carrito.getIdCarrito());

        response.sendRedirect(request.getContextPath() + "/carrito");
    }

    private void vaciarCarrito(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        Carrito carrito = obtenerOCrearCarrito(USUARIO_TEMPORAL);

        detalleCarritoDAO.vaciarCarrito(carrito.getIdCarrito());

        carritoDAO.actualizarTotal(carrito.getIdCarrito(), 0);

        response.sendRedirect(request.getContextPath() + "/carrito");
    }

    private Carrito obtenerOCrearCarrito(int idUsuario) {

        Carrito carrito = carritoDAO.obtenerPorUsuario(idUsuario);

        if (carrito == null) {
            carritoDAO.crearCarrito(idUsuario);
            carrito = carritoDAO.obtenerPorUsuario(idUsuario);
        }

        return carrito;
    }

    private void recalcularTotal(int idCarrito) {

        List<DetalleCarrito> detalles =
                detalleCarritoDAO.obtenerPorCarrito(idCarrito);

        double total = 0;

        for (DetalleCarrito detalle : detalles) {
            total += detalle.getSubtotal();
        }

        carritoDAO.actualizarTotal(idCarrito, total);
    }
}