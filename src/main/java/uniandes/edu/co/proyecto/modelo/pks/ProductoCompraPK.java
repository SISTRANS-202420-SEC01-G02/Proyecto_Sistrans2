package uniandes.edu.co.proyecto.modelo.pks;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import uniandes.edu.co.proyecto.modelo.OrdenCompra;
import uniandes.edu.co.proyecto.modelo.Producto;

@Embeddable
public class ProductoCompraPK implements Serializable{

    @ManyToOne
    @JoinColumn(name = "producto_codigobarras", referencedColumnName = "codigobarras")
    private Producto producto_codigobarras;

    @ManyToOne
    @JoinColumn(name = "ordencompra_id", referencedColumnName = "id")
    private OrdenCompra ordencompra_id;

    public ProductoCompraPK(Producto producto_codigobarras, OrdenCompra ordencompra_id){

        super();
        this.producto_codigobarras = producto_codigobarras;
        this.ordencompra_id = ordencompra_id;

    }

    public ProductoCompraPK() {
        super();
    }

    public Producto getProducto_codigobarras() {
        return producto_codigobarras;
    }

    public void setProducto_codigobarras(Producto producto_codigobarras) {
        this.producto_codigobarras = producto_codigobarras;
    }

    public OrdenCompra getOrdencompra_id() {
        return ordencompra_id;
    }

    public void setOrdencompra_id(OrdenCompra ordencompra_id) {
        this.ordencompra_id = ordencompra_id;
    }

    @Override
    public int hashCode() {
        return producto_codigobarras.hashCode() + ordencompra_id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof ProductoCompraPK)) return false;
        ProductoCompraPK other = (ProductoCompraPK) obj;
        return producto_codigobarras.equals(other.producto_codigobarras) && ordencompra_id.equals(other.ordencompra_id);
    }
    
}
