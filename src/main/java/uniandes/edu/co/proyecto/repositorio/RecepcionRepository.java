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

    @Query(value = "SELECT Recepcion.*, Producto.nombre, ProductoCompra.cantidad, ProductoCompra.precioacordado "+//
                    "FROM Recepcion "+//
                    "INNER JOIN Bodega ON Recepcion.bodega_id = Bodega.id "+//
                    "INNER JOIN OrdenCompra ON OrdenCompra.id = Recepcion.ordencompra_id "+//
                    "INNER JOIN ProductoCompra ON ProductoCompra.ordencompra_id = OrdenCompra.id "+//
                    "INNER JOIN Producto ON Producto.codigobarras = ProductoCompra.producto_codigobarras "+//
                    "WHERE Recepcion.id = :recepcion_id", nativeQuery = true)
    Recepcion darRecepcionRF10(@Param("recepcion_id") int recepcion_id);
    
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO recepcion (id, fecharecepcion, bodega_id, ordencompra_id, sucursal_nombre, proveedor_nombre, bodega_nombre) " +//
                    "VALUES(recepcion_sequence.NEXTVAL, "+//
                    "(SELECT OrdenCompra.fechaesperada FROM OrdenCompra WHERE OrdenCompra.id = :ordencompra_id),:bodega_id,:ordencompra_id, "+//
                    "(SELECT Sucursal.nombre FROM Sucursal INNER JOIN OrdenCompra ON OrdenCompra.sucursal_id = Sucursal.id WHERE OrdenCompra.id = :ordencompra_id), " +//
                    "(SELECT Proveedor.nombre FROM Proveedor INNER JOIN OrdenCompra.proveedor_nit = Proveedor.nit WHERE OrdenCompra.id = :ordencompra_id), "+//
                    "(SELECT Bodega.nombre FROM Bodega WHERE Bodega.id = :bodega_id)) ",nativeQuery = true)
    void insertarRecepcion(@Param("ordencompra_id") int ordencompra_id, @Param("bodega_id") int bodega_id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO recepcion (id, fecharecepcion, bodega_id, ordencompra_id, sucursal_nombre, proveedor_nombre, bodega_nombre) "+//
                    "VALUES (recepcion_sequence.NEXTVAL, :fecharecepcion, :bodega_id, :ordencompra_id, :sucursal_nombre, :proveedor_nombre, :bodega_nombre)", nativeQuery = true)
    void insertarRecepcionRF10(@Param("ordencompra_id") int ordencompra_id, @Param("bodega_id") int bodega_id, @Param("fecharecepcion") Date fecharecepcion, @Param("sucursal_nombre") String sucursal_nombre, @Param("proveedor_nombre") String proveedor_nombre, @Param("bodega_nombre") String bodega_nombre);

    @Modifying
    @Transactional
    @Query(value = "UPDATE recepcion SET fecharecepcion=:fechaRecepcion WHERE id=:id", nativeQuery = true)
    void actualizarRecepcion(@Param("id") int id, @Param("fechaRecepcion") Date fechaRecepcion);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM recepcion WHERE id=:id", nativeQuery = true)
    void eliminarRecepcion(@Param("id") int id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE ProductoBodega PB SET " + //
    "PB.cantidadproducto = PB.cantidadproducto + (SELECT ProductoCompra.cantidad FROM OrdenCompra INNER JOIN ProductoCompra ON OrdenCompra.id = ProductoCompra.ordencompra_id WHERE OrdenCompra.id = :ordencompra_id), " + //
    "PB.costopromedio = ROUND(((PB.cantidadproducto * PB.costopromedio) + " + //
    "((SELECT ProductoCompra.precioacordado FROM OrdenCompra INNER JOIN ProductoCompra ON OrdenCompra.id = ProductoCompra.ordencompra_id WHERE OrdenCompra.id = :ordencompra_id) * " + //
    "(SELECT ProductoCompra.cantidad FROM OrdenCompra INNER JOIN ProductoCompra ON OrdenCompra.id = ProductoCompra.ordencompra_id WHERE OrdenCompra.id = :ordencompra_id))) / " + //
    "(PB.cantidadproducto + (SELECT ProductoCompra.cantidad FROM OrdenCompra INNER JOIN ProductoCompra ON OrdenCompra.id = ProductoCompra.ordencompra_id WHERE OrdenCompra.id = :ordencompra_id))) " + //
    "WHERE PB.bodega_id = :bodega_id AND PB.producto_codigobarras IN " + //
    "(SELECT ProductoCompra.producto_codigobarras FROM OrdenCompra INNER JOIN ProductoCompra ON OrdenCompra.id = ProductoCompra.ordencompra_id WHERE OrdenCompra.id = :ordencompra_id)", 
    nativeQuery = true)
    void actualizarProductoBodega(@Param("bodega_id") int bodega_id, @Param("ordencompra_id") int ordencompra_id);

    @Query(value = "select r.* from recepcion r inner join ordencompra on r.ordencompra_id = ordencompra .id where r.bodega_id = :idB and ordencompra.sucursal_id = :idS", nativeQuery = true)
    Collection<Recepcion> obtenerRecepcion( @Param("idB") int idB, @Param("idS") int idS);
}
