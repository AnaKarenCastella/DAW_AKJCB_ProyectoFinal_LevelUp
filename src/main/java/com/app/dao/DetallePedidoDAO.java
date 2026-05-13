package com.app.dao;

import com.app.model.DetallePedido;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DetallePedidoDAO {

    // Agregar detalle al pedido
    public void agregarDetalle(int idPedido, int idJuego, int cantidad, double precioUnitario) {
        String sql = "INSERT INTO detalle_pedido(cantidad, precio_unitario, subtotal, id_pedido, id_juego) VALUES (?, ?, ?, ?, ?)";

        double subtotal = cantidad * precioUnitario;

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, cantidad);
            ps.setDouble(2, precioUnitario);
            ps.setDouble(3, subtotal);
            ps.setInt(4, idPedido);
            ps.setInt(5, idJuego);

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Obtener detalles por pedido
    public List<DetallePedido> obtenerPorPedido(int idPedido) {
        List<DetallePedido> lista = new ArrayList<>();
        String sql = "SELECT * FROM detalle_pedido WHERE id_pedido = ?";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idPedido);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                DetallePedido d = new DetallePedido();

                d.setIdDetallePedido(rs.getInt("id_detalle_pedido"));
                d.setCantidad(rs.getInt("cantidad"));
                d.setPrecioUnitario(rs.getDouble("precio_unitario"));
                d.setSubtotal(rs.getDouble("subtotal"));
                d.setIdPedido(rs.getInt("id_pedido"));
                d.setIdJuego(rs.getInt("id_juego"));

                lista.add(d);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    // Eliminar detalle
    public void eliminar(int idDetallePedido) {
        String sql = "DELETE FROM detalle_pedido WHERE id_detalle_pedido = ?";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idDetallePedido);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}