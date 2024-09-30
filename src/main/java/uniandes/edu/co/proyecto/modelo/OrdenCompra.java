package uniandes.edu.co.proyecto.modelo;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "ordencompra")
public class OrdenCompra {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ordencompra_sequence_gen")
    @SequenceGenerator(name = "ordencompra_sequence_gen", sequenceName = "ordencompra_sequence", allocationSize = 1)
    @Column(name = "id")
    private Integer id;
    @Column(name = "fechaesperada")
    private Date fechaEsperada;
    @Column(name = "fechacreacion", updatable = false)
    private Date fechaCreacion;
    @Column(name = "estado")
    private String estado;

    // FOREIGN KEYS

    @ManyToOne
    @JoinColumn(name = "sucursal_id", referencedColumnName = "id")
    private Sucursal sucursal;

    @ManyToOne
    @JoinColumn(name = "proveedor_nit", referencedColumnName = "nit")
    private Proveedor proveedor;

    public OrdenCompra(Date fechaEsperada, Date fechaCreacion, String estado){

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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }
    
    
}
