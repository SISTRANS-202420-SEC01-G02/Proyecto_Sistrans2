package uniandes.edu.co.proyecto.modelo;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="producto")
public abstract class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer codigoBarras;
    
    private String nombre;
    private Integer costoBodega;
    private Integer costoUnidad;
    private String presentacion;
    private Integer cantidadPresentacion;
    private String unidadMedida;
    private Date fechaExpiracion;

    // FOREIGN KEYS

    @ManyToOne
    @JoinColumn(name = "categoria_codigo", referencedColumnName = "codigo")
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "empaque_id", referencedColumnName = "id")
    private Empaque empaque;

    public Producto(String nombre, Integer costoBodega, Integer costoUnidad, String presentacion, Integer cantidadPresentacion, String unidadMedida, Date fechaExpiracion){

        this.nombre = nombre;
        this.costoBodega = costoBodega;
        this.costoUnidad = costoUnidad;
        this.presentacion = presentacion;
        this.cantidadPresentacion = cantidadPresentacion;
        this.unidadMedida = unidadMedida;
        this.fechaExpiracion = fechaExpiracion;

    }

    public Producto(){
        ;
    }

    public Integer getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(Integer codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getCostoBodega() {
        return costoBodega;
    }

    public void setCostoBodega(Integer costoBodega) {
        this.costoBodega = costoBodega;
    }

    public Integer getCostoUnidad() {
        return costoUnidad;
    }

    public void setCostoUnidad(Integer costoUnidad) {
        this.costoUnidad = costoUnidad;
    }

    public String getPresentacion() {
        return presentacion;
    }

    public void setPresentacion(String presentacion) {
        this.presentacion = presentacion;
    }

    public Integer getCantidadPresentacion() {
        return cantidadPresentacion;
    }

    public void setCantidadPresentacion(Integer cantidadPresentacion) {
        this.cantidadPresentacion = cantidadPresentacion;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public Date getFechaExpiracion() {
        return fechaExpiracion;
    }

    public void setFechaExpiracion(Date fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Empaque getEmpaque() {
        return empaque;
    }

    public void setEmpaque(Empaque empaque) {
        this.empaque = empaque;
    }

}
