package com.mycompany.producto.api;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import javax.sql.DataSource;

@SpringBootApplication
public class ProductoApi implements CommandLineRunner {

    @Autowired
    private DataSource dataSource;

    public static void main(String[] args) throws Exception {
        SpringApplication.run(ProductoApi.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        try {
            // Verificar conexión a la base de datos (optimizado para no consumir conexiones del pool)
            try (var connection = dataSource.getConnection()) {
                System.out.println("✅ Conexión a la base de datos establecida exitosamente!");
                System.out.println("📍 URL de conexión: " + connection.getMetaData().getURL());
                System.out.println("🗄️  Base de datos: " + connection.getCatalog());
                System.out.println("🔌 Conexiones máximas configuradas: " + 
                    dataSource.getConnection().getMetaData().getDatabaseProductName() + " (Pool optimizado)");
                System.out.println("🚀 API de Productos lista para recibir peticiones en http://localhost:8080");
            }
        } catch (Exception e) {
            System.err.println("❌ Error al conectar a la base de datos: " + e.getMessage());
            System.err.println("🔧 Verifica que el servidor esté disponible y las credenciales sean correctas");
            System.err.println("💡 Si el error persiste, revisa el límite de conexiones en Clever Cloud");
            throw e;
        }
    }
}
