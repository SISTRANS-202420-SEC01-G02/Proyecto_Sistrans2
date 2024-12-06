package uniandes.edu.co.proyecto.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import uniandes.edu.co.proyecto.modelo.Categoria;
import uniandes.edu.co.proyecto.repository.CategoriaRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/categorias")

public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @PostMapping("/new")
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

    @GetMapping("/all")
    public ResponseEntity<Map<String, Object>> obtenerCategorias() {
        try {
            Map<String, Object> response = new HashMap<>();
            response.put("categorias", categoriaRepository.findAll());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("message", "Error al obtener las categorías: " + e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
