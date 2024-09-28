package uniandes.edu.co.proyecto.repositorio;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.ProductoCompra;
import uniandes.edu.co.proyecto.modelo.pks.ProductoCompraPK;

@Repository
public interface ProductoCompraRepository extends JpaRepository<ProductoCompra, ProductoCompraPK> {

    @Query(value = "SELECT * FROM productocompra", nativeQuery = true)
    Collection<ProductoCompra> darProductosCompra();
    
    @Query(value = "SELECT * FROM productocompra WHERE producto_codigobarras = :productoCodigo AND ordencompra_id = :ordenCompraId", nativeQuery = true)
    ProductoCompra darProductoCompra(@Param("productoCodigo") int productoCodigo, @Param("ordenCompraId") int ordenCompraId);
    
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO productocompra (producto_codigobarras, ordencompra_id, precioacordado, cantidad) VALUES(:productoCodigo, :ordenCompraId, :precioAcordado, :cantidad)", nativeQuery = true)
    void insertarProductoCompra(@Param("productoCodigo") int productoCodigo, @Param("ordenCompraId") int ordenCompraId, @Param("precioAcordado") int precioAcordado, @Param("cantidad") int cantidad);

    @Modifying
    @Transactional
    @Query(value = "UPDATE productocompra SET precioacordado=:precioAcordado, cantidad=:cantidad WHERE producto_codigobarras=:productoCodigo AND ordencompra_id=:ordenCompraId", nativeQuery = true)
    void actualizarProductoCompra(@Param("productoCodigo") int productoCodigo, @Param("ordenCompraId") int ordenCompraId, @Param("precioAcordado") int precioAcordado, @Param("cantidad") int cantidad);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM productocompra WHERE producto_codigobarras=:productoCodigo AND ordencompra_id=:ordenCompraId", nativeQuery = true)
    void eliminarProductoCompra(@Param("productoCodigo") int productoCodigo, @Param("ordenCompraId") int ordenCompraId);

}
