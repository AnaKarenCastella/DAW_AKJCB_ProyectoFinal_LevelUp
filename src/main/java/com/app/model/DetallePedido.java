package com.app.model;

public class DetallePedido {

    private int idDetallePedido;
    private int cantidad;
    private double precioUnitario;
    private double subtotal;
    private int idPedido;

    private int rawgId;

    public DetallePedido() {}

    public int getIdDetallePedido() { return idDetallePedido; }
    public void setIdDetallePedido(int idDetallePedido) { this.idDetallePedido = idDetallePedido; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public double getPrecioUnitario() { return precioUnitario; }
    public void setPrecioUnitario(double precioUnitario) { this.precioUnitario = precioUnitario; }

    public double getSubtotal() { return subtotal; }
    public void setSubtotal(double subtotal) { this.subtotal = subtotal; }

    public int getIdPedido() { return idPedido; }
    public void setIdPedido(int idPedido) { this.idPedido = idPedido; }

    public int getRawgId() {
        return rawgId;
    }

    public void setRawgId(int rawgId) {
        this.rawgId = rawgId;
    }
}