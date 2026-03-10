package com.mycompany.producto.api.controller;

import com.mycompany.producto.api.model.Proveedor;
import com.mycompany.producto.api.service.ProveedorService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/proveedores")
public class ProveedorController {

    @Autowired
    private ProveedorService proveedorService;

    @PostMapping
    public ResponseEntity<?> crearProveedor(@RequestBody Proveedor proveedor) throws Exception {
        try {
            proveedorService.save(proveedor);
            return ResponseEntity.status(HttpStatus.CREATED).body(proveedor);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al crear el proveedor");
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Proveedor>> leerProveedores() throws Exception {
        return ResponseEntity.ok(proveedorService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> leerProveedor(@PathVariable int id) throws Exception {
        try {
            return ResponseEntity.ok(proveedorService.findById(id));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Proveedor no encontrado");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> EliminarProveedor(@PathVariable int id) throws Exception {
        try {
            proveedorService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> ActualizarProveedor(@PathVariable int id, @RequestBody Proveedor proveedor) throws Exception {
        try {
            proveedorService.update(id, proveedor);
            return ResponseEntity.ok("Proveedor actualizado");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se pudo actualizar el proveedor");
        }
    }
}
