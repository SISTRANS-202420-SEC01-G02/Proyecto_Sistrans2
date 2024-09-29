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

import uniandes.edu.co.proyecto.modelo.Ciudad;
import uniandes.edu.co.proyecto.repositorio.CiudadRepository;

@RestController
public class CiudadController {

    @Autowired
    private CiudadRepository ciudadRepository;

    @GetMapping("/ciudad")
    public Collection<Ciudad> ciudades() {
        return ciudadRepository.darCiudades();
    }

    @PostMapping("/ciudad/new/save")
    public ResponseEntity<String> guardarCiudad(@RequestBody Ciudad ciudad) {
        try {
            ciudadRepository.insertarCiudad(ciudad.getNombre());
            return new ResponseEntity<>("Ciudad creada exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear la Ciudad", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/ciudad/{codigo}/edit/save")
    public ResponseEntity<String> editarGuardarCiudad(@PathVariable("codigo") int codigo, @RequestBody Ciudad ciudad) {
        try {
            ciudadRepository.actualizarCiudad(codigo, ciudad.getNombre());
            return new ResponseEntity<>("Ciudad actualizada exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("La ciudad no se actualizó correctamente", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/ciudad/{codigo}/delete")
    public ResponseEntity<String> eliminarCiudad(@PathVariable("codigo") int codigo) {
        try {
            ciudadRepository.eliminarCiudad(codigo);
            return new ResponseEntity<>("Ciudad eliminada exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("La ciudad no se eliminó correctamente", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
