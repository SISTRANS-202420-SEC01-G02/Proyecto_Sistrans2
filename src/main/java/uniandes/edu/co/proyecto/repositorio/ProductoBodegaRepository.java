package uniandes.edu.co.proyecto.repositorio;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.ProductoBodega;
import uniandes.edu.co.proyecto.modelo.pks.ProductoBodegaPK;

@Repository
public interface ProductoBodegaRepository extends JpaRepository<ProductoBodega, ProductoBodegaPK> {

    @Query(value = "SELECT * FROM productobodega", nativeQuery = true)
    Collection<ProductoBodega> darProductosBodega();
    
    @Query(value = "SELECT * FROM productobodega WHERE producto_codigobarras = :productoCodigo AND bodega_id = :bodegaId", nativeQuery = true)
    ProductoBodega darProductoBodega(@Param("productoCodigo") int productoCodigo, @Param("bodegaId") int bodegaId);
    
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO productobodega (producto_codigobarras, bodega_id, costopromedio, cantidadproducto, nivelreorden, capacidadproducto) VALUES(:productoCodigo, :bodegaId, :costoPromedio, :cantidadProducto, :nivelReorden, :capacidadProducto)", nativeQuery = true)
    void insertarProductoBodega(@Param("productoCodigo") int productoCodigo, @Param("bodegaId") int bodegaId, @Param("costoPromedio") int costoPromedio, @Param("cantidadProducto") int cantidadProducto, @Param("nivelReorden") int nivelReorden, @Param("capacidadProducto") int capacidadProducto);

    @Modifying
    @Transactional
    @Query(value = "UPDATE productobodega SET costopromedio=:costoPromedio, cantidadproducto=:cantidadProducto, nivelreorden=:nivelReorden, capacidadproducto=:capacidadProducto WHERE producto_codigobarras=:productoCodigo AND bodega_id=:bodegaId", nativeQuery = true)
    void actualizarProductoBodega(@Param("productoCodigo") int productoCodigo, @Param("bodegaId") int bodegaId, @Param("costoPromedio") int costoPromedio, @Param("cantidadProducto") int cantidadProducto, @Param("nivelReorden") int nivelReorden, @Param("capacidadProducto") int capacidadProducto);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM productobodega WHERE producto_codigobarras=:productoCodigo AND bodega_id=:bodegaId", nativeQuery = true)
    void eliminarProductoBodega(@Param("productoCodigo") int productoCodigo, @Param("bodegaId") int bodegaId);

}
