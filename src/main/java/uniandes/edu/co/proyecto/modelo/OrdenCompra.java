package uniandes.edu.co.proyecto.modelo;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.ToString;

@Document(collection = "ordenescompra")
@ToString
public class OrdenCompra {

    @Id
    private int id;
    private String fecha_creacion;
    private int sucursal_id;
    private int proveedor_id;
    private String fecha_esperada;
    private String estado;
    private int cantidad;
    private List<Integer> productos;
    
    public OrdenCompra(int id, String fecha_creacion, int sucursal_id, int proveedor_id, String fecha_esperada,
            String estado, int cantidad) {
        this.id = id;
        this.fecha_creacion = fecha_creacion;
        this.sucursal_id = sucursal_id;
        this.proveedor_id = proveedor_id;
        this.fecha_esperada = fecha_esperada;
        this.estado = estado;
        this.cantidad = cantidad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(String fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public int getSucursal_id() {
        return sucursal_id;
    }

    public void setSucursal_id(int sucursal_id) {
        this.sucursal_id = sucursal_id;
    }

    public int getProveedor_id() {
        return proveedor_id;
    }

    public void setProveedor_id(int proveedor_id) {
        this.proveedor_id = proveedor_id;
    }

    public String getFecha_esperada() {
        return fecha_esperada;
    }

    public void setFecha_esperada(String fecha_esperada) {
        this.fecha_esperada = fecha_esperada;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public List<Integer> getProductos() {
        return productos;
    }

    public void setProductos(List<Integer> productos) {
        this.productos = productos;
    }

    
    
}
