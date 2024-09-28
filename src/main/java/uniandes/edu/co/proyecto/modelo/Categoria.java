package uniandes.edu.co.proyecto.modelo;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table(name = "categoria")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer codigo;
    
    private String nombre;
    private String descripcion;
    private String caracteristicasAlmacena;

    // FOREIGN KEYS
    
    @OneToMany(mappedBy = "categoria")
    private List<Producto> productos;

    public Categoria(String nombre, String descripcion, String caracteristicasAlmacena, Date fechaExpiracion){

        this.nombre = nombre;
        this.descripcion = descripcion;
        this.caracteristicasAlmacena = caracteristicasAlmacena;

    }

    public Categoria(){
        ;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCaracteristicasAlmacena() {
        return caracteristicasAlmacena;
    }

    public void setCaracteristicasAlmacena(String caracteristicasAlmacena) {
        this.caracteristicasAlmacena = caracteristicasAlmacena;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }
    
}
