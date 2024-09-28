package uniandes.edu.co.proyecto.repositorio;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.Proveedor;

@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor, Integer> {

    @Query(value = "SELECT * FROM proveedor", nativeQuery = true)
    Collection<Proveedor> darProveedores();
    
    @Query(value = "SELECT * FROM proveedor WHERE nit = :nit", nativeQuery = true)
    Proveedor darProveedor(@Param("nit") int nit);
    
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO proveedor (nit, nombre, direccion, nombrepersona, telefonopersona) VALUES(:nit, :nombre, :direccion, :nombrePersona, :telefonoPersona)", nativeQuery = true)
    void insertarProveedor(@Param("nit") int nit, @Param("nombre") String nombre, @Param("direccion") String direccion, @Param("nombrePersona") String nombrePersona, @Param("telefonoPersona") int telefonoPersona);

    @Modifying
    @Transactional
    @Query(value = "UPDATE proveedor SET nombre=:nombre, direccion=:direccion, nombrepersona=:nombrePersona, telefonopersona=:telefonoPersona WHERE nit=:nit", nativeQuery = true)
    void actualizarProveedor(@Param("nit") int nit, @Param("nombre") String nombre, @Param("direccion") String direccion, @Param("nombrePersona") String nombrePersona, @Param("telefonoPersona") int telefonoPersona);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM proveedor WHERE nit=:nit", nativeQuery = true)
    void eliminarProveedor(@Param("nit") int nit);
}
