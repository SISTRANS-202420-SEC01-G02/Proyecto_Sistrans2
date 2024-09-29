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

import uniandes.edu.co.proyecto.modelo.Recepcion;
import uniandes.edu.co.proyecto.repositorio.RecepcionRepository;

@RestController
public class RecepcionController {

    @Autowired
    private RecepcionRepository recepcionRepository;

    @GetMapping("/recepcion")
    public Collection<Recepcion> recepciones() {
        return recepcionRepository.darRecepciones();
    }

    @PostMapping("/recepcion/new/save")
    public ResponseEntity<String> guardarRecepcion(@RequestBody Recepcion recepcion) {
        try {
            recepcionRepository.insertarRecepcion(
                    recepcion.getFechaRecepcion()

            );
            return new ResponseEntity<>("Recepción creada exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear la Recepción", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/recepcion/{id}/edit/save")
    public ResponseEntity<String> editarGuardarRecepcion(
            @PathVariable("id") int id,
            @RequestBody Recepcion recepcion) {
        try {
            recepcionRepository.actualizarRecepcion(
                    id,
                    recepcion.getFechaRecepcion()
            );
            return new ResponseEntity<>("Recepción actualizada exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("La recepción no se actualizó correctamente", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/recepcion/{id}/delete")
    public ResponseEntity<String> eliminarRecepcion(@PathVariable("id") int id) {
        try {
            recepcionRepository.eliminarRecepcion(id);
            return new ResponseEntity<>("Recepción eliminada exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("La recepción no se eliminó correctamente", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
