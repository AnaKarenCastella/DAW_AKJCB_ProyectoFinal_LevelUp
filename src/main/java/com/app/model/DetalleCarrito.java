package com.app.model;

public class DetalleCarrito {

    private int idDetalleCarrito;
    private int cantidad;
    private double subtotal;
    private int idCarrito;

    //private int idJuego;

    private int rawgId;

    public DetalleCarrito() {}

    public int getIdDetalleCarrito() { return idDetalleCarrito; }
    public void setIdDetalleCarrito(int idDetalleCarrito) { this.idDetalleCarrito = idDetalleCarrito; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public double getSubtotal() { return subtotal; }
    public void setSubtotal(double subtotal) { this.subtotal = subtotal; }

    public int getIdCarrito() { return idCarrito; }
    public void setIdCarrito(int idCarrito) { this.idCarrito = idCarrito; }

    //public int getIdJuego() { return idJuego; }
    //public void setIdJuego(int idJuego) { this.idJuego = idJuego; }
    public int getRawgId() { return rawgId; }
    public void setRawgId(int rawgId) { this.rawgId = rawgId;}

}