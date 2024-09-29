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

import uniandes.edu.co.proyecto.modelo.ProductoCompra;
import uniandes.edu.co.proyecto.repositorio.ProductoCompraRepository;

@RestController
public class ProductoCompraController {

    @Autowired
    private ProductoCompraRepository productoCompraRepository;

    @GetMapping("/productocompra")
    public Collection<ProductoCompra> productosCompra() {
        return productoCompraRepository.darProductosCompra();
    }

    @PostMapping("/productocompra/new/save")
    public ResponseEntity<String> guardarProductoCompra(@RequestBody ProductoCompra productoCompra) {
        try {
            productoCompraRepository.insertarProductoCompra(
                    productoCompra.getPk().getProducto_codigobarras().getCodigoBarras(),
                    productoCompra.getPk().getOrdencompra_id().getId(),
                    productoCompra.getPrecioAcordado(),
                    productoCompra.getCantidad()
            );
            return new ResponseEntity<>("ProductoCompra creado exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear el ProductoCompra", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/productocompra/{producto_codigobarras}/{ordencompra_id}/edit/save")
    public ResponseEntity<String> editarGuardarProductoCompra(
            @PathVariable("producto_codigobarras") int producto_codigobarras,
            @PathVariable("ordencompra_id") int ordencompra_id,
            @RequestBody ProductoCompra productoCompra) {

        try {
            productoCompraRepository.actualizarProductoCompra(
                    producto_codigobarras,
                    ordencompra_id,
                    productoCompra.getPrecioAcordado(),
                    productoCompra.getCantidad()
            );
            return new ResponseEntity<>("ProductoCompra actualizado exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("El ProductoCompra no se actualizó correctamente", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/productocompra/{producto_codigobarras}/{ordencompra_id}/delete")
    public ResponseEntity<String> eliminarProductoCompra(
            @PathVariable("producto_codigobarras") int producto_codigobarras,
            @PathVariable("ordencompra_id") int ordencompra_id) {
        try {
            productoCompraRepository.eliminarProductoCompra(producto_codigobarras, ordencompra_id);
            return new ResponseEntity<>("ProductoCompra eliminado exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("El ProductoCompra no se eliminó correctamente", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
