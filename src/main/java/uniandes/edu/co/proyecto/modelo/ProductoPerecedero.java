package uniandes.edu.co.proyecto.modelo;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "productoperecedero")
@PrimaryKeyJoinColumn(name = "productoperecedero_id", referencedColumnName = "codigoBarras")
public class ProductoPerecedero extends Producto{

    private Date fechaVencimiento;

    public ProductoPerecedero(String nombre, Integer costoBodega, Integer costoUnidad, String presentacion,
            Integer cantidadPresentacion, String unidadMedida, Date fechaExpiracion, Date fechaVencimiento) {
        super(nombre, costoBodega, costoUnidad, presentacion, cantidadPresentacion, unidadMedida, fechaExpiracion);
        this.fechaVencimiento = fechaVencimiento;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

}
