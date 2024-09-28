package uniandes.edu.co.proyecto.modelo;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "empaque")
public class Empaque {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Integer volumenEmpaque;
    private Integer pesoEmpaque;

    // FOREIGN KEYS

    @OneToMany(mappedBy = "empaque")
    private List<Producto> productos;

    public Empaque(Integer volumenEmpaque, Integer pesoEmpaque) {

        this.volumenEmpaque = volumenEmpaque;
        this.pesoEmpaque = pesoEmpaque;

    }

    public Empaque() {
        ;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVolumenEmpaque() {
        return volumenEmpaque;
    }

    public void setVolumenEmpaque(Integer volumenEmpaque) {
        this.volumenEmpaque = volumenEmpaque;
    }

    public Integer getPesoEmpaque() {
        return pesoEmpaque;
    }

    public void setPesoEmpaque(Integer pesoEmpaque) {
        this.pesoEmpaque = pesoEmpaque;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }
    
}
