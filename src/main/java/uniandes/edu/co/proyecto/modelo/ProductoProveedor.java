package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import uniandes.edu.co.proyecto.modelo.pks.ProductoProveedorPK;

@Entity
@Table(name = "productoproveedor")
public class ProductoProveedor {

    @EmbeddedId
    private ProductoProveedorPK pk;

    public ProductoProveedor(Producto producto_codigobarras, Proveedor proveedor_nit) {

        this.pk = new ProductoProveedorPK(producto_codigobarras, proveedor_nit);

    }

    public ProductoProveedorPK getPk() {
        return pk;
    }

    public void setPk(ProductoProveedorPK pk) {
        this.pk = pk;
    }
    
}
