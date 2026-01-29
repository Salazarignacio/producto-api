package com.mycompany.producto.api.model;

public class Producto {

    private Long id;
    private String codigo;
    private String articulo;
    private String categoria;
    private double precio;
    private int stock;

    public Producto(String articulo, String categoria, double precio, int stock, String codigo) {
        this.codigo = codigo;
        this.articulo = articulo;
        this.categoria = categoria;
        this.precio = precio;
        this.stock = stock;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Long getId() {
        return id;
    }

    public String getArticulo() {
        return articulo;
    }

    public String getCategoria() {
        return categoria;
    }

    public double getPrecio() {
        return precio;
    }

    public int getStock() {
        return stock;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setArticulo(String articulo) {
        this.articulo = articulo;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Producto{" + "id=" + id + ", codigo=" + codigo + ", articulo=" + articulo + ", categoria=" + categoria + ", precio=" + precio + ", stock=" + stock + '}';
    }
}
