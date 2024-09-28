package uniandes.edu.co.proyecto.repositorio;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.Bodega;

@Repository
public interface BodegaRepository extends JpaRepository<Bodega, Integer>{

    
    @Query(value = "SELECT * FROM Bodega", nativeQuery = true)
    Collection<Bodega> darBodegas();

    @Query(value = "SELECT * FROM Bodega WHERE id=:id", nativeQuery = true)
    Bodega darBodega();

    @Modifying
    @Transactional
    @Query( value = "INSERT INTO Bodega (nombre,tamanio) VALUES(:nombre,:tamanio)", nativeQuery = true)
    void insertarBodega(@Param("nombre") String nombre, @Param("tamanio") Integer tamanio);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Bodega SET nombre=:nombre, tamanio=:tamanio WHERE id=:id", nativeQuery = true)
    void actualizarBodega(@Param("id") int id, @Param("nombre") String nombre, @Param("tamanio") Integer tamanio);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Bodega WHERE id=:id", nativeQuery = true)
    void eliminarBodega(@Param("id") int id);
    
}
