package com.mycompany.producto.api.dao;

import java.util.List;

public interface GenericDAO<T> {

    void crear(T entity) throws Exception;

    T leer(Integer id) throws Exception;
    
    List<T> leerCodigo(String id) throws Exception;

    List<T> leerTodos() throws Exception;

    void actualizar(Integer id, T entity) throws Exception;

    void eliminar(Integer id) throws Exception;
}
