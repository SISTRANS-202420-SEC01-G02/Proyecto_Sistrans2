package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uniandes.edu.co.proyecto.modelo.OrdenCompra;
import uniandes.edu.co.proyecto.repository.OrdenCompraRepository;
import uniandes.edu.co.proyecto.repository.SucursalRepository;
import uniandes.edu.co.proyecto.modelo.Sucursal;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ordenescompra")
public class OrdenCompraController {

    @Autowired
    private OrdenCompraRepository ordenCompraRepository;

    @Autowired
    private SucursalRepository sucursalRepository;

    // Crear una nueva orden de compra
    @PostMapping("")
    public ResponseEntity<String> crearOrdenCompra(@RequestBody OrdenCompra ordenCompra) {
        try {
            // Verificar si la sucursal existe
            Optional<Sucursal> sucursal = sucursalRepository.findById(ordenCompra.getSucursal_id());
            if (!sucursal.isPresent()) {
                return new ResponseEntity<>("Sucursal no encontrada o ID de sucursal inválido", HttpStatus.BAD_REQUEST);
            }

            // Establecer el estado de la orden de compra como "vigente"
            ordenCompra.setEstado("Vigente");

            // Generar un ID único para la orden de compra si no se ha proporcionado
            if (ordenCompra.getId() == 0) {
                ordenCompra.setId(4);  // Ejemplo de generación de ID único
            }

            // Guardar la orden de compra en la base de datos
            ordenCompraRepository.save(ordenCompra);

            return new ResponseEntity<>("Orden de compra creada exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();  // Log para obtener más detalles
            return new ResponseEntity<>("Error al crear la orden de compra: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Obtener todas las órdenes de compra
    @GetMapping("")
    public ResponseEntity<List<OrdenCompra>> obtenerTodasLasOrdenesCompra() {
        try {
            List<OrdenCompra> ordenesCompra = ordenCompraRepository.findAll();
            return ResponseEntity.ok(ordenesCompra);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Obtener una orden de compra por ID
    @GetMapping("/{id}")
    public ResponseEntity<OrdenCompra> obtenerOrdenCompraPorId(@PathVariable int id) {
        try {
            OrdenCompra ordenCompra = ordenCompraRepository.findById(id).orElse(null);
            if (ordenCompra != null) {
                return ResponseEntity.ok(ordenCompra);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
