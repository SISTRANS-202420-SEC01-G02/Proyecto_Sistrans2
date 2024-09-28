package uniandes.edu.co.proyecto.modelo.pks;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import uniandes.edu.co.proyecto.modelo.Producto;
import uniandes.edu.co.proyecto.modelo.Proveedor;

@Embeddable
public class ProductoProveedorPK implements Serializable{

    @ManyToOne
    @JoinColumn(name = "produdcto_codigobarras", referencedColumnName = "codigoBarras")
    private Producto producto_codigobarras;

    @ManyToOne
    @JoinColumn(name = "proveedor_nit", referencedColumnName = "nit")
    private Proveedor proveedor_nit;

    public ProductoProveedorPK(Producto producto_codigobarras, Proveedor proveedor_nit) {

        super();
        this.producto_codigobarras = producto_codigobarras;
        this.proveedor_nit = proveedor_nit;

    }

    public Producto getProducto_codigobarras() {
        return producto_codigobarras;
    }

    public void setProducto_codigobarras(Producto producto_codigobarras) {
        this.producto_codigobarras = producto_codigobarras;
    }

    public Proveedor getProveedor_nit() {
        return proveedor_nit;
    }

    public void setProveedor_nit(Proveedor proveedor_nit) {
        this.proveedor_nit = proveedor_nit;
    }

}
