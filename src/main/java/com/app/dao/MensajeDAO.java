package com.app.dao;

import com.app.model.Mensaje;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MensajeDAO {

    public void enviarMensaje(Mensaje mensaje) {
        String sql = "INSERT INTO mensajes(contenido, fecha_envio, leido, id_chat, id_emisor) " +
                "VALUES (?, CURRENT_TIMESTAMP, false, ?, ?)";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, mensaje.getContenido());
            ps.setInt(2, mensaje.getIdChat());
            ps.setInt(3, mensaje.getIdEmisor());

            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error al enviar mensaje");
            e.printStackTrace();
        }
    }

    public List<Mensaje> obtenerMensajesPorChat(int idChat) {
        List<Mensaje> lista = new ArrayList<>();
        String sql = "SELECT * FROM mensajes WHERE id_chat = ? ORDER BY fecha_envio ASC";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idChat);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Mensaje mensaje = new Mensaje();
                mensaje.setIdMensaje(rs.getInt("id_mensaje"));
                mensaje.setContenido(rs.getString("contenido"));
                mensaje.setFechaEnvio(rs.getTimestamp("fecha_envio"));
                mensaje.setLeido(rs.getBoolean("leido"));
                mensaje.setIdChat(rs.getInt("id_chat"));
                mensaje.setIdEmisor(rs.getInt("id_emisor"));

                lista.add(mensaje);
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener mensajes");
            e.printStackTrace();
        }

        return lista;
    }

    public void marcarComoLeido(int idMensaje) {
        String sql = "UPDATE mensajes SET leido = true WHERE id_mensaje = ?";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idMensaje);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error al marcar mensaje como leído");
            e.printStackTrace();
        }
    }

    public void eliminarMensaje(int idMensaje) {
        String sql = "DELETE FROM mensajes WHERE id_mensaje = ?";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idMensaje);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error al eliminar mensaje");
            e.printStackTrace();
        }
    }
}