package uniandes.edu.co.proyecto.repositorio;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer>{

    @Query(value = "SELECT * FROM Categoria", nativeQuery = true)
    Collection<Categoria> darCategorias();
    
    @Query(value = "SELECT * FROM Categoria WHERE codigo = :codigo", nativeQuery = true)
    Categoria darCategoria(@Param("codigo") int codigo);
    
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Categoria (nombre,descripcion,caracteristicasalmacenaje) VALUES(:nombre,:descripcion,:caracteristicasAlmacenaje)", nativeQuery = true)
    void insertarCategoria(@Param("nombre") String nombre, @Param("descripcion") String descripcion, @Param("caracteristicasAlmacenaje") String caracteristicasAlmacenaje);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Categoria SET nombre=:nombre, descripcion=:descripcion, caracteristicasalmacenaje=:caracteristicasAlmacenaje WHERE codigo=:codigo", nativeQuery = true)
    void actualizarCategoria(@Param("codigo") int codigo, @Param("nombre") String nombre, @Param("descripcion") String descripcion, @Param("caracteristicasAlmacenaje") String caracteristicasAlmacenaje);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Categoria WHERE codigo=:codigo", nativeQuery = true)
    void eliminarCategoria(@Param("codigo") int codigo);

}
