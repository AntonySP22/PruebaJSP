package www.udb.edu.sv.webproductos;

import java.math.BigDecimal;
import java.sql.Date;

public class Producto {
    private String codigo;
    private String nombre;
    private BigDecimal precio;
    private BigDecimal costo;
    private Date fechaIngreso;
    private int codProveedor;
    private String imgPath;

    public Producto(String codigo, String nombre, BigDecimal precio, BigDecimal costo, Date fechaIngreso, int codProveedor, String imgPath) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.costo = costo;
        this.fechaIngreso = fechaIngreso;
        this.codProveedor = codProveedor;
        this.imgPath = imgPath;
    }

    // Getters and setters

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public BigDecimal getCosto() {
        return costo;
    }

    public void setCosto(BigDecimal costo) {
        this.costo = costo;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public int getCodProveedor() {
        return codProveedor;
    }

    public void setCodProveedor(int codProveedor) {
        this.codProveedor = codProveedor;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }
}