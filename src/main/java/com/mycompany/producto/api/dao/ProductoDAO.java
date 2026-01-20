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
        if (entity == null) {
            throw new IllegalArgumentException("El producto no puede ser null");
        }
        String sql = "INSERT INTO Producto (articulo, categoria, precio, stock, codigo) VALUES (?,?,?,?,?)";

        try (Connection conn = dataSource.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, entity.getArticulo());
            stmt.setString(2, entity.getCategoria());
            stmt.setDouble(3, entity.getPrecio());
            stmt.setInt(4, entity.getStock());
            stmt.setInt(5, entity.getCodigo());

            int filasAfectadas = stmt.executeUpdate();

            if (filasAfectadas < 1) {
                throw new SQLException("No se pudo crear el producto");
            }

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    Long idGenerado = rs.getLong(1);
                    entity.setId(idGenerado);
                } else {
                    throw new SQLException("El producto fue creado pero no se pudo obtener el ID generado");
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Error al crear el producto en la base de datos", e);
        }
    }

    @Override
    public Producto leer(Integer id) throws Exception {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID inválido para leer producto");
        }
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
                prod.setId(rs.getLong("id"));
                productos.add(prod);
            }
        } catch (SQLException e) {
            throw new SQLException("Error al leer la lista de productos", e);
        }
        return productos;
    }

    @Override
    public void actualizar(Integer id, Producto entity) throws Exception {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID inválido para actualizar producto");
        }

        if (entity == null) {
            throw new IllegalArgumentException("El producto a actualizar no puede ser null");
        }
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
                throw new SQLException("ID no encontrado" + id);
            }
        } catch (SQLException e) {
            throw new SQLException("Error al actualizar el producto con id " + id, e);
        }
    }

    @Override
    public void eliminar(Integer id) throws Exception {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID inválido para eliminar producto");
        }
        String sql = "DELETE FROM Producto WHERE ID = ?";
        try (Connection conn = dataSource.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int filasAfectadas = stmt.executeUpdate();
            if (filasAfectadas == 0) {
                throw new SQLException("ID no encontrado " + id);
            }
        } catch (SQLException e) {
            throw new SQLException("Error al eliminar el producto con id " + id, e);
        }
    }

    public Producto leerCodigo(Integer codigo) throws Exception {
        if (codigo == null) {
            throw new IllegalArgumentException("Codigo inválido para leer producto");
        }
        String sql = "SELECT * FROM Producto WHERE codigo = ?";

        try (Connection conn = dataSource.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, codigo);

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
                return prod;
            } else {
                throw new SQLException("No se encontró producto con codigo " + codigo);
            }
        }
    }

}
