package uniandes.edu.co.proyecto.modelo;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.ToString;

@Document(collection = "sucursales")
@ToString
public class Sucursal {

    @Id
    private int id;
    private String nombre;

    @Field("ciudad")
    private String ciudad;
    private String direccion;
    private Long telefono;

    @Field("ordenes_compra")
    private List<Integer> ordenes_compra;

    @Field("bodegas")
    private List<Bodega> bodegas;
    public Sucursal(int id, String nombre, String ciudad, String direccion, Long telefono) {
        this.id = id;
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.direccion = direccion;
        this.telefono = telefono;
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
    public String getCiudad() {
        return ciudad;
    }
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
    public String getDireccion() {
        return direccion;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public Long getTelefono() {
        return telefono;
    }
    public void setTelefono(Long telefono) {
        this.telefono = telefono;
    }
    public List<Integer> getOrdenes_compra() {
        return ordenes_compra;
    }
    public void setOrdenes_compra(List<Integer> ordenes_compra) {
        this.ordenes_compra = ordenes_compra;
    }
    public List<Bodega> getBodegas() {
        return bodegas;
    }
    public void setBodegas(List<Bodega> bodegas) {
        this.bodegas = bodegas;
    }

    
    
}
