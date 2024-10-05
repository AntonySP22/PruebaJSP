package www.udb.edu.sv.webproductos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProveedorDAO {

    // Metodo para obtener los proveedores
    public List<Proveedor> obtenerProveedores() throws SQLException {
        List<Proveedor> proveedores = new ArrayList<>();
        String sql = "SELECT CodProveedor, NomProveedor FROM proveedores";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int codProveedor = rs.getInt("CodProveedor");
                String nomProveedor = rs.getString("NomProveedor");
                proveedores.add(new Proveedor(codProveedor, nomProveedor));
            }
        }
        return proveedores;
    }
}