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

import uniandes.edu.co.proyecto.modelo.Bodega;
import uniandes.edu.co.proyecto.repositorio.BodegaRepository;

@RestController
public class BodegaController {

    @Autowired
    private BodegaRepository bodegaRepository;

    @GetMapping("/bodega")
    public Collection<Bodega> bodegas() {
        return bodegaRepository.darBodegas();
    }

    @PostMapping("/bodega/new/save")
    public ResponseEntity<String> guardarBodega(@RequestBody Bodega bodega) {
        try {
            bodegaRepository.insertarBodega(bodega.getNombre(), bodega.getTamanio());
            return new ResponseEntity<>("Bodega creada exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear la Bodega", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/bodega/{id}/edit/save")
    public ResponseEntity<String> editarGuardarBodega(@PathVariable("id") int id, @RequestBody Bodega bodega) {
        try {
            bodegaRepository.actualizarBodega(id, bodega.getNombre(), bodega.getTamanio());
            return new ResponseEntity<>("Bodega actualizada exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("La bodega no se actualizó correctamente", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/bodega/{id}/delete")
    public ResponseEntity<String> eliminarBodega(@PathVariable("id") int id) {
        try {
            bodegaRepository.eliminarBodega(id);
            return new ResponseEntity<>("Bodega eliminada exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("La bodega no se eliminó correctamente", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
