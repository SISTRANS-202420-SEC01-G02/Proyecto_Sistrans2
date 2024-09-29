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

import uniandes.edu.co.proyecto.modelo.ProductoBodega;
import uniandes.edu.co.proyecto.repositorio.ProductoBodegaRepository;

@RestController
public class ProductoBodegaController {

    @Autowired
    private ProductoBodegaRepository productoBodegaRepository;

    @GetMapping("/productobodega")
    public Collection<ProductoBodega> productosBodega() {
        return productoBodegaRepository.darProductosBodega();
    }

    @PostMapping("/productobodega/new/save")
    public ResponseEntity<String> guardarProductoBodega(@RequestBody ProductoBodega productoBodega) {
        try {
            productoBodegaRepository.insertarProductoBodega(
                    productoBodega.getPk().getProducto_codigobarras().getCodigoBarras(),
                    productoBodega.getPk().getBodega_id().getId(),
                    productoBodega.getCostoPromedio(),
                    productoBodega.getCantidadProducto(),
                    productoBodega.getNivelReorden(),
                    productoBodega.getCapacidadProducto()
            );
            return new ResponseEntity<>("ProductoBodega creado exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear el ProductoBodega", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/productobodega/{producto_codigobarras}/{bodega_id}/edit/save")
    public ResponseEntity<String> editarGuardarProductoBodega(
            @PathVariable("producto_codigobarras") int producto_codigobarras,
            @PathVariable("bodega_id") int bodega_id,
            @RequestBody ProductoBodega productoBodega) {

        try {
            productoBodegaRepository.actualizarProductoBodega(
                    producto_codigobarras,
                    bodega_id,
                    productoBodega.getCostoPromedio(),
                    productoBodega.getCantidadProducto(),
                    productoBodega.getNivelReorden(),
                    productoBodega.getCapacidadProducto()
            );
            return new ResponseEntity<>("ProductoBodega actualizado exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("El ProductoBodega no se actualizó correctamente", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/productobodega/{producto_codigobarras}/{bodega_id}/delete")
    public ResponseEntity<String> eliminarProductoBodega(
            @PathVariable("producto_codigobarras") int producto_codigobarras,
            @PathVariable("bodega_id") int bodega_id) {
        try {
            productoBodegaRepository.eliminarProductoBodega(producto_codigobarras, bodega_id);
            return new ResponseEntity<>("ProductoBodega eliminado exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("El ProductoBodega no se eliminó correctamente", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
