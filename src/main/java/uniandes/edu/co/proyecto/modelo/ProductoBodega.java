package uniandes.edu.co.proyecto.modelo;
import uniandes.edu.co.proyecto.modelo.pks.ProductoBodegaPK;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "productobodega")
public class ProductoBodega{

    @EmbeddedId 
    private ProductoBodegaPK pk;

    private Integer costoPromedio;
    private Integer cantidadProducto;
    private Integer nivelReorden;
    private Integer capacidadProducto;

    public ProductoBodega(Producto producto_codigobarras, Bodega bodega_id, Integer costoPromedio, Integer cantidadProducto, Integer nivelReorden, Integer capacidadProducto) {
        
        this.pk = new ProductoBodegaPK(producto_codigobarras, bodega_id);
        this.costoPromedio = costoPromedio;
        this.cantidadProducto = cantidadProducto;
        this.nivelReorden = nivelReorden;
        this.capacidadProducto = capacidadProducto;

    }

    public ProductoBodega() {
        ;
    }

    public ProductoBodegaPK getPk() {
        return pk;
    }

    public void setPk(ProductoBodegaPK pk) {
        this.pk = pk;
    }

    public Integer getCostoPromedio() {
        return costoPromedio;
    }

    public void setCostoPromedio(Integer costoPromedio) {
        this.costoPromedio = costoPromedio;
    }

    public Integer getCantidadProducto() {
        return cantidadProducto;
    }

    public void setCantidadProducto(Integer cantidadProducto) {
        this.cantidadProducto = cantidadProducto;
    }

    public Integer getNivelReorden() {
        return nivelReorden;
    }

    public void setNivelReorden(Integer nivelReorden) {
        this.nivelReorden = nivelReorden;
    }

    public Integer getCapacidadProducto() {
        return capacidadProducto;
    }

    public void setCapacidadProducto(Integer capacidadProducto) {
        this.capacidadProducto = capacidadProducto;
    }
    
}