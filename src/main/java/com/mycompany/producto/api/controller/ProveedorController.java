package com.mycompany.producto.api.controller;

import com.mycompany.producto.api.model.Proveedor;
import com.mycompany.producto.api.service.ProveedorService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

}
