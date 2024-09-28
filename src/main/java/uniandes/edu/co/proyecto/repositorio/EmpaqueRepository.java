package uniandes.edu.co.proyecto.repositorio;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.Empaque;

@Repository
public interface EmpaqueRepository extends JpaRepository<Empaque, Integer> {

    @Query(value = "SELECT * FROM Empaque", nativeQuery = true)
    Collection<Empaque> darEmpaques();
    
    @Query(value = "SELECT * FROM Empaque WHERE id = :id", nativeQuery = true)
    Empaque darEmpaque(@Param("id") int id);
    
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Empaque (volumenempaque, pesoempaque) VALUES(:volumenEmpaque, :pesoEmpaque)", nativeQuery = true)
    void insertarEmpaque(@Param("volumenEmpaque") int volumenEmpaque, @Param("pesoEmpaque") int pesoEmpaque);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Empaque SET volumenempaque=:volumenEmpaque, pesoempaque=:pesoEmpaque WHERE id=:id", nativeQuery = true)
    void actualizarEmpaque(@Param("id") int id, @Param("volumenEmpaque") int volumenEmpaque, @Param("pesoEmpaque") int pesoEmpaque);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Empaque WHERE id=:id", nativeQuery = true)
    void eliminarEmpaque(@Param("id") int id);
}
