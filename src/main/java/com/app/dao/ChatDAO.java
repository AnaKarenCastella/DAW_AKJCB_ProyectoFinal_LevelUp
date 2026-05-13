package com.app.dao;

import com.app.model.Chat;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ChatDAO {

    public int crearChat(int idCliente, int idAdministrador) {
        String sql = "INSERT INTO chats(fecha_inicio, estado, id_cliente, id_administrador) " +
                "VALUES (CURRENT_TIMESTAMP, 'ABIERTO', ?, ?) RETURNING id_conversacion";

        int idChat = 0;

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idCliente);
            ps.setInt(2, idAdministrador);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                idChat = rs.getInt("id_conversacion");
            }

        } catch (SQLException e) {
            System.out.println("Error al crear chat");
            e.printStackTrace();
        }

        return idChat;
    }

    public List<Chat> obtenerChatsPorCliente(int idCliente) {
        List<Chat> lista = new ArrayList<>();
        String sql = "SELECT * FROM chats WHERE id_cliente = ?";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idCliente);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Chat chat = new Chat();
                chat.setIdConversacion(rs.getInt("id_conversacion"));
                chat.setFechaInicio(rs.getTimestamp("fecha_inicio"));
                chat.setEstado(rs.getString("estado"));
                chat.setIdCliente(rs.getInt("id_cliente"));
                chat.setIdAdministrador(rs.getInt("id_administrador"));

                lista.add(chat);
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener chats por cliente");
            e.printStackTrace();
        }

        return lista;
    }

    public List<Chat> obtenerChatsPorAdministrador(int idAdministrador) {
        List<Chat> lista = new ArrayList<>();
        String sql = "SELECT * FROM chats WHERE id_administrador = ?";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idAdministrador);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Chat chat = new Chat();
                chat.setIdConversacion(rs.getInt("id_conversacion"));
                chat.setFechaInicio(rs.getTimestamp("fecha_inicio"));
                chat.setEstado(rs.getString("estado"));
                chat.setIdCliente(rs.getInt("id_cliente"));
                chat.setIdAdministrador(rs.getInt("id_administrador"));

                lista.add(chat);
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener chats por administrador");
            e.printStackTrace();
        }

        return lista;
    }

    public void cerrarChat(int idChat) {
        String sql = "UPDATE chats SET estado = 'CERRADO' WHERE id_conversacion = ?";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idChat);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error al cerrar chat");
            e.printStackTrace();
        }
    }
}