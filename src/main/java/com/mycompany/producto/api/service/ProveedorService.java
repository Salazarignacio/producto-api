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

    public void delete(int id) throws Exception {
        if (id <= 0) {
            throw new IllegalArgumentException("ID inválido");
        }

        try {
            proveedorDAO.eliminar(id);
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar el proveedor con id " + id, e);
        }
    }

    public void update(int id, Proveedor entity) throws Exception {
        if (entity == null) {
            throw new IllegalArgumentException("El proveedor no puede ser null");
        }

        try {
            proveedorDAO.actualizar(id, entity);
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar el proveedor con id " + id, e);
        }
    }
}
