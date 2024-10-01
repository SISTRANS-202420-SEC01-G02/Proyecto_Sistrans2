package uniandes.edu.co.proyecto.repositorio;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer>{

    public interface ProductoRequiereReorden {
        Integer getProductoId();
        String getProductoNombre();
        Integer getBodegaId();
        String getBodegaNombre();
        Integer getSucursalId();
        String getSucursalNombre();
        Integer getProveedorNit();
        String getProveedorNombre();
        Integer getCantidadActual();
    }

    @Query(value = "SELECT * FROM Producto", nativeQuery = true)
    Collection<Producto> darProductos();

    @Query(value = "SELECT * FROM Producto WHERE codigobarras = :codigoBarras", nativeQuery = true)
    Producto darProductoCodigo(@Param("codigoBarras") Integer codigoBarras);

    @Query(value = "SELECT * FROM Producto WHERE nombre = :nombre", nativeQuery = true)
    Producto darProductoNombre(@Param("nombre") String nombre);

    @Query(value = "SELECT prod.* " +
                   "FROM producto prod " +
                   "INNER JOIN categoria cat ON prod.categoria_codigo = cat.codigo " +
                   "INNER JOIN productobodega pb ON prod.codigobarras = pb.producto_codigobarras " +
                   "INNER JOIN productoperecedero pp ON pp.producto_codigobarras = prod.codigobarras " +
                   "INNER JOIN bodega bod ON pb.bodega_id = bod.id " +
                   "WHERE prod.costounidad BETWEEN :precio_min AND :precio_max " +
                   "AND pp.fechavencimiento > :fechavencimiento " +
                   "AND bod.sucursal_id = :sucursal_id " +
                   "AND cat.nombre = :nombre_categoria",
          nativeQuery = true)
    Collection<Producto> encontrarProductosFiltrados(@Param("precio_min") int precioMin, 
                                                     @Param("precio_max") int precioMax, 
                                                     @Param("fechavencimiento") Date fechaVencimiento, 
                                                     @Param("sucursal_id") int sucursalId, 
                                                     @Param("nombre_categoria") String nombreCategoria);


    @Query(value = "SELECT * FROM Producto WHERE codigoBarras = :codigoBarras", nativeQuery = true)
    Producto darProducto(@Param("codigoBarras") int codigoBarras);
    
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Producto (codigobarras, nombre,costobodega,costounidad,presentacion,cantidadpresentacion,unidadmedida,fechaexpiracion, categoria_codigo, empaque_id) VALUES(producto_sequence.NEXTVAL,:nombre,:costoBodega,:costoUnidad,:presentacion,:cantidadPresentacion,:unidadMedida,:fechaExpiracion, :categoria_codigo, :empaque_id)", nativeQuery = true)
    void insertarProducto(@Param("nombre") String nombre, @Param("costoBodega") Integer costoBodega, @Param("costoUnidad") Integer costoUnidad, @Param("presentacion") String presentacion, @Param("cantidadPresentacion") Integer cantidadPresentacion, @Param("unidadMedida") String unidadMedida, @Param("fechaExpiracion") Date fechaExpiracion, @Param("categoria_codigo") Integer categoria_codigo, @Param("empaque_id") Integer empaque_id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Producto SET nombre=:nombre, costobodega=:costoBodega, costounidad=:costoUnidad, presentacion=:presentacion, cantidadpresentacion=:cantidadPresentacion, unidadmedida=:unidadMedida, fechaexpiracion=:fechaExpiracion, categoria_codigo=:categoria_codigo, empaque_id=:empaque_id WHERE codigobarras=:codigoBarras", nativeQuery = true)
    void actualizarProducto(@Param("codigoBarras") Integer codigoBarras, @Param("nombre") String nombre, @Param("costoBodega") Integer costoBodega, @Param("costoUnidad") Integer costoUnidad, @Param("presentacion") String presentacion, @Param("cantidadPresentacion") Integer cantidadPresentacion, @Param("unidadMedida") String unidadMedida, @Param("fechaExpiracion") Date fechaExpiracion, @Param("categoria_codigo") Integer categoria_codigo, @Param("empaque_id") Integer empaque_id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Producto WHERE codigobarras=:codigoBarras", nativeQuery = true)
    void eliminarProducto(@Param("codigoBarras") int codigoBarras);

    @Query(value = "SELECT prod.codigobarras AS productoId, " +
                   "prod.nombre AS productoNombre, " +
                   "bod.id AS bodegaId, " +
                   "bod.nombre AS bodegaNombre, " +
                   "suc.id AS sucursalId, " +
                   "suc.nombre AS sucursalNombre, " +
                   "prov.nit AS proveedorNit, " +
                   "prov.nombre AS proveedorNombre, " +
                   "pb.cantidadproducto AS cantidadActual " +
                   "FROM productobodega pb " +
                   "INNER JOIN producto prod ON pb.producto_codigobarras = prod.codigobarras " +
                   "INNER JOIN bodega bod ON pb.bodega_id = bod.id " +
                   "INNER JOIN sucursal suc ON bod.sucursal_id = suc.id " +
                   "INNER JOIN productoproveedor pp ON pp.producto_codigobarras = prod.codigobarras " +
                   "INNER JOIN proveedor prov ON pp.proveedor_nit = prov.nit " +
                   "WHERE pb.cantidadproducto < pb.nivelreorden", nativeQuery = true)
    Collection<ProductoRequiereReorden> obtenerProductosRequierenReorden();
}