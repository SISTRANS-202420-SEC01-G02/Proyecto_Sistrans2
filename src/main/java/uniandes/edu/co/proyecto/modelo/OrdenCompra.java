package uniandes.edu.co.proyecto.modelo;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "ordencompra")
public class OrdenCompra {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    private Date fechaEsperada;
    private Date fechaCreacion;
    private Estado estado;

    // FOREIGN KEYS

    @ManyToOne
    @JoinColumn(name = "sucursal_id", referencedColumnName = "id")
    private Sucursal sucursal;

    @ManyToOne
    @JoinColumn(name = "proveedor_nit", referencedColumnName = "nit")
    private Proveedor proveedor_nit;

    @OneToOne(optional = true)
    private Recepcion recepcion;

    public OrdenCompra(Date fechaEsperada, Date fechaCreacion, Estado estado){

        this.fechaEsperada = fechaEsperada;
        this.fechaCreacion = fechaCreacion;
        this.estado = estado;

    }

    public OrdenCompra(){
        ;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFechaEsperada() {
        return fechaEsperada;
    }

    public void setFechaEsperada(Date fechaEsperada) {
        this.fechaEsperada = fechaEsperada;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    public Proveedor getProveedor_nit() {
        return proveedor_nit;
    }

    public void setProveedor_nit(Proveedor proveedor_nit) {
        this.proveedor_nit = proveedor_nit;
    }

    public Recepcion getRecepcion() {
        return recepcion;
    }

    public void setRecepcion(Recepcion recepcion) {
        this.recepcion = recepcion;
    }
    
    
}
