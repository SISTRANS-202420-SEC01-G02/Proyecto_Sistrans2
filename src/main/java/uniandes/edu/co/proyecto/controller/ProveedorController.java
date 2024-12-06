package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uniandes.edu.co.proyecto.modelo.Proveedor;
import uniandes.edu.co.proyecto.repository.ProveedorRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/proveedores")
public class ProveedorController {

    @Autowired
    private ProveedorRepository proveedorRepository;

    // Obtener todos los proveedores
    @GetMapping("/all")
    public ResponseEntity<List<Proveedor>> getAllProveedores() {
        List<Proveedor> proveedores = proveedorRepository.findAll();
        return ResponseEntity.ok(proveedores);
    }

    // Obtener proveedor por id
    @GetMapping("/{id}")
    public ResponseEntity<Proveedor> getProveedorById(@PathVariable int id) {
        Optional<Proveedor> proveedor = proveedorRepository.findById(id);
        return proveedor.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear un nuevo proveedor
    @PostMapping("/new")
    public ResponseEntity<String> createProveedor(@RequestBody Proveedor proveedor) {
        try {
            proveedorRepository.save(proveedor);
            return new ResponseEntity<>("Proveedor creado exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear el proveedor: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Actualizar un proveedor existente
    @PutMapping("/{id}")
    public ResponseEntity<Proveedor> updateProveedor(@PathVariable int id, @RequestBody Proveedor proveedor) {
        if (proveedorRepository.existsById(id)) {
            proveedorRepository.delete(proveedor);
            Proveedor updatedProveedor = proveedorRepository.save(proveedor);
            return ResponseEntity.ok(updatedProveedor);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar un proveedor
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProveedor(@PathVariable int id) {
        try{
            proveedorRepository.deleteById(id);
            return new ResponseEntity<>("Proveedor eliminado exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar el proveedor: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
