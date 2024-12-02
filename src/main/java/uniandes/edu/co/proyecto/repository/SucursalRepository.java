package uniandes.edu.co.proyecto.repository;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;
import uniandes.edu.co.proyecto.modelo.Bodega;
import uniandes.edu.co.proyecto.modelo.Sucursal;

public interface SucursalRepository extends MongoRepository<Sucursal, Integer> {

    // Consultar todas las sucursales sin la lista de bodegas para mejorar el rendimiento
    @Query(value = "{}", fields = "{ 'bodegas': 0 }")
    List<Sucursal> buscarTodasLasSucursales();

    // Consultar sucursal por su ID
    @Query("{_id: ?0}")
    List<Sucursal> buscarPorId(int id);


    // Actualizar una sucursal por su ID
    @Query("{ _id: ?0 }")
    @Update("{ $set: { nombre: ?1, ciudad: ?2, direccion: ?3, telefono: ?4, bodegas: ?5 }}")
    void actualizarSucursal(int id, String nombre, String ciudad, String direccion, long telefono, List<Bodega> bodegas);

    // Eliminar una sucursal por su ID
    @Query(value = "{_id: ?0}", delete = true)
    void eliminarSucursalPorId(int id);

    // Obtener todas las bodegas de una sucursal por su ID
    @Query(value = "{_id: ?0}", fields = "{ 'bodegas': 1 }")
    List<Bodega> obtenerBodegasPorSucursal(int id);

    // Agregar una bodega a la lista de bodegas de una sucursal
    @Query("{ _id: ?0 }")
    @Update("{ $push: { bodegas: ?1 } }")
    void agregarBodega(int idSucursal, Bodega nuevaBodega);

    
    @Query("{ '_id': ?0 }")
    @Update("{ $pull: { 'bodegas': { '_id': ?1 } } }")
    void eliminarBodega(int idSucursal, int bodegaId);
    
    
    
}
