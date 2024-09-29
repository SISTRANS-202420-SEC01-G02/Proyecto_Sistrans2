package uniandes.edu.co.proyecto.modelo;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;


@Entity
@Table(name = "bodega")
public class Bodega {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bodega_sequence_gen")
    @SequenceGenerator(name = "bodega_sequence_gen", sequenceName = "bodega_sequence", allocationSize = 1)
    @Column(name = "id")
    private Integer id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "tamanio")
    private Integer tamanio;

    // FOREIGN KEYS

    @ManyToOne
    @JoinColumn(name = "sucursal_id", referencedColumnName = "id")
    private Sucursal sucursal;

    @OneToMany(mappedBy = "bodega")
    private List<Recepcion> recepciones;

    public Bodega(String nombre, Integer tamanio){
        this.nombre = nombre;
        this.tamanio = tamanio;

    }

    public Bodega(){
        ;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getTamanio() {
        return tamanio;
    }

    public void setTamanio(Integer tamanio) {
        this.tamanio = tamanio;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    public List<Recepcion> getRecepciones() {
        return recepciones;
    }

    public void setRecepciones(List<Recepcion> recepciones) {
        this.recepciones = recepciones;
    }

    
    
}
