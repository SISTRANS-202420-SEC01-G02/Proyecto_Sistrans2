package uniandes.edu.co.proyecto.modelo;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import uniandes.edu.co.proyecto.modelo.pks.ProductoPerecederoPK;


@Entity
@Table(name = "productoperecedero")
public class ProductoPerecedero {

    @EmbeddedId
    private ProductoPerecederoPK pk;

    @Column(name = "fechavencimiento")
    private Date fechaVencimiento;

    public ProductoPerecedero(Producto producto_codigobarras, Date fechaVencimiento) {
        this.pk = new ProductoPerecederoPK(producto_codigobarras);
        this.fechaVencimiento = fechaVencimiento;
    }

    public ProductoPerecedero() {
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public ProductoPerecederoPK getPk() {
        return pk;
    }

    public void setPk(ProductoPerecederoPK pk) {
        this.pk = pk;
    }
    
}
