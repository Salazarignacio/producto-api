#!/bin/bash

# Script para probar la optimización de conexiones en la API de productos
# Uso: ./test-connection-optimization.sh

echo "🧪 Iniciando pruebas de optimización de conexiones..."
echo "========================================"

# Verificar que el archivo .env exista
if [ ! -f ".env" ]; then
    echo "❌ Archivo .env no encontrado. Por favor créalo primero."
    exit 1
fi

# Cargar variables de entorno
source .env

echo "📋 Configuración actual:"
echo "   - URL: ${DB_URL}"
echo "   - Usuario: ${DB_USERNAME}"
echo "   - Driver: ${DB_DRIVER}"
echo ""

# Función para probar endpoints
test_endpoint() {
    local method=$1
    local url=$2
    local data=$3
    local description=$4
    
    echo "🔍 Probando: $description"
    echo "   Método: $method"
    echo "   URL: $url"
    
    if [ "$method" = "GET" ]; then
        response=$(curl -s -w "%{http_code}" "$url")
        http_code="${response: -3}"
        response_body="${response%???}"
    elif [ "$method" = "POST" ]; then
        response=$(curl -s -w "%{http_code}" -X POST \
            -H "Content-Type: application/json" \
            -d "$data" "$url")
        http_code="${response: -3}"
        response_body="${response%???}"
    fi
    
    if [ "$http_code" = "200" ]; then
        echo "   ✅ Éxito ($http_code)"
    elif [ "$http_code" = "201" ]; then
        echo "   ✅ Creado exitosamente ($http_code)"
    elif [ "$http_code" = "404" ]; then
        echo "   ⚠️  No encontrado ($http_code) - Esto puede ser normal si no hay datos"
    elif [ "$http_code" = "500" ]; then
        echo "   ❌ Error del servidor ($http_code)"
        echo "   Detalles: $response_body"
    else
        echo "   ⚠️  Respuesta inesperada ($http_code)"
    fi
    
    echo "   Respuesta: ${response_body:0:200}..."
    echo ""
}

# Verificar que la aplicación esté corriendo
echo "🚀 Verificando que la API esté corriendo..."
health_response=$(curl -s -w "%{http_code}" "http://localhost:8080/api/productos/test" 2>/dev/null)
health_code="${health_response: -3}"

if [ "$health_code" != "200" ]; then
    echo "❌ La API no está corriendo en http://localhost:8080"
    echo "💡 Por favor inicia la aplicación con: mvn spring-boot:run"
    exit 1
fi

echo "✅ API está corriendo correctamente"
echo ""

# Ejecutar pruebas de endpoints
echo "🧪 Ejecutando pruebas de endpoints..."
echo ""

# 1. Probar endpoint de prueba
test_endpoint "GET" "http://localhost:8080/api/productos/test" "" "Endpoint de prueba"

# 2. Probar obtener todos los productos
test_endpoint "GET" "http://localhost:8080/api/productos/all" "" "Obtener todos los productos"

# 3. Probar crear un producto
producto_test='{
  "articulo": "Producto de Prueba",
  "categoria": "Testing",
  "precio": 100.50,
  "stock": 10,
  "codigo": "TEST001"
}'
test_endpoint "POST" "http://localhost:8080/api/productos" "$producto_test" "Crear producto de prueba"

# 4. Probar buscar por código
test_endpoint "GET" "http://localhost:8080/api/productos/codigo/TEST001" "" "Buscar producto por código"

# 5. Probar obtener todos los productos nuevamente (para verificar)
test_endpoint "GET" "http://localhost:8080/api/productos/all" "" "Obtener todos los productos (post-creación)"

echo "========================================"
echo "🎯 Pruebas completadas"
echo ""

echo "📊 Resumen de optimizaciones implementadas:"
echo "   ✅ Pool de conexiones limitado a 2 (máximo para Clever Cloud)"
echo "   ✅ Timeout de conexión ajustado a 20s"
echo "   ✅ Validación y detección de leaks configuradas"
echo "   ✅ Todas las conexiones usan try-with-resources"
echo "   ✅ Logging de HikariPool activado para monitoreo"
echo ""

echo "🔍 Si experimentas problemas:"
echo "   - Revisa los logs de la aplicación para ver el estado del pool"
echo "   - Verifica que no superes el límite de 5 conexiones simultáneas"
echo "   - Los logs mostrarán cuando se crean/destroyan conexiones"
echo ""

echo "💡 Para habilitar debugging completo:"
echo "   mvn spring-boot:run -Dspring.profiles.active=debug"