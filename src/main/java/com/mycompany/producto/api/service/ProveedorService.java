package com.mycompany.producto.api.service;

import com.mycompany.producto.api.dao.ProveedorDAO;
import com.mycompany.producto.api.model.Proveedor;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProveedorService {

    @Autowired
    private final ProveedorDAO proveedorDAO;

    public ProveedorService(ProveedorDAO proveedorDAO) {
        this.proveedorDAO = proveedorDAO;
    }

    public void save(Proveedor entity) throws Exception {
        proveedorDAO.crear(entity);
    }

    public Proveedor findById(int id) throws Exception {
        if (id <= 0) {
            throw new IllegalArgumentException("ID inválido");
        }
        try {
            return proveedorDAO.leer(id);
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar el producto con id " + id, e);
        }
    }

    public List<Proveedor> findAll() throws Exception {
        try {
            return proveedorDAO.leerTodos();
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener lista de proveedores", e);
        }
    }
}
