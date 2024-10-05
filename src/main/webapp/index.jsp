<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="www.udb.edu.sv.webproductos.ProveedorDAO, www.udb.edu.sv.webproductos.Proveedor, java.util.List" %>
<%
    ProveedorDAO proveedorDAO = new ProveedorDAO();
    List<Proveedor> proveedores = proveedorDAO.obtenerProveedores();
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ingreso de Producto</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container py-5">
    <!-- Formulario de ingreso de producto -->
    <div class="card shadow">
        <div class="card-body">
            <h2 class="card-title text-center mb-4">Ingreso de Producto</h2>
            <form action="guardarProducto" method="post" enctype="multipart/form-data"
                  onsubmit="showAlertAndSubmit(event)">
                <div class="row mb-3">
                    <div class="col-md-6">
                        <label for="codigo" class="form-label">Código</label>
                        <input type="text" class="form-control" id="codigo" name="codigo"
                               placeholder="ZA000" required>
                    </div>
                    <div class="col-md-6">
                        <label for="nombre" class="form-label">Nombre</label>
                        <input type="text" class="form-control" id="nombre" name="nombre"
                               placeholder="Ingrese el nombre" required>
                    </div>
                </div>
                <div class="row mb-3">
                    <div class="col-md-6">
                        <label for="costo" class="form-label">Costo</label>
                        <input type="number" class="form-control" id="costo" name="costo" placeholder="000.00"
                              step="0.01" min="0" required>
                    </div>
                    <div class="col-md-6">
                        <label for="precio" class="form-label">Precio</label>
                        <input type="number" class="form-control" id="precio" name="precio"
                               placeholder="000.00" step="0.01" min="0" required>
                    </div>
                </div>
                <div class="mb-3">
                    <label for="fecha" class="form-label">Fecha de Ingreso</label>
                    <input type="date" class="form-control" id="fecha" name="fecha" required>
                </div>
                <!-- Selección de proveedor -->
                <div class="mb-3">
                    <label for="proveedor" class="form-label">Código Proveedor</label>
                    <select class="form-select" id="proveedor" name="proveedor" required>
                        <option value="">Seleccione un proveedor</option>
                        <c:forEach var="proveedor" items="<%= proveedores %>">
                            <option value="${proveedor.codProveedor}">${proveedor.nomProveedor}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="mb-3">
                    <label for="imagen" class="form-label">Imagen del Producto</label>
                    <input type="file" class="form-control" id="imagen" name="imagen" accept="image/*"
                           onchange="previewImage(event)" required>
                </div>
                <div id="imagePreview" class="mb-3 text-center d-none">
                    <img id="preview" src="#" alt="Vista previa" class="img-fluid" style="max-height: 200px;">
                </div>
                <div class="d-grid">
                    <button type="submit" class="btn btn-primary btn-lg">Guardar Producto</button>
                </div>
            </form>
        </div>
    </div>
</div>
<!-- Vista previa de la imagen -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script>
    function previewImage(event) {
        var reader = new FileReader();
        reader.onload = function () {
            var output = document.getElementById('preview');
            output.src = reader.result;
            document.getElementById('imagePreview').classList.remove('d-none');
        };
        reader.readAsDataURL(event.target.files[0]);
    }
</script>
<script>
    window.onload = function () {
        const urlParams = new URLSearchParams(window.location.search);
        if (urlParams.has('success')) {
            alert('Producto guardado correctamente');
            window.location.href = window.location.pathname;
        } else if (urlParams.has('error') && urlParams.get('error') === 'codigoExiste') {
            alert('El código de producto ya existe. Por favor, ingrese un código diferente.');
        }
    }
</script>
</body>
</html>