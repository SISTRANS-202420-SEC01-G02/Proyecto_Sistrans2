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
    @GetMapping
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
    @PostMapping
    public ResponseEntity<Proveedor> createProveedor(@RequestBody Proveedor proveedor) {
        Proveedor proveedorGuardado = proveedorRepository.save(proveedor);
        return new ResponseEntity<>(proveedorGuardado, HttpStatus.CREATED);
    }

    // Actualizar un proveedor existente
    @PutMapping("/{id}")
    public ResponseEntity<Proveedor> updateProveedor(@PathVariable int id, @RequestBody Proveedor proveedor) {
        if (proveedorRepository.existsById(id)) {
            proveedor.setId(id);  // Se asegura de que el id esté presente en el proveedor
            Proveedor updatedProveedor = proveedorRepository.save(proveedor);
            return ResponseEntity.ok(updatedProveedor);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar un proveedor
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProveedor(@PathVariable int id) {
        if (proveedorRepository.existsById(id)) {
            proveedorRepository.deleteById(id);
            return ResponseEntity.noContent().build();  // Respuesta correcta cuando se elimina
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
