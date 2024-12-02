package uniandes.edu.co.proyecto.modelo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.ToString;

@Document(collection = "categorias")
@ToString
public class Categoria {

    @Id
    @Field("_id")
    private int id;
    private String nombre;
    private String descripcion;
    @Field("caracteristicas_almacenamiento")
    private String caracteristicas_almacenamiento;

    public Categoria(int id, String nombre, String descripcion, String caracteristicas_almacenamiento) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.caracteristicas_almacenamiento = caracteristicas_almacenamiento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getCaracteristicas_almacenamiento() {
        return caracteristicas_almacenamiento;
    }

    public void setCaracteristicas_almacenamiento(String caracteristicas_almacenamiento) {
        this.caracteristicas_almacenamiento = caracteristicas_almacenamiento;
    }
    
    

    
}
