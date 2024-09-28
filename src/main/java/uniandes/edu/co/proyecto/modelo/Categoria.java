package uniandes.edu.co.proyecto.modelo;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.Column;
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
    @Column(name = "codigo")
    private Integer codigo;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "caracteristicasalmacenaje")
    private String caracteristicasAlmacenaje;

    // FOREIGN KEYS
    
    @OneToMany(mappedBy = "categoria")
    private List<Producto> productos;

    public Categoria(String nombre, String descripcion, String caracteristicasAlmacena, Date fechaExpiracion){

        this.nombre = nombre;
        this.descripcion = descripcion;
        this.caracteristicasAlmacenaje = caracteristicasAlmacena;

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

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public String getCaracteristicasAlmacenaje() {
        return caracteristicasAlmacenaje;
    }

    public void setCaracteristicasAlmacenaje(String caracteristicasAlmacenaje) {
        this.caracteristicasAlmacenaje = caracteristicasAlmacenaje;
    }
    
}
