package uniandes.edu.co.proyecto.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import uniandes.edu.co.proyecto.modelo.OrdenCompra;

import java.util.List;

@Repository
public interface OrdenCompraRepository extends MongoRepository<OrdenCompra, Integer> {

    // Consultar ordenes de compra por el ID de la sucursal
    @Query("{'sucursal_id': ?0}")
    List<OrdenCompra> findBySucursalId(int sucursal_id);

    // Consultar ordenes de compra por el ID del proveedor
    @Query("{'proveedor_id': ?0}")
    List<OrdenCompra> findByProveedorId(int proveedor_id);

    // Consultar ordenes de compra por estado
    @Query("{'estado': ?0}")
    List<OrdenCompra> findByEstado(String estado);

    // Consultar ordenes de compra por la fecha de creación
    @Query("{'fecha_creacion': ?0}")
    List<OrdenCompra> findByFechaCreacion(String fechaCreacion);

    // Consultar ordenes de compra por el ID de producto
    @Query("{'productos': { $in: [?0] }}")
    List<OrdenCompra> findByProductoId(int producto_id);

}
