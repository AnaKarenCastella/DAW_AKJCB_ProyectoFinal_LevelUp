package com.app.dao;

import com.app.model.Resena;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ResenaDAO {

    public void insertar(Resena resena) {

        String sql =
                "INSERT INTO resena " +
                        "(rawg_id, nombre_usuario, calificacion, comentario) " +
                        "VALUES (?, ?, ?, ?)";

        try (
                Connection conn = Conexion.conectar();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setInt(1, resena.getRawgId());
            ps.setString(2, resena.getNombreUsuario());
            ps.setInt(3, resena.getCalificacion());
            ps.setString(4, resena.getComentario());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Resena> obtenerPorRawgId(int rawgId) {

        List<Resena> lista = new ArrayList<>();

        String sql =
                "SELECT id_resena, rawg_id, nombre_usuario, " +
                        "calificacion, comentario, fecha, estado " +
                        "FROM resena " +
                        "WHERE rawg_id = ? " +
                        "AND estado = 'ACTIVA' " +
                        "ORDER BY fecha DESC";

        try (
                Connection conn = Conexion.conectar();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setInt(1, rawgId);

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    Resena resena = new Resena();

                    resena.setIdResena(rs.getInt("id_resena"));
                    resena.setRawgId(rs.getInt("rawg_id"));
                    resena.setNombreUsuario(rs.getString("nombre_usuario"));
                    resena.setCalificacion(rs.getInt("calificacion"));
                    resena.setComentario(rs.getString("comentario"));
                    resena.setFecha(rs.getTimestamp("fecha"));
                    resena.setEstado(rs.getString("estado"));

                    lista.add(resena);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public double obtenerPromedioPorRawgId(int rawgId) {

        String sql =
                "SELECT id_resena, rawg_id, nombre_usuario, " +
                        "calificacion, comentario, fecha, estado " +
                        "FROM resena " +
                        "WHERE rawg_id = ? " +
                        "AND estado = 'ACTIVA' " +
                        "ORDER BY fecha DESC";

        try (
                Connection conn = Conexion.conectar();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setInt(1, rawgId);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    return rs.getDouble("promedio");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public void desactivar(int idResena) {

        String sql =
                "UPDATE resena" +
                "SET estado = 'OCULTA'" +
                "WHERE id_resena = ?";

        try (
                Connection conn = Conexion.conectar();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setInt(1, idResena);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}