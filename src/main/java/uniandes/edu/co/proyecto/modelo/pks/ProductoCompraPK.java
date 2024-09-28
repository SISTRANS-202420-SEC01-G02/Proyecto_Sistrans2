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
    @JoinColumn(name = "producto_codigobarras", referencedColumnName = "codigoBarras")
    private Producto producto_codigobarras;

    @ManyToOne
    @JoinColumn(name = "ordencompra_id", referencedColumnName = "id")
    private OrdenCompra ordencompra_id;

    public ProductoCompraPK(Producto producto_codigobarras, OrdenCompra ordencompra_id){

        super();
        this.producto_codigobarras = producto_codigobarras;
        this.ordencompra_id = ordencompra_id;

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
    
}
