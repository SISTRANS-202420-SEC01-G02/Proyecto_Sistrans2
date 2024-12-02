package uniandes.edu.co.proyecto.modelo;

public class ProductoBodega {


    private String productos; 
    private int costoPromedio; 
    private int cantidadActual; 
    private int nivelReorden;

    public ProductoBodega(String productos, int costoPromedio, int cantidadActual, int nivelReorden) {
        this.productos = productos;
        this.costoPromedio = costoPromedio;
        this.cantidadActual = cantidadActual;
        this.nivelReorden = nivelReorden;
    }

    public ProductoBodega() {}

    public String getProductos() {
        return productos;
    }

    public void setProductos(String productos) {
        this.productos = productos;
    }

    public double getCostoPromedio() {
        return costoPromedio;
    }

    public void setCostoPromedio(int costoPromedio) {
        this.costoPromedio = costoPromedio;
    }

    public int getCantidadActual() {
        return cantidadActual;
    }

    public void setCantidadActual(int cantidadActual) {
        this.cantidadActual = cantidadActual;
    }

    public int getNivelReorden() {
        return nivelReorden;
    }

    public void setNivelReorden(int nivelReorden) {
        this.nivelReorden = nivelReorden;
    }

}
