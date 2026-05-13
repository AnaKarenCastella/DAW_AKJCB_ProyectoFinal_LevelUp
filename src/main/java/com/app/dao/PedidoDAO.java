package com.app.dao;

import com.app.model.Pedido;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAO {

    // Crear pedido y regresar el ID generado
    public int crearPedido(int idCliente, double total) {
        String sql = "INSERT INTO pedidos(fecha, estado, total, id_cliente) " +
                "VALUES (CURRENT_DATE, 'PENDIENTE', ?, ?) RETURNING id_pedido";

        int idPedido = 0;

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setDouble(1, total);
            ps.setInt(2, idCliente);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                idPedido = rs.getInt("id_pedido");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return idPedido;
    }

    // Obtener todos los pedidos
    public List<Pedido> obtenerTodos() {
        List<Pedido> lista = new ArrayList<>();
        String sql = "SELECT * FROM pedidos";

        try (Connection con = Conexion.conectar();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Pedido p = new Pedido();

                p.setIdPedido(rs.getInt("id_pedido"));
                p.setFecha(rs.getDate("fecha"));
                p.setEstado(rs.getString("estado"));
                p.setTotal(rs.getDouble("total"));
                p.setIdCliente(rs.getInt("id_cliente"));

                lista.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    // Obtener pedidos por cliente
    public List<Pedido> obtenerPedidosPorCliente(int idCliente) {
        List<Pedido> lista = new ArrayList<>();
        String sql = "SELECT * FROM pedidos WHERE id_cliente = ?";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idCliente);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Pedido p = new Pedido();

                p.setIdPedido(rs.getInt("id_pedido"));
                p.setFecha(rs.getDate("fecha"));
                p.setEstado(rs.getString("estado"));
                p.setTotal(rs.getDouble("total"));
                p.setIdCliente(rs.getInt("id_cliente"));

                lista.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    // Buscar pedido por ID
    public Pedido obtenerPorId(int idPedido) {
        Pedido p = null;
        String sql = "SELECT * FROM pedidos WHERE id_pedido = ?";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idPedido);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                p = new Pedido();

                p.setIdPedido(rs.getInt("id_pedido"));
                p.setFecha(rs.getDate("fecha"));
                p.setEstado(rs.getString("estado"));
                p.setTotal(rs.getDouble("total"));
                p.setIdCliente(rs.getInt("id_cliente"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return p;
    }

    // Actualizar estado del pedido
    public void actualizarEstado(int idPedido, String estado) {
        String sql = "UPDATE pedidos SET estado = ?::estado_pedido_enum WHERE id_pedido = ?";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, estado);
            ps.setInt(2, idPedido);

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Eliminar pedido
    public void eliminar(int idPedido) {
        String sql = "DELETE FROM pedidos WHERE id_pedido = ?";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idPedido);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}