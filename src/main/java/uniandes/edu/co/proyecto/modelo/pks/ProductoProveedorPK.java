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
    @JoinColumn(name = "producto_codigobarras", referencedColumnName = "codigobarras")
    private Producto producto_codigobarras;

    @ManyToOne
    @JoinColumn(name = "proveedor_nit", referencedColumnName = "nit")
    private Proveedor proveedor_nit;

    // Constructor vacío requerido por Hibernate
    public ProductoProveedorPK() {
        super();
    }

    public ProductoProveedorPK(Producto producto_codigobarras, Proveedor proveedor_nit) {
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

    @Override
    public int hashCode() {
        return producto_codigobarras.hashCode() + proveedor_nit.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof ProductoProveedorPK)) return false;
        ProductoProveedorPK other = (ProductoProveedorPK) obj;
        return producto_codigobarras.equals(other.producto_codigobarras) && proveedor_nit.equals(other.proveedor_nit);
    }
}
