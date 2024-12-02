package uniandes.edu.co.proyecto.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import uniandes.edu.co.proyecto.modelo.Proveedor;
import java.util.List;

@Repository
public interface ProveedorRepository extends MongoRepository<Proveedor, Integer> {
    // Consultar todos los proveedores sin la lista de personas de contacto
    @Query(value = "{}", fields = "{ 'persona_contacto': 0 }")
    List<Proveedor> buscarTodosLosProveedoresSinContacto();

    // Consultar proveedor por su ID
    @Query("{_id: ?0}")
    Proveedor buscarPorId(String id);

    // Eliminar un proveedor por su ID
    @Query(value = "{_id: ?0}", delete = true)
    void eliminarProveedorPorId(String id);
}
