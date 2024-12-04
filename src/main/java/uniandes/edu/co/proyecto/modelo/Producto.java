package uniandes.edu.co.proyecto.modelo;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.ToString;

@Document(collection = "productos")
@ToString
public class Producto {
    @Id
    private int id;
    private String nombre;
    private int costo_bodega;
    private int costo_unitario;
    private String presentacion;
    private String unidad_medida;
    private int volumen_empaque;
    private int peso_empaque;
    
    @JsonIgnoreProperties({"nombre", "descripcion", "caracteristicas_almacenamiento"})
    private int categoria;
    private Date fecha_expiracion;
    
    @Field("codigo_barras")
    private String codigoBarras;
    public Producto(int id, String nombre, int costo_bodega, int costo_unitario, String presentacion,
            String unidad_medida, int volumen_empaque, int peso_empaque, int categoria, Date fecha_expiracion,
            String codigo_barras) {
        this.id = id;
        this.nombre = nombre;
        this.costo_bodega = costo_bodega;
        this.costo_unitario = costo_unitario;
        this.presentacion = presentacion;
        this.unidad_medida = unidad_medida;
        this.volumen_empaque = volumen_empaque;
        this.peso_empaque = peso_empaque;
        this.categoria = categoria;
        this.fecha_expiracion = fecha_expiracion;
        this.codigoBarras = codigo_barras;
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
    public int getCosto_bodega() {
        return costo_bodega;
    }
    public void setCosto_bodega(int costo_bodega) {
        this.costo_bodega = costo_bodega;
    }
    public int getCosto_unitario() {
        return costo_unitario;
    }
    public void setCosto_unitario(int costo_unitario) {
        this.costo_unitario = costo_unitario;
    }
    public String getPresentacion() {
        return presentacion;
    }
    public void setPresentacion(String presentacion) {
        this.presentacion = presentacion;
    }
    public String getUnidad_medida() {
        return unidad_medida;
    }
    public void setUnidad_medida(String unidad_medida) {
        this.unidad_medida = unidad_medida;
    }
    public int getVolumen_empaque() {
        return volumen_empaque;
    }
    public void setVolumen_empaque(int volumen_empaque) {
        this.volumen_empaque = volumen_empaque;
    }
    public int getPeso_empaque() {
        return peso_empaque;
    }
    public void setPeso_empaque(int peso_empaque) {
        this.peso_empaque = peso_empaque;
    }
    public int getCategoria() {
        return categoria;
    }
    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }
    public Date getFecha_expiracion() {
        return fecha_expiracion;
    }
    public void setFecha_expiracion(Date fecha_expiracion) {
        this.fecha_expiracion = fecha_expiracion;
    }
    public String getCodigo_barras() {
        return codigoBarras;
    }
    public void setCodigo_barras(String codigo_barras) {
        this.codigoBarras = codigo_barras;
    }

    
}
