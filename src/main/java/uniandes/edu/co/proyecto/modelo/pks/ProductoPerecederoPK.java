package uniandes.edu.co.proyecto.modelo.pks;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import uniandes.edu.co.proyecto.modelo.Producto;

@Embeddable
public class ProductoPerecederoPK implements Serializable{

    @OneToOne
    @JoinColumn(name = "producto_codigobarras", referencedColumnName = "codigobarras")
    private Producto producto_codigobarras;

    public ProductoPerecederoPK(Producto producto_codigobarras) {
        super();
        this.producto_codigobarras = producto_codigobarras;
    }

    public ProductoPerecederoPK() {
        super();
    }

    public Producto getProducto_codigobarras() {
        return producto_codigobarras;
    }

    public void setProducto_codigobarras(Producto producto_codigobarras) {
        this.producto_codigobarras = producto_codigobarras;
    }   
    
}
