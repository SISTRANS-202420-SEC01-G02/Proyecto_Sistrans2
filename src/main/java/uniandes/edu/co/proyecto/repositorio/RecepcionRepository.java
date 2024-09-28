package uniandes.edu.co.proyecto.repositorio;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.Recepcion;

@Repository
public interface RecepcionRepository extends JpaRepository<Recepcion, Integer> {

    @Query(value = "SELECT * FROM recepcion", nativeQuery = true)
    Collection<Recepcion> darRecepciones();
    
    @Query(value = "SELECT * FROM recepcion WHERE id = :id", nativeQuery = true)
    Recepcion darRecepcion(@Param("id") int id);
    
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO recepcion (fecharecepcion) VALUES(:fechaRecepcion)", nativeQuery = true)
    void insertarRecepcion(@Param("fechaRecepcion") Date fechaRecepcion);

    @Modifying
    @Transactional
    @Query(value = "UPDATE recepcion SET fecharecepcion=:fechaRecepcion WHERE id=:id", nativeQuery = true)
    void actualizarRecepcion(@Param("id") int id, @Param("fechaRecepcion") Date fechaRecepcion);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM recepcion WHERE id=:id", nativeQuery = true)
    void eliminarRecepcion(@Param("id") int id);
}
