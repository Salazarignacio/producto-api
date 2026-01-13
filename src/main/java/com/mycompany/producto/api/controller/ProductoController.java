package com.mycompany.producto.api.controller;

import com.mycompany.producto.api.model.Producto;
import com.mycompany.producto.api.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoService pService;

    @PostMapping
    public String crearProducto(@RequestBody Producto producto) throws Exception {
        pService.save(producto);
        return "Producto guardado correctamente";
    }

    @GetMapping("/all")
    public String leerProductos() throws Exception {
        pService.findAll();
        return "Productos";
    }

    @GetMapping("/{id}")
    public String leerProducto(@PathVariable int id) throws Exception {
        pService.findById(id);
        return "Producto con id " + id;
    }

    @PutMapping
    public String ActualizarProducto(@RequestBody Producto producto) throws Exception {
        pService.update(producto);
        return "Producto actualizado";
    }

    @DeleteMapping("/{id}")
    public String EliminarProducto(@PathVariable int id) throws Exception {
        pService.delete(id);
        return "Producto Eliminado";
    }
}
