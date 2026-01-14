package com.mycompany.producto.api.dao;

import com.mycompany.producto.api.model.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
        String sql = "INSERT INTO Producto (articulo, categoria, precio, stock, codigo) VALUES (?,?,?,?,?)";

        try (
                Connection conn = dataSource.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, entity.getArticulo());
            stmt.setString(2, entity.getCategoria());
            stmt.setDouble(3, entity.getPrecio());
            stmt.setInt(4, entity.getStock());
            stmt.setInt(5, entity.getCodigo());

            int filasAfectadas = stmt.executeUpdate();

            if (filasAfectadas < 1) {
                throw new SQLException("No se pudo crear el producto");
            }

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                Long idGenerado = rs.getLong(1);
                entity.setId(idGenerado);
            }

            System.out.println("Producto creado: " + entity);
        }

    }

    @Override
    public Producto leer(Integer id) throws Exception {
        String sql = "SELECT * FROM Producto WHERE id = ?";

        try (Connection conn = dataSource.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Producto prod = new Producto(
                        rs.getString("articulo"),
                        rs.getString("categoria"),
                        rs.getDouble("precio"),
                        rs.getInt("stock"),
                        rs.getInt("codigo")
                );

                prod.setId(rs.getLong("id"));

                System.out.println("Producto leído: " + prod);
                return prod;
            } else {
                throw new SQLException("No se encontró producto con id " + id);
            }
        }
    }

    @Override
    public ArrayList leerTodos() throws Exception {
        ArrayList<Producto> productos = new ArrayList();
        String sql = "SELECT * FROM Producto";
        try (Connection conn = dataSource.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Producto prod = new Producto(rs.getString("articulo"), rs.getString("categoria"), rs.getDouble("precio"), rs.getInt("stock"), rs.getInt("codigo"));
                productos.add(prod);
            }
        }
        return productos;
    }

    @Override
    public void actualizar(Integer id, Producto entity) throws Exception {
        String sql = "UPDATE Producto "
                + "SET articulo = ?, categoria = ?, precio = ?, stock = ?, codigo = ? "
                + "WHERE id = ?";
        try (Connection conn = dataSource.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, entity.getArticulo());
            stmt.setString(2, entity.getCategoria());
            stmt.setDouble(3, entity.getPrecio());
            stmt.setInt(4, entity.getStock());
            stmt.setInt(5, entity.getCodigo());
            stmt.setLong(6, id);
            int filasAfectadas = stmt.executeUpdate();
            if (filasAfectadas == 0) {
                throw new SQLException("ID no encontrado");
            }
        }
    }

    @Override
    public void eliminar(Integer id) throws Exception {
        String sql = "DELETE FROM Producto WHERE ID = ?";
        try (Connection conn = dataSource.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int filasAfectadas = stmt.executeUpdate();
            if (filasAfectadas == 0) {
                throw new SQLException("ID no encontrado");
            } else {
                System.out.println("ID eliminado correctamente");
            }
        }
    }

}
