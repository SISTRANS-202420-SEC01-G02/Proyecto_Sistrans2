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

    @Query(value = "SELECT prod.codigobarras, prod.nombre,pb.*\r\n" +
                    "FROM productobodega pb\r\n" + 
                    "INNER JOIN producto prod ON pb.producto_codigobarras = prod.codigobarras\r\n" + 
                    "INNER JOIN bodega bod ON pb.bodega_id = bod.id\r\n" +
                    "WHERE bod.id =:bodega_id AND bod.sucursal_id =:sucursal_id", nativeQuery = true)
    Collection<ProductoBodega> rFC3(@Param("bodega_id") int bodega_id, @Param("sucursal_id") int sucursal_id);
             
}
