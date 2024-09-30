package uniandes.edu.co.proyecto.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import uniandes.edu.co.proyecto.modelo.Producto;
import uniandes.edu.co.proyecto.repositorio.ProductoRepository;

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
    
}
