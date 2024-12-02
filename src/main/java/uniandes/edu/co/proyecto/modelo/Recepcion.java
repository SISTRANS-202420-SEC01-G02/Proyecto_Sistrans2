package uniandes.edu.co.proyecto.modelo;

import java.time.LocalDate;

public class Recepcion {

    private String id_ordencompra;
    private LocalDate fechaIngreso;

    public Recepcion(String id_ordencompra, LocalDate fechaIngreso) {
        this.id_ordencompra = id_ordencompra;
        this.fechaIngreso = fechaIngreso;
    }

    public Recepcion() {}

    public String getIdOrdenCompra() {
        return id_ordencompra;
    }

    public void setIdOrdenCompra(String idOrdenCompra) {
        this.id_ordencompra = idOrdenCompra;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

}
