package uniandes.edu.co.proyecto.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import uniandes.edu.co.proyecto.modelo.Categoria;
import uniandes.edu.co.proyecto.repository.CategoriaRepository;

@RestController
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @PostMapping("/categoria/new")
    public ResponseEntity<Map<String, Object>> categoriaGuardar(@RequestBody Categoria categoria) {
        try {
            categoriaRepository.save(categoria);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Categoría creada exitosamente");
            response.put("id", categoria.getId());
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("message", "Error al crear la categoría: " + e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
