package uniandes.edu.co.proyecto.modelo.pks;

import java.io.Serializable;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import uniandes.edu.co.proyecto.modelo.Bodega;
import uniandes.edu.co.proyecto.modelo.Producto;

@Embeddable
public class ProductoBodegaPK implements Serializable {
    
    @ManyToOne
    @JoinColumn(name = "producto_codigobarras", referencedColumnName = "codigobarras")
    private Producto producto_codigobarras;

    @ManyToOne
    @JoinColumn(name = "bodega_id", referencedColumnName = "id")
    private Bodega bodega_id;

    // Constructor vacío requerido por Hibernate
    public ProductoBodegaPK() {
        super();
    }

    public ProductoBodegaPK(Producto producto_codigobarras, Bodega bodega_id) {
        this.producto_codigobarras = producto_codigobarras;
        this.bodega_id = bodega_id;
    }

    public Producto getProducto_codigobarras() {
        return producto_codigobarras;
    }

    public void setProducto_codigobarras(Producto producto_codigobarras) {
        this.producto_codigobarras = producto_codigobarras;
    }

    public Bodega getBodega_id() {
        return bodega_id;
    }

    public void setBodega_id(Bodega bodega_id) {
        this.bodega_id = bodega_id;
    }

    @Override
    public int hashCode() {
        return producto_codigobarras.hashCode() + bodega_id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof ProductoBodegaPK)) return false;
        ProductoBodegaPK other = (ProductoBodegaPK) obj;
        return producto_codigobarras.equals(other.producto_codigobarras) && bodega_id.equals(other.bodega_id);
    }
}
