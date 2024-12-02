package uniandes.edu.co.proyecto.modelo;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

public class Bodega {

    @Id
    private int id;
    private String nombre;

    @Field("tamaño")
    private int tamanio;
    @Field("recepcion")
    private List<Recepcion> recepcion;
    private List<ProductoBodega> producto_bodegas;

    public Bodega(int id, String nombre, int tamanio) {
        this.id = id;
        this.nombre = nombre;
        this.tamanio = tamanio;
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
    public int getTamanio() {
        return tamanio;
    }
    public void setTamanio(int tamanio) {
        this.tamanio = tamanio;
    }
    public List<Recepcion> getRecepcion() {
        return recepcion;
    }
    public void setRecepcion(List<Recepcion> recepcion) {
        this.recepcion = recepcion;
    }
    public List<ProductoBodega> getProducto_bodegas() {
        return producto_bodegas;
    }
    public void setProducto_bodegas(List<ProductoBodega> producto_bodegas) {
        this.producto_bodegas = producto_bodegas;
    }

    

    
}
