package com.app.model;

import java.sql.Timestamp;

public class Resena {

    private int idResena;
    private int rawgId;
    private String nombreUsuario;
    private int calificacion;
    private String comentario;
    private Timestamp fecha;
    private String estado;

    public Resena() {
    }

    public int getIdResena() {
        return idResena;
    }

    public void setIdResena(int idResena) {
        this.idResena = idResena;
    }

    public int getRawgId() {
        return rawgId;
    }

    public void setRawgId(int rawgId) {
        this.rawgId = rawgId;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}