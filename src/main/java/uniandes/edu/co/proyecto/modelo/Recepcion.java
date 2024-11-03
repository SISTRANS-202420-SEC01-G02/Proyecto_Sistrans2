package uniandes.edu.co.proyecto.modelo;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;


@Entity
@Table(name = "recepcion")
public class Recepcion {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "recepcion_sequence_gen")
    @SequenceGenerator(name = "recepcion_sequence_gen", sequenceName = "recepcion_sequence", allocationSize = 1)    
    @Column(name = "id")
    private Integer id;

    @Column(name = "fecharecepcion")
    private Date fechaRecepcion;

    @Column(name = "sucursal_nombre")
    private String sucursalNombre;

    @Column(name = "proveedor_nombre")
    private String proveedorNombre;

    @Column(name = "bodega_nombre")
    private String bodegaNombre;

    // FOREIGN KEYS

    @ManyToOne
    @JoinColumn(name = "bodega_id", referencedColumnName = "id")
    private Bodega bodega;

    @OneToOne
    @JoinColumn(name = "ordencompra_id", referencedColumnName = "id")
    private OrdenCompra ordenCompra;

    public Recepcion(Date fecharecepcion, String sucursalNombre, String proveedorNombre, String bodegaNombre){

        this.fechaRecepcion = fecharecepcion;
        this.sucursalNombre = sucursalNombre;
        this.proveedorNombre = proveedorNombre;
        this.bodegaNombre = bodegaNombre;

    }

    public Recepcion(){
        ;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Date getFechaRecepcion() {
        return fechaRecepcion;
    }

    public void setFechaRecepcion(Date fechaRecepcion) {
        this.fechaRecepcion = fechaRecepcion;
    }

    public OrdenCompra getOrdenCompra() {
        return ordenCompra;
    }

    public void setOrdenCompra(OrdenCompra ordenCompra) {
        this.ordenCompra = ordenCompra;
    }

    public Bodega getBodega() {
        return bodega;
    }

    public void setBodega(Bodega bodega) {
        this.bodega = bodega;
    }

    public String getSucursalNombre() {
        return sucursalNombre;
    }

    public void setSucursalNombre(String sucursalNombre) {
        this.sucursalNombre = sucursalNombre;
    }

    public String getProveedorNombre() {
        return proveedorNombre;
    }

    public void setProveedorNombre(String proveedorNombre) {
        this.proveedorNombre = proveedorNombre;
    }

    public String getBodegaNombre() {
        return bodegaNombre;
    }

    public void setBodegaNombre(String bodegaNombre) {
        this.bodegaNombre = bodegaNombre;
    }
    
}
