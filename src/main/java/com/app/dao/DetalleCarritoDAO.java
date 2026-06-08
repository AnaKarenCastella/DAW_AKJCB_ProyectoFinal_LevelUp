package com.app.dao;

import com.app.model.DetalleCarrito;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DetalleCarritoDAO {

    // Agregar producto al carrito
    public void agregarProducto(int idCarrito, int rawgId, int cantidad, double subtotal) {
        String sql = "INSERT INTO detalle_carrito(cantidad, subtotal, id_carrito, rawg_id) VALUES (?, ?, ?, ?)";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, cantidad);
            ps.setDouble(2, subtotal);
            ps.setInt(3, idCarrito);
            ps.setInt(4, rawgId);

            ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error al agregar producto al carrito: "
                    + e.getMessage());
            throw new RuntimeException("No fue posible agregar el producto al carrito.", e);
        }
    }

    // Obtener productos por carrito
    public List<DetalleCarrito> obtenerPorCarrito(int idCarrito) {
        List<DetalleCarrito> lista = new ArrayList<>();
        String sql = "SELECT * FROM detalle_carrito WHERE id_carrito = ?";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idCarrito);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                DetalleCarrito d = new DetalleCarrito();

                d.setIdDetalleCarrito(rs.getInt("id_detalle_carrito"));
                d.setCantidad(rs.getInt("cantidad"));
                d.setSubtotal(rs.getDouble("subtotal"));
                d.setIdCarrito(rs.getInt("id_carrito"));
                d.setRawgId(rs.getInt("rawg_id"));

                lista.add(d);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    // Actualizar cantidad y subtotal
    public void actualizarCantidad(int idDetalleCarrito, int cantidad, double subtotal) {
        String sql = "UPDATE detalle_carrito SET cantidad = ?, subtotal = ? WHERE id_detalle_carrito = ?";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, cantidad);
            ps.setDouble(2, subtotal);
            ps.setInt(3, idDetalleCarrito);

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Eliminar un producto del carrito
    public void eliminarDetalle(int idDetalleCarrito) {
        String sql = "DELETE FROM detalle_carrito WHERE id_detalle_carrito = ?";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idDetalleCarrito);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Vaciar carrito completo
    public void vaciarCarrito(int idCarrito) {
        String sql = "DELETE FROM detalle_carrito WHERE id_carrito = ?";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idCarrito);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error al vaciar carrito: " + e.getMessage());
            throw new RuntimeException("No fue posible vaciar el carrito.", e);
        }
    }
}