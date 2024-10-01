package uniandes.edu.co.proyecto.repositorio;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.Sucursal;

@Repository
public interface SucursalRepository extends JpaRepository<Sucursal, Integer> {

    @Query(value = "SELECT * FROM sucursal", nativeQuery = true)
    Collection<Sucursal> darSucursales();
    
    @Query(value = "SELECT * FROM sucursal WHERE id = :id", nativeQuery = true)
    Sucursal darSucursal(@Param("id") int id);
    
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO sucursal (id, nombre, tamanio, direccion, ciudad_codigo) VALUES(sucursal_sequence.NEXTVAL, :nombre, :tamanio, :direccion, :ciudad_codigo)", nativeQuery = true)
    void insertarSucursal(@Param("nombre") String nombre, @Param("tamanio") int tamanio, @Param("direccion") String direccion, @Param("ciudad_codigo") int ciudad_codigo);

    @Modifying
    @Transactional
    @Query(value = "UPDATE sucursal SET nombre=:nombre, tamanio=:tamanio, direccion=:direccion WHERE id=:id", nativeQuery = true)
    void actualizarSucursal(@Param("id") int id, @Param("nombre") String nombre, @Param("tamanio") int tamanio, @Param("direccion") String direccion);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM sucursal WHERE id=:id", nativeQuery = true)
    void eliminarSucursal(@Param("id") int id);

    @Query(value = "SELECT suc.* " +
               "FROM productobodega pb " +
               "INNER JOIN bodega bod ON pb.bodega_id = bod.id " + 
               "INNER JOIN sucursal suc ON bod.sucursal_id = suc.id " + 
               "INNER JOIN producto prod ON pb.producto_codigobarras = prod.codigobarras " + 
               "WHERE prod.codigobarras = :codigobarras OR prod.nombre = :nombre_producto", nativeQuery = true)
    Collection<Sucursal> mostrarSucursalesProdDisponibles(@Param("codigobarras") int codigobarras, @Param("nombre_producto") String nombre);

}
