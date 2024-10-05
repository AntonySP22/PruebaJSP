package www.udb.edu.sv.webproductos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductoDAO {

    // Metodo para verificar si un código de producto ya existe
    public boolean codigoExiste(String codigo) throws SQLException {
        String query = "SELECT COUNT(*) FROM productos WHERE CodProducto = ?";
        try (Connection connection = Conexion.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, codigo);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0;
                }
            }
        }
        return false;
    }

    // Metodo para guardar un producto
    public void guardarProducto(Producto producto) throws SQLException {
        String sql = "INSERT INTO productos (CodProducto, NomProducto, Precio, Costo, FechaIngreso, CodProveedor, ImgProducto) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, producto.getCodigo());
            stmt.setString(2, producto.getNombre());
            stmt.setBigDecimal(3, producto.getPrecio());
            stmt.setBigDecimal(4, producto.getCosto());
            stmt.setDate(5, producto.getFechaIngreso());
            stmt.setInt(6, producto.getCodProveedor());
            stmt.setString(7, producto.getImgPath());
            stmt.executeUpdate();
        }
    }
}