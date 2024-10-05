package www.udb.edu.sv.webproductos;

public class Proveedor {
    private int codProveedor;
    private String nomProveedor;

    public Proveedor(int codProveedor, String nomProveedor) {
        this.codProveedor = codProveedor;
        this.nomProveedor = nomProveedor;
    }

    public int getCodProveedor() {
        return codProveedor;
    }

    public String getNomProveedor() {
        return nomProveedor;
    }
}