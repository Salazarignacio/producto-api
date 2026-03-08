package com.mycompany.producto.api.model;

public class Proveedor {

    private Long id;
    private String nombre;

    public Proveedor() {
    }

    public Proveedor(String nombre) {

        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Proveedor{" + "id=" + id + ", nombre=" + nombre + '}';
    }

}
