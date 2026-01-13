package com.mycompany.producto.api.dao;

import com.mycompany.producto.api.model.Producto;
import java.sql.Connection;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProductoDAO implements GenericDAO<Producto> {

    @Autowired
    private DataSource dataSource;

    @Override
    public void crear(Producto entity) throws Exception {
        try (Connection conn = dataSource.getConnection()) {
            System.out.println("Creando");
        }
    }

    @Override
    public Producto leer(Integer id) throws Exception {
        System.out.println("Leyendo");
        return null;
    }

    @Override
    public List<Producto> leerTodos() throws Exception {
        System.out.println("LeyendoTodos");
        return null;
    }

    @Override
    public void actualizar(Producto entity) throws Exception {
        System.out.println("Actualizando");
    }

    @Override
    public void eliminar(Integer id) throws Exception {
        System.out.println("Eliminando");
    }

}
