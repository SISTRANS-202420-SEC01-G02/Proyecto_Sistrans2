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

import uniandes.edu.co.proyecto.modelo.Categoria;
import uniandes.edu.co.proyecto.repositorio.CategoriaRepository;

@RestController
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping("/categoria")
    public Collection<Categoria> categorias() {
        return categoriaRepository.darCategorias();
    }

    @GetMapping("/categoria/codigo/{codigo}")
    public ResponseEntity<Categoria> obtenerCategoriaPorCodigo(@PathVariable("codigo") int codigo) {
        try {
            Categoria categoria = categoriaRepository.darCategoriaPorCodigo(codigo);
            if (categoria != null) {
                return new ResponseEntity<>(categoria, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/categoria/nombre/{nombre}")
    public ResponseEntity<Categoria> obtenerCategoriaPorNombre(@PathVariable("nombre") String nombre) {
        try {
            Categoria categoria = categoriaRepository.darCategoriaPorNombre(nombre);
            if (categoria != null) {
                return new ResponseEntity<>(categoria, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/categoria/new/save")
    public ResponseEntity<String> guardarCategoria(@RequestBody Categoria categoria) {
        try {
            categoriaRepository.insertarCategoria(categoria.getNombre(), categoria.getDescripcion(), categoria.getCaracteristicasAlmacenaje());
            return new ResponseEntity<>("Categoría creada exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear la Categoría", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/categoria/{codigo}/edit/save")
    public ResponseEntity<String> editarGuardarCategoria(@PathVariable("codigo") int codigo, @RequestBody Categoria categoria) {
        try {
            categoriaRepository.actualizarCategoria(codigo, categoria.getNombre(), categoria.getDescripcion(), categoria.getCaracteristicasAlmacenaje());
            return new ResponseEntity<>("Categoría actualizada exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("La categoría no se actualizó correctamente", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/categoria/{codigo}/delete")
    public ResponseEntity<String> eliminarCategoria(@PathVariable("codigo") int codigo) {
        try {
            categoriaRepository.eliminarCategoria(codigo);
            return new ResponseEntity<>("Categoría eliminada exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("La categoría no se eliminó correctamente", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
