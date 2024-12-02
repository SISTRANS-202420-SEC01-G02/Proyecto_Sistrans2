package uniandes.edu.co.proyecto.modelo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.ToString;

@Document(collection = "proveedores")
@ToString
public class Proveedor {

    @Id
    private int id;
    private Long nit;
    private String nombre;
    private String direccion;
    private String persona_contacto;
    private Long telefono;

    public Proveedor(int id, Long nit, String nombre, String direccion, String persona_contacto, Long telefono) {
        this.id = id;
        this.nit = nit;
        this.nombre = nombre;
        this.direccion = direccion;
        this.persona_contacto = persona_contacto;
        this.telefono = telefono;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Long getNit() {
        return nit;
    }

    public void setNit(Long nit) {
        this.nit = nit;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getPersona_contacto() {
        return persona_contacto;
    }

    public void setPersona_contacto(String persona_contacto) {
        this.persona_contacto = persona_contacto;
    }

    public Long getTelefono() {
        return telefono;
    }

    public void setTelefono(Long telefono) {
        this.telefono = telefono;
    }

    

    
    
}
