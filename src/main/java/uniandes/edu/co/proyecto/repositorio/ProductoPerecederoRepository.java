package uniandes.edu.co.proyecto.repositorio;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.ProductoPerecedero;
import uniandes.edu.co.proyecto.modelo.pks.ProductoPerecederoPK;


@Repository
public interface ProductoPerecederoRepository extends JpaRepository<ProductoPerecedero, ProductoPerecederoPK>{

    @Query(value = "SELECT * FROM ProductoPerecedero", nativeQuery = true)
    Collection<ProductoPerecedero> darProductosPerecederos();

    @Query(value = "SELECT * FROM ProductoPerecedero WHERE producto_codigobarras=:producto_codigobarras", nativeQuery = true)
    ProductoPerecedero daProductoPerecedero(@Param("producto_codigobarras") Integer producto_codigobarras);
    
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO ProductoPerecedero (producto_codigobarra,fechavencimiento) VALUES(:producto_codigobarras,:fechavencimiento", nativeQuery = true)
    void insertarProductoPerecedero(@Param("producto_codigobarras") Integer producto_codigobarras, @Param("fechavencimiento") Date fechavencimiento);

    @Modifying
    @Transactional
    @Query(value = "UPDATE ProductoPerecedero SET fechavencimiento=:fechavencimiento WHERE prodcuto_codigobarras=:producto_codigobarras", nativeQuery = true)
    void actualizarProductoPerecedero(@Param("producto_codigobarras") Integer producto_codigobarras, @Param("fechavencimiento") Date fechavencimiento);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM ProductoPerecedero WHERE producto_codigobarras=:producto_codigobarras", nativeQuery = true)
    void eliminarProductoPerecedero(@Param("producto_codigobarras") int producto_codigobarras);
}
