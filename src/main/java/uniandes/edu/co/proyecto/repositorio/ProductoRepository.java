package uniandes.edu.co.proyecto.repositorio;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer>{

    @Query(value = "SELECT * FROM Producto", nativeQuery = true)
    Collection<Producto> darProductos();

    @Query(value = "SELECT * FROM Producto WHERE codigoBarras = :codigoBarras", nativeQuery = true)
    Producto darProducto(@Param("codigoBarras") int codigoBarras);
    
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Producto (nombre,costobodega,costounidad,presentacion,cantidadpresentacion,unidadmedida,fechaexpiracion) VALUES(:nombre,:costoBodega,:costoUnidad,:presentacion,:cantidadPresentacion,:unidadMedida,:fechaExpiracion)", nativeQuery = true)
    void insertarProducto(@Param("nombre") String nombre, @Param("costoBodega") Integer costoBodega, @Param("costoUnidad") Integer costoUnidad, @Param("presentacion") String presentacion, @Param("cantidadPresentacion") Integer cantidadPresentacion, @Param("unidadMedida") String unidadMedida, @Param("fechaExpiracion") Date fechaExpiracion);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Producto SET nombre=:nombre, costobodega=:costoBodega, costounidad=:costoUnidad, presentacion=:presentacion, cantidadpresentacion=:cantidadPresentacion, unidadmedida=:unidadMedida, fechaexpiracion=:fechaExpiracion WHERE codigobarras=:codigoBarras", nativeQuery = true)
    void actualizarProducto(@Param("codigoBarras") int codigoBarras, @Param("nombre") String nombre, @Param("costoBodega") Integer costoBodega, @Param("costoUnidad") Integer costoUnidad, @Param("presentacion") String presentacion, @Param("cantidadPresentacion") Integer cantidadPresentacion, @Param("unidadMedida") String unidadMedida, @Param("fechaExpiracion") Date fechaExpiracion);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Producto WHERE codigobarras=:codigoBarras", nativeQuery = true)
    void eliminarProducto(@Param("codigoBarras") int codigoBarras);
}