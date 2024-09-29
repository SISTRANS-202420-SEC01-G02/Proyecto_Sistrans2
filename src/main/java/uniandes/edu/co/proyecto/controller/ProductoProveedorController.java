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

import uniandes.edu.co.proyecto.modelo.ProductoProveedor;
import uniandes.edu.co.proyecto.repositorio.ProductoProveedorRepository;

@RestController
public class ProductoProveedorController {

    @Autowired
    private ProductoProveedorRepository productoProveedorRepository;

    @GetMapping("/productoproveedor")
    public Collection<ProductoProveedor> productosProveedor() {
        return productoProveedorRepository.darProductosProveedores();
    }

    @PostMapping("/productoproveedor/new/save")
    public ResponseEntity<String> guardarProductoProveedor(@RequestBody ProductoProveedor productoProveedor) {
        try {
            productoProveedorRepository.insertarProductoProveedor(
                    productoProveedor.getPk().getProducto_codigobarras().getCodigoBarras(),
                    productoProveedor.getPk().getProveedor_nit().getNit()
            );
            return new ResponseEntity<>("ProductoProveedor creado exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear el ProductoProveedor", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/productoproveedor/{producto_codigobarras}/{proveedor_nit}/delete")
    public ResponseEntity<String> eliminarProductoProveedor(
            @PathVariable("producto_codigobarras") int producto_codigobarras,
            @PathVariable("proveedor_nit") int proveedor_nit) {
        try {
            productoProveedorRepository.eliminarProductoProveedor(producto_codigobarras, proveedor_nit);
            return new ResponseEntity<>("ProductoProveedor eliminado exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("El ProductoProveedor no se eliminó correctamente", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
