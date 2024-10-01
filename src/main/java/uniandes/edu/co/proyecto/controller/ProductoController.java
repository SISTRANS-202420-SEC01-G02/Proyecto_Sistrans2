package uniandes.edu.co.proyecto.controller;

import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import uniandes.edu.co.proyecto.modelo.Producto;
import uniandes.edu.co.proyecto.repositorio.ProductoRepository;
import uniandes.edu.co.proyecto.repositorio.ProductoRepository.ProductoRequiereReorden;

@RestController
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository;

    @GetMapping("/producto")
    public Collection<Producto> productos(){
        return productoRepository.darProductos();
    }

    @GetMapping("/producto/codigobarras/{codigoBarras}")
    public ResponseEntity<Producto> darProductoCodigo(@PathVariable("codigoBarras") Integer codigoBarras){
        Producto producto = productoRepository.darProductoCodigo(codigoBarras);
        if (producto != null) {
            return new ResponseEntity<>(producto, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/producto/rfc5")
    public ResponseEntity<?> productoRFC5(){
        try{
            Collection<ProductoRequiereReorden> informacion = productoRepository.obtenerProductosRequierenReorden();
            ProductoRequiereReorden info = informacion.iterator().next();
            Map<String, Object> response = new HashMap<>();
            response.put("producto_codigobarras", info.getProductoId());
            response.put("producto_nombre", info.getProductoNombre());
            response.put("bodega_id", info.getBodegaId());
            response.put("bodega_nombre", info.getBodegaNombre());
            response.put("sucursal_id", info.getSucursalId());
            response.put("sucursal_nombre", info.getSucursalNombre());
            response.put("proveedor_nit", info.getProveedorNit());
            response.put("proveedor_nombre", info.getProveedorNombre());
            response.put("pb_cantidadActual", info.getCantidadActual());

            return ResponseEntity.ok(response);
        }catch (Exception e){
            return new ResponseEntity<>("Falló", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/producto/nombre/{nombre}")
    public ResponseEntity<Producto> darProductoNombre(@PathVariable("nombre") String nombre){
        Producto producto = productoRepository.darProductoNombre(nombre);
        if (producto != null) {
            return new ResponseEntity<>(producto, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/producto/new/save")
    public ResponseEntity<String> guardarProducto(@RequestBody Producto producto){
        try{
            productoRepository.insertarProducto(producto.getNombre(), producto.getCostoBodega(), producto.getCostoUnidad(), producto.getPresentacion(), producto.getCantidadPresentacion(), producto.getUnidadMedida(), producto.getFechaExpiracion(), producto.getCategoria().getCodigo(), producto.getEmpaque().getId());
            return new ResponseEntity<>("Producto creado exitosamente", HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>("Error al crear el Producto", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/producto/{codigoBarras}/edit/save")
    public ResponseEntity<String> editarGuardarProducto(@PathVariable("codigoBarras") int codigoBarras, @RequestBody Producto producto){
        
        try{
            productoRepository.actualizarProducto(codigoBarras, producto.getNombre(), producto.getCostoBodega(), producto.getCostoUnidad(), producto.getPresentacion(), producto.getCantidadPresentacion(), producto.getUnidadMedida(), producto.getFechaExpiracion(), producto.getCategoria().getCodigo(), producto.getEmpaque().getId());
            return new ResponseEntity<>("Producto actualizado exitosamente", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("El producto no se actualizó correctamente", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/producto/{codigoBarras}/delete")
    public ResponseEntity<String> eliminarProducto(@PathVariable("codigoBarras") int codigoBarras){
        try{
            productoRepository.eliminarProducto(codigoBarras);
            return new ResponseEntity<>("Producto eliminado exitosamente", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("El producto no se eliminó correctamente", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/producto/filtrar/{precio_min}/{precio_max}/{fechavencimiento}/{sucursal_id}/{nombre_categoria}")
    public ResponseEntity<Collection<Producto>> filtrarProductos(
            @PathVariable("precio_min") int precioMin,
            @PathVariable("precio_max") int precioMax,
            @PathVariable("fechavencimiento")  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fechaVencimiento,
            @PathVariable("sucursal_id") int sucursalId,
            @PathVariable("nombre_categoria") String nombreCategoria) {
        try {
            Collection<Producto> productos = productoRepository.encontrarProductosFiltrados(
                    precioMin, 
                    precioMax, 
                    fechaVencimiento, 
                    sucursalId, 
                    nombreCategoria);

            return new ResponseEntity<>(productos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
