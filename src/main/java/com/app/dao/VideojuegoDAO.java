package com.app.dao;

import com.app.model.Videojuego;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VideojuegoDAO {

    // Obtener todos los videojuegos
    public List<Videojuego> obtenerTodos() {
        List<Videojuego> lista = new ArrayList<>();
        String sql = "SELECT * FROM videojuegos";

        try (Connection con = Conexion.conectar();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Videojuego v = new Videojuego();

                v.setIdJuego(rs.getInt("id_juego")); // ✔ corregido
                v.setTitulo(rs.getString("titulo"));
                v.setDescripcion(rs.getString("descripcion"));
                v.setPlataforma(rs.getString("plataforma"));
                v.setCategoria(rs.getString("categoria"));
                v.setPrecio(rs.getDouble("precio"));
                v.setStock(rs.getInt("stock"));

                lista.add(v);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    // Insertar videojuego
    public void insertar(Videojuego juego) {
        String sql = "INSERT INTO videojuegos (titulo, descripcion, plataforma, categoria, precio, stock) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, juego.getTitulo());
            ps.setString(2, juego.getDescripcion());
            ps.setString(3, juego.getPlataforma());
            ps.setString(4, juego.getCategoria());
            ps.setDouble(5, juego.getPrecio());
            ps.setInt(6, juego.getStock());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Buscar por ID
    public Videojuego obtenerPorId(int id) {
        Videojuego v = null;
        String sql = "SELECT * FROM videojuegos WHERE id_juego = ?";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                v = new Videojuego();

                v.setIdJuego(rs.getInt("id_juego"));
                v.setTitulo(rs.getString("titulo"));
                v.setDescripcion(rs.getString("descripcion"));
                v.setPlataforma(rs.getString("plataforma"));
                v.setCategoria(rs.getString("categoria"));
                v.setPrecio(rs.getDouble("precio"));
                v.setStock(rs.getInt("stock"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return v;
    }

    // Actualizar videojuego
    public void actualizar(Videojuego juego) {
        String sql = "UPDATE videojuegos SET titulo=?, descripcion=?, plataforma=?, categoria=?, precio=?, stock=? WHERE id_juego=?";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, juego.getTitulo());
            ps.setString(2, juego.getDescripcion());
            ps.setString(3, juego.getPlataforma());
            ps.setString(4, juego.getCategoria());
            ps.setDouble(5, juego.getPrecio());
            ps.setInt(6, juego.getStock());
            ps.setInt(7, juego.getIdJuego());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Eliminar videojuego
    public void eliminar(int id) {
        String sql = "DELETE FROM videojuegos WHERE id_juego = ?";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}