package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import uniandes.edu.co.proyecto.modelo.pks.ProductoCompraPK;

@Entity
@Table(name = "productocompra")
public class ProductoCompra {

    @EmbeddedId
    private ProductoCompraPK pk;

    private Integer precioAcordado;
    private Integer cantidad;
    
    public ProductoCompra(Producto producto_codigobarras, OrdenCompra ordencompra_id, Integer precioAcordado, Integer cantidad) {

        this.pk = new ProductoCompraPK(producto_codigobarras, ordencompra_id);
        this.precioAcordado = precioAcordado;
        this.cantidad = cantidad;
    }

    public ProductoCompraPK getPk() {
        return pk;
    }
    public void setPk(ProductoCompraPK pk) {
        this.pk = pk;
    }
    public Integer getPrecioAcordado() {
        return precioAcordado;
    }
    public void setPrecioAcordado(Integer precioAcordado) {
        this.precioAcordado = precioAcordado;
    }
    public Integer getCantidad() {
        return cantidad;
    }
    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
    
}
