package com.app.model;

import java.util.Date;

public class Mensaje {

    private int idMensaje;
    private String contenido;
    private Date fechaEnvio;
    private boolean leido;
    private int idChat;
    private int idEmisor;

    public Mensaje() {}

    public int getIdMensaje() { return idMensaje; }
    public void setIdMensaje(int idMensaje) { this.idMensaje = idMensaje; }

    public String getContenido() { return contenido; }
    public void setContenido(String contenido) { this.contenido = contenido; }

    public Date getFechaEnvio() { return fechaEnvio; }
    public void setFechaEnvio(Date fechaEnvio) { this.fechaEnvio = fechaEnvio; }

    public boolean isLeido() { return leido; }
    public void setLeido(boolean leido) { this.leido = leido; }

    public int getIdChat() { return idChat; }
    public void setIdChat(int idChat) { this.idChat = idChat; }

    public int getIdEmisor() { return idEmisor; }
    public void setIdEmisor(int idEmisor) { this.idEmisor = idEmisor; }
}