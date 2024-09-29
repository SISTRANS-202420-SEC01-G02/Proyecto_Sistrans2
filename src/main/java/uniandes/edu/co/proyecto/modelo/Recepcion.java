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
    @SequenceGenerator(name = "recepcion_sequence_gen", sequenceName = "recepcion_sequence", allocationSize = 1)    @Column(name = "id")
    private Integer id;
    @Column(name = "fecharecepcion")
    private Date fechaRecepcion;

    // FOREIGN KEYS

    @ManyToOne
    @JoinColumn(name = "bodega_id", referencedColumnName = "id")
    private Bodega bodega;

    @OneToOne
    @JoinColumn(name = "ordencompra_id", referencedColumnName = "id")
    private OrdenCompra ordenCompra;

    public Recepcion(Date fecharecepcion){

        this.fechaRecepcion = fecharecepcion;

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
    
    
}
