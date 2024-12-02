package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import uniandes.edu.co.proyecto.modelo.Bodega;
import uniandes.edu.co.proyecto.modelo.Sucursal;
import uniandes.edu.co.proyecto.repository.SucursalRepository;

import java.util.List;

@RestController
@RequestMapping("/sucursales")
public class SucursalController {

    @Autowired
    private SucursalRepository sucursalesRepository;

    // Crear una nueva sucursal
    @PostMapping("")
    public ResponseEntity<String> crearSucursal(@RequestBody Sucursal sucursales) {
        try {
            sucursalesRepository.save(sucursales);  // Guardamos la sucursal utilizando el método save() de MongoRepository
            return new ResponseEntity<>("Sucursal creada exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();  // Log para obtener más detalles
            return new ResponseEntity<>("Error al crear la sucursal: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    


    // Actualizar una sucursal existente
    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarSucursal(@PathVariable("id") int id, @RequestBody Sucursal sucursales) {
        try {
            // Primero buscamos la sucursal por ID
            Sucursal sucursalExistente = sucursalesRepository.findById(id).orElse(null);
            if (sucursalExistente != null) {
                // Actualizamos los campos de la sucursal existente
                sucursalExistente.setNombre(sucursales.getNombre());
                sucursalExistente.setCiudad(sucursales.getCiudad());
                sucursalExistente.setDireccion(sucursales.getDireccion());
                sucursalExistente.setTelefono(sucursales.getTelefono());
                sucursalExistente.setBodegas(sucursales.getBodegas());
                sucursalExistente.setOrdenes_compra(sucursales.getOrdenes_compra());

                // Guardamos la sucursal actualizada
                sucursalesRepository.save(sucursalExistente);
                return new ResponseEntity<>("Sucursal actualizada exitosamente", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Sucursal no encontrada", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar la sucursal: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Obtener todas las sucursales
    @GetMapping("")
    public ResponseEntity<List<Sucursal>> obtenerTodasLasSucursales() {
        try {
            List<Sucursal> sucursales = sucursalesRepository.findAll();
            return ResponseEntity.ok(sucursales);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Obtener una sucursal por ID
    @GetMapping("/{id}")
    public ResponseEntity<Sucursal> obtenerSucursalPorId(@PathVariable("id") int id) {
        try {
            Sucursal sucursal = sucursalesRepository.findById(id).orElse(null);
            if (sucursal != null) {
                return ResponseEntity.ok(sucursal);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Eliminar una sucursal por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarSucursal(@PathVariable("id") int id) {
        try {
            sucursalesRepository.deleteById(id);
            return new ResponseEntity<>("Sucursal eliminada exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar la sucursal: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Obtener bodegas de una sucursal
    @GetMapping("/{id}/bodegas")
    public ResponseEntity<List<Bodega>> obtenerBodegasPorSucursal(@PathVariable("id") int id) {
        try {
            Sucursal sucursal = sucursalesRepository.findById(id).orElse(null);
            if (sucursal != null) {
                return ResponseEntity.ok(sucursal.getBodegas());
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Agregar una nueva bodega a una sucursal
    @PostMapping("/{id}/bodegas/agregar")
    public ResponseEntity<String> agregarBodega(@PathVariable("id") int idSucursal, @RequestBody Bodega nuevaBodega) {
        try {
            Sucursal sucursal = sucursalesRepository.findById(idSucursal).orElse(null);
            if (sucursal != null) {
                sucursal.getBodegas().add(nuevaBodega);  // Agregar la bodega a la lista
                sucursalesRepository.save(sucursal);  // Guardamos la sucursal con la bodega añadida
                return new ResponseEntity<>("Bodega agregada exitosamente a la sucursal: " + idSucursal, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Sucursal no encontrada", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error al agregar la bodega: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping("/{id}/bodegas/eliminar")
    public ResponseEntity<String> eliminarBodega(@PathVariable("id") int idSucursal, @RequestBody Bodega bodega) {
    try {
        // Encontrar la sucursal por su ID
        Sucursal sucursal = sucursalesRepository.findById(idSucursal).orElse(null);

        if (sucursal != null) {
            // Si la sucursal se encuentra, eliminamos la bodega usando su _id
            sucursalesRepository.eliminarBodega(idSucursal, bodega.getId());

            // Verificamos si la bodega ha sido eliminada correctamente
            if (!sucursal.getBodegas().stream().anyMatch(b -> b.getId() == bodega.getId())) {
                return new ResponseEntity<>("Bodega eliminada exitosamente", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("No se pudo eliminar la bodega", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>("Sucursal no encontrada", HttpStatus.NOT_FOUND);
        }
    } catch (Exception e) {
        return new ResponseEntity<>("Error al eliminar la bodega: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}


}
