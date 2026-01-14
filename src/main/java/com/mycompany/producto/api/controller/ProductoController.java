package com.mycompany.producto.api.controller;

import com.mycompany.producto.api.model.Producto;
import com.mycompany.producto.api.service.ProductoService;
import java.util.ArrayList;
import java.util.List;
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
    public List<Producto> leerProductos() throws Exception {
        return pService.findAll();

    }

    @GetMapping("/{id}")
    public Producto leerProducto(@PathVariable int id) throws Exception {
        return pService.findById(id);
    }

    @PutMapping("/{id}")
    public String ActualizarProducto(@PathVariable int id, @RequestBody Producto producto) throws Exception {
        pService.update(id, producto);
        return "Producto actualizado";
    }

    @DeleteMapping("/{id}")
    public String EliminarProducto(@PathVariable int id) throws Exception {
        pService.delete(id);
        return "Producto Eliminado";
    }
}
