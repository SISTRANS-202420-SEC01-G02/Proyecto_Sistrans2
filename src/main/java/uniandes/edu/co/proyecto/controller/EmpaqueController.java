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

import uniandes.edu.co.proyecto.modelo.Empaque;
import uniandes.edu.co.proyecto.repositorio.EmpaqueRepository;

@RestController
public class EmpaqueController {

    @Autowired
    private EmpaqueRepository empaqueRepository;

    @GetMapping("/empaque")
    public Collection<Empaque> empaques() {
        return empaqueRepository.darEmpaques();
    }

    @PostMapping("/empaque/new/save")
    public ResponseEntity<String> guardarEmpaque(@RequestBody Empaque empaque) {
        try {
            empaqueRepository.insertarEmpaque(empaque.getVolumenEmpaque(), empaque.getPesoEmpaque());
            return new ResponseEntity<>("Empaque creado exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear el Empaque", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/empaque/{id}/edit/save")
    public ResponseEntity<String> editarGuardarEmpaque(@PathVariable("id") int id, @RequestBody Empaque empaque) {
        try {
            empaqueRepository.actualizarEmpaque(id, empaque.getVolumenEmpaque(), empaque.getPesoEmpaque());
            return new ResponseEntity<>("Empaque actualizado exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("El empaque no se actualizó correctamente", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/empaque/{id}/delete")
    public ResponseEntity<String> eliminarEmpaque(@PathVariable("id") int id) {
        try {
            empaqueRepository.eliminarEmpaque(id);
            return new ResponseEntity<>("Empaque eliminado exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("El empaque no se eliminó correctamente", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
