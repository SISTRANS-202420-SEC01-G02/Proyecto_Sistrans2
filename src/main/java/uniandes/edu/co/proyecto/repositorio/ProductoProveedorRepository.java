package uniandes.edu.co.proyecto.repositorio;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.ProductoProveedor;
import uniandes.edu.co.proyecto.modelo.pks.ProductoProveedorPK;

@Repository
public interface ProductoProveedorRepository extends JpaRepository<ProductoProveedor, ProductoProveedorPK> {

    @Query(value = "SELECT * FROM productoproveedor", nativeQuery = true)
    Collection<ProductoProveedor> darProductosProveedores();
    
    @Query(value = "SELECT * FROM productoproveedor WHERE producto_codigobarras = :productoCodigo AND proveedor_nit = :proveedorNit", nativeQuery = true)
    ProductoProveedor darProductoProveedor(@Param("productoCodigo") int productoCodigo, @Param("proveedorNit") int proveedorNit);
    
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO productoproveedor (producto_codigobarras, proveedor_nit) VALUES(:productoCodigo, :proveedorNit)", nativeQuery = true)
    void insertarProductoProveedor(@Param("productoCodigo") int productoCodigo, @Param("proveedorNit") int proveedorNit);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM productoproveedor WHERE producto_codigobarras=:productoCodigo AND proveedor_nit=:proveedorNit", nativeQuery = true)
    void eliminarProductoProveedor(@Param("productoCodigo") int productoCodigo, @Param("proveedorNit") int proveedorNit);

}
