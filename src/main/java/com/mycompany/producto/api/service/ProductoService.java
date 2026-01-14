package com.mycompany.producto.api.service;

import com.mycompany.producto.api.dao.ProductoDAO;
import com.mycompany.producto.api.model.Producto;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class ProductoService implements GenericService<Producto> {
    
    @Autowired
    private final ProductoDAO prodDAO;

    public ProductoService(ProductoDAO prodDAO) {
        this.prodDAO = prodDAO;
    }

    @Override
    public void save(Producto entity) throws Exception {
        prodDAO.crear(entity);
    }

    @Override
    public Producto findById(int id) throws Exception {
        return prodDAO.leer(id);
    }

    @Override
    public List<Producto> findAll() throws Exception {
        return prodDAO.leerTodos();
    }

    @Override
    public void update(int id, Producto entity) throws Exception {
        prodDAO.actualizar(id, entity);
    }

    @Override
    public void delete(int id) throws Exception {
        prodDAO.eliminar(id);
    }

}
