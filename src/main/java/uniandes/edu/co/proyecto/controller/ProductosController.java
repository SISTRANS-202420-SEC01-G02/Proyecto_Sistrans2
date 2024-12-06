package uniandes.edu.co.proyecto.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import uniandes.edu.co.proyecto.modelo.Producto;
import uniandes.edu.co.proyecto.repository.ProductoRepository;

@RestController
@RequestMapping("/productos")
public class ProductosController {

    @Autowired
    private ProductoRepository productoRepository;

    @PostMapping("/new")
    public ResponseEntity<Map<String, Object>> crearProducto(@RequestBody Producto producto) {
        try {
            productoRepository.save(producto);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Producto creado exitosamente");
            response.put("id", producto.getId());
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("message", "Error al crear el producto: " + e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{codigo_barras}")
    public ResponseEntity<Producto> obtenerProductoPorCodigo(@PathVariable("codigo_barras") String codigo_barras) {
        Producto producto = productoRepository.findByCodigoBarras(codigo_barras);
        return producto != null ? new ResponseEntity<>(producto, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<Producto> obtenerProductoPorNombre(@PathVariable("nombre") String nombre) {
        Producto producto = productoRepository.findByNombre(nombre);
        return producto != null ? new ResponseEntity<>(producto, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{codigoBarras}")
    public ResponseEntity<Map<String, Object>> actualizarProducto(@PathVariable("codigoBarras") String codigoBarras, @RequestBody Producto productoActualizado) {
        try {
            Producto producto = productoRepository.findByCodigoBarras(codigoBarras);
            if (producto != null) {
                producto.setNombre(productoActualizado.getNombre());
                producto.setCosto_bodega(productoActualizado.getCosto_bodega());
                producto.setCosto_unitario(productoActualizado.getCosto_unitario());
                producto.setPresentacion(productoActualizado.getPresentacion());
                producto.setUnidad_medida(productoActualizado.getUnidad_medida());
                producto.setVolumen_empaque(productoActualizado.getVolumen_empaque());
                producto.setPeso_empaque(productoActualizado.getPeso_empaque());
                producto.setCategoria(productoActualizado.getCategoria());
                producto.setFecha_expiracion(productoActualizado.getFecha_expiracion());
                productoRepository.save(producto);
                Map<String, Object> response = new HashMap<>();
                response.put("message", "Producto actualizado exitosamente");
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("message", "Error al actualizar el producto: " + e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/caracteristicas")
    public ResponseEntity<List<Producto>> mostrarProductosPorCaracteristicas(
        @RequestParam(required = false, defaultValue = "0") Integer precioMin,
        @RequestParam(required = false, defaultValue = "0") Integer precioMax,
        @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaExpInf,
        @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaExpSup,
        @RequestParam(required = false, defaultValue = "0") Integer categoriaID) {
            System.out.println("Parametros recibidos:");
            System.out.println( precioMin);
            System.out.println( precioMax);
            System.out.println( fechaExpInf);
            System.out.println(fechaExpSup);
            System.out.println(categoriaID);
        

        List<Producto> productos = productoRepository.findByCaracteristicas(precioMin, precioMax, fechaExpInf, fechaExpSup, categoriaID);
        return new ResponseEntity<>(productos, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<Map<String, Object>> obtenerProductos() {
        try {
            Map<String, Object> response = new HashMap<>();
            response.put("productos", productoRepository.findAll());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("message", "Error al obtener los productos: " + e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}