package uniandes.edu.co.proyecto.modelo;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "proveedor")
public class Proveedor {

    @Id
    private Integer nit;
    
    private String nombre;
    private String direccion;
    private String nombrePersona;
    private Integer telefonoPersona;

    // FOREIGN KEYS

    @OneToMany(mappedBy = "proveedor")
    private List<OrdenCompra> ordenCompras;
    
    public Proveedor(Integer nit, String nombre, String direccion, String nombrePersona, Integer telefonoPersona) {
        
        this.nit = nit;
        this.nombre = nombre;
        this.direccion = direccion;
        this.nombrePersona = nombrePersona;
        this.telefonoPersona = telefonoPersona;

    }

    public Proveedor() {
        ;
    }

    public Integer getNit() {
        return nit;
    }

    public void setNit(Integer nit) {
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

    public String getNombrePersona() {
        return nombrePersona;
    }

    public void setNombrePersona(String nombrePersona) {
        this.nombrePersona = nombrePersona;
    }

    public Integer getTelefonoPersona() {
        return telefonoPersona;
    }

    public void setTelefonoPersona(Integer telefonoPersona) {
        this.telefonoPersona = telefonoPersona;
    }

    public List<OrdenCompra> getOrdenCompras() {
        return ordenCompras;
    }

    public void setOrdenCompras(List<OrdenCompra> ordenCompras) {
        this.ordenCompras = ordenCompras;
    }
    
}
