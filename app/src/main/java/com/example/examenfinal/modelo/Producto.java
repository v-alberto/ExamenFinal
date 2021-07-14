package com.example.examenfinal.modelo;

public class Producto {
    private String id;
    private String nomprod;
    private String precio;
    private  String stock;
    public Producto() {
    }
    public Producto(String nomprod, String precio, String stock) {
        this.nomprod = nomprod;
        this.precio = precio;
        this.stock = stock;
    }

    public String getNombres() {
        return nomprod;
    }

    public void setNombres(String nomprod) {
        this.nomprod = nomprod;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }
}
