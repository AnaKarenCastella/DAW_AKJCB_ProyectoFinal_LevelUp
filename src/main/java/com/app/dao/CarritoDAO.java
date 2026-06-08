package com.app.dao;

import com.app.model.Carrito;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarritoDAO {

    // Crear carrito
    public void crearCarrito(int idUsuario) {
        String sql = "INSERT INTO carritos(fecha_creacion, total, id_usuario) VALUES (CURRENT_DATE, 0, ?)";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idUsuario);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Obtener todos los carritos
    public List<Carrito> obtenerTodos() {
        List<Carrito> lista = new ArrayList<>();
        String sql = "SELECT * FROM carritos";

        try (Connection con = Conexion.conectar();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Carrito c = new Carrito();

                c.setIdCarrito(rs.getInt("id_carrito"));
                c.setFechaCreacion(rs.getDate("fecha_creacion"));
                c.setTotal(rs.getDouble("total"));
                c.setIdUsuario(rs.getInt("id_usuario"));

                lista.add(c);
            }

        } catch (SQLException e) {
            System.err.println("Error" + e.getMessage());
            throw new RuntimeException("No fue posible actualizar el pedido", e);
        }

        return lista;
    }

    // Obtener carrito por usuario
    public Carrito obtenerPorUsuario(int idUsuario) {
        Carrito c = null;
        String sql = "SELECT * FROM carritos WHERE id_usuario = ?";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idUsuario);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                c = new Carrito();

                c.setIdCarrito(rs.getInt("id_carrito"));
                c.setFechaCreacion(rs.getDate("fecha_creacion"));
                c.setTotal(rs.getDouble("total"));
                c.setIdUsuario(rs.getInt("id_usuario"));
            }

        } catch (SQLException e) {
            System.err.println("Error" + e.getMessage());
            throw new RuntimeException("No fue posible crear el carrito", e);
        }

        return c;
    }

    // Actualizar total
    public void actualizarTotal(int idCarrito, double total) {
        String sql = "UPDATE carritos SET total = ? WHERE id_carrito = ?";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setDouble(1, total);
            ps.setInt(2, idCarrito);

            ps.executeUpdate();

        }catch (SQLException e) {
        System.err.println("Error" + e.getMessage());
        throw new RuntimeException("No fue posible actualizar el total", e);
        }
    }

    // Eliminar carrito
    public void eliminar(int idCarrito) {
        String sql = "DELETE FROM carritos WHERE id_carrito = ?";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idCarrito);
            ps.executeUpdate();

        } catch (SQLException e) {
        System.err.println("Error" + e.getMessage());
        throw new RuntimeException("No fue posible eliminar el producto del carrito", e);
        }
    }
}