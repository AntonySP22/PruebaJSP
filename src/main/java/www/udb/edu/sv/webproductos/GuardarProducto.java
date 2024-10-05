package www.udb.edu.sv.webproductos;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.file.Paths;
import java.sql.Date;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet(name = "GuardarProducto", urlPatterns = "/guardarProducto")
@MultipartConfig
public class GuardarProducto extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener los parámetros del formulario
        String codigo = request.getParameter("codigo");
        String nombre = request.getParameter("nombre");
        BigDecimal costo = new BigDecimal(request.getParameter("costo"));
        BigDecimal precio = new BigDecimal(request.getParameter("precio"));
        Date fechaIngreso = Date.valueOf(request.getParameter("fecha"));
        int codProveedor = Integer.parseInt(request.getParameter("proveedor"));

        // Manejar la carga del archivo de imagen
        Part filePart = request.getPart("imagen");
        // Obtener la extensión del archivo original
        String fileExtension = Paths.get(filePart.getSubmittedFileName()).toString().substring(filePart.getSubmittedFileName().lastIndexOf('.'));
        // Cambiar el nombre del archivo de imagen al código del producto
        String fileName = codigo + fileExtension;
        String uploadPath = getServletContext().getRealPath("") + File.separator + "uploads";
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) uploadDir.mkdir();

        // Guardar el archivo de imagen en el directorio especificado
        File file = new File(uploadPath, fileName);
        try (FileOutputStream fos = new FileOutputStream(file);
             InputStream is = filePart.getInputStream()) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = is.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
            }
        }

        // Crear una instancia de ProductoDAO para interactuar con la base de datos
        ProductoDAO productoDAO = new ProductoDAO();
        try {
            // Verificar si el código del producto ya existe
            if (productoDAO.codigoExiste(codigo)) {
                response.sendRedirect("index.jsp?error=codigoExiste");
                return;
            }

            // Crear un objeto Producto y guardarlo en la base de datos
            Producto producto = new Producto(codigo, nombre, precio, costo, fechaIngreso, codProveedor, fileName);
            productoDAO.guardarProducto(producto);
            response.sendRedirect("index.jsp?success=true");
        } catch (SQLException e) {
            throw new ServletException("Error al guardar el producto", e);
        }
    }
}