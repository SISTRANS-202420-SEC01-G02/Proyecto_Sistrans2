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

import uniandes.edu.co.proyecto.modelo.Sucursal;
import uniandes.edu.co.proyecto.repositorio.SucursalRepository;

@RestController
public class SucursalController {

    @Autowired
    private SucursalRepository sucursalRepository;

    @GetMapping("/sucursal")
    public Collection<Sucursal> sucursales() {
        return sucursalRepository.darSucursales();
    }

    @PostMapping("/sucursal/new/save")
    public ResponseEntity<String> guardarSucursal(@RequestBody Sucursal sucursal) {
        try {
            sucursalRepository.insertarSucursal(
                    sucursal.getNombre(),
                    sucursal.getTamanio(),
                    sucursal.getDireccion()
            );
            return new ResponseEntity<>("Sucursal creada exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear la Sucursal", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/sucursal/{id}/edit/save")
    public ResponseEntity<String> editarGuardarSucursal(
            @PathVariable("id") int id,
            @RequestBody Sucursal sucursal) {
        try {
            sucursalRepository.actualizarSucursal(
                    id,
                    sucursal.getNombre(),
                    sucursal.getTamanio(),
                    sucursal.getDireccion()
            );
            return new ResponseEntity<>("Sucursal actualizada exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("La sucursal no se actualizó correctamente", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/sucursal/{id}/delete")
    public ResponseEntity<String> eliminarSucursal(@PathVariable("id") int id) {
        try {
            sucursalRepository.eliminarSucursal(id);
            return new ResponseEntity<>("Sucursal eliminada exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("La sucursal no se eliminó correctamente", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
