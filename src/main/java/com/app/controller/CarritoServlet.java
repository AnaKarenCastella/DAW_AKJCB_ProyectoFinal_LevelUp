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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Integer idUsuario = obtenerIdUsuarioSesion(request, response);

        if (idUsuario == null) {
            return;
        }

        Carrito carrito = obtenerOCrearCarrito(idUsuario);

        List<DetalleCarrito> detalles =
                detalleCarritoDAO.obtenerPorCarrito(carrito.getIdCarrito());

        List<Videojuego> juegosCarrito = new ArrayList<>();

        for (DetalleCarrito detalle : detalles) {
            Videojuego juego =
                    rawgService.obtenerJuegoPorId(detalle.getRawgId());

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

        Integer idUsuario = obtenerIdUsuarioSesion(request, response);

        if (idUsuario == null) {
            return;
        }

        String accion = request.getParameter("accion");

        if (accion == null) {
            response.sendRedirect(request.getContextPath() + "/carrito");
            return;
        }

        switch (accion) {
            case "agregar":
                agregarProducto(request, response, idUsuario);
                break;

            case "eliminar":
                eliminarProducto(request, response, idUsuario);
                break;

            case "vaciar":
                vaciarCarrito(request, response, idUsuario);
                break;

            default:
                response.sendRedirect(request.getContextPath() + "/carrito");
                break;
        }
    }

    private void agregarProducto(HttpServletRequest request,
                                 HttpServletResponse response,
                                 int idUsuario)
            throws IOException {

        int rawgId =
                Integer.parseInt(request.getParameter("idJuego"));

        Carrito carrito =
                obtenerOCrearCarrito(idUsuario);

        Videojuego juego =
                rawgService.obtenerJuegoPorId(rawgId);

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

    private void eliminarProducto(HttpServletRequest request,
                                  HttpServletResponse response,
                                  int idUsuario)
            throws IOException {

        int idDetalleCarrito =
                Integer.parseInt(
                        request.getParameter("idDetalleCarrito")
                );

        detalleCarritoDAO.eliminarDetalle(idDetalleCarrito);

        Carrito carrito =
                obtenerOCrearCarrito(idUsuario);

        recalcularTotal(carrito.getIdCarrito());

        response.sendRedirect(request.getContextPath() + "/carrito");
    }

    private void vaciarCarrito(HttpServletRequest request,
                               HttpServletResponse response,
                               int idUsuario)
            throws IOException {

        Carrito carrito =
                obtenerOCrearCarrito(idUsuario);

        detalleCarritoDAO.vaciarCarrito(
                carrito.getIdCarrito()
        );

        carritoDAO.actualizarTotal(
                carrito.getIdCarrito(),
                0
        );

        response.sendRedirect(request.getContextPath() + "/carrito");
    }

    private Carrito obtenerOCrearCarrito(int idUsuario) {

        Carrito carrito =
                carritoDAO.obtenerPorUsuario(idUsuario);

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

    private Integer obtenerIdUsuarioSesion(HttpServletRequest request,
                                           HttpServletResponse response)
            throws IOException {

        HttpSession sesion =
                request.getSession(false);

        if (sesion == null ||
                sesion.getAttribute("idUsuario") == null) {

            response.sendRedirect(
                    request.getContextPath() + "/login"
            );

            return null;
        }

        return (Integer) sesion.getAttribute("idUsuario");
    }
}