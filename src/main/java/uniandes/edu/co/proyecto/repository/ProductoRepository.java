package uniandes.edu.co.proyecto.repository;

import java.util.List;
import java.util.Date;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import uniandes.edu.co.proyecto.modelo.Producto;

@Repository
public interface ProductoRepository extends MongoRepository<Producto, Integer> {
    Producto findByNombre(String nombre);
    Producto findByCodigoBarras(String codigo_barras);

    //RFC1
    @Query("{ 'costo_unitario': { $gte: ?0, $lte: ?1 }, 'fecha_expiración': { $gte: ?2, $lte: ?3 }, 'categoria._id': ?4 }")
    List<Producto> findByCaracteristicas(int precioMin, int precioMax, Date fechaExpInf, Date fechaExpSup, int categoria);


}
