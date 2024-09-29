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

import uniandes.edu.co.proyecto.modelo.Proveedor;
import uniandes.edu.co.proyecto.repositorio.ProveedorRepository;

@RestController
public class ProveedorController {

    @Autowired
    private ProveedorRepository proveedorRepository;

    @GetMapping("/proveedor")
    public Collection<Proveedor> proveedores() {
        return proveedorRepository.darProveedores();
    }

    @PostMapping("/proveedor/new/save")
    public ResponseEntity<String> guardarProveedor(@RequestBody Proveedor proveedor) {
        try {
            proveedorRepository.insertarProveedor(
                    proveedor.getNit(),
                    proveedor.getNombre(),
                    proveedor.getDireccion(),
                    proveedor.getNombrePersona(),
                    proveedor.getTelefonoPersona()
            );
            return new ResponseEntity<>("Proveedor creado exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear el Proveedor", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/proveedor/{nit}/edit/save")
    public ResponseEntity<String> editarGuardarProveedor(
            @PathVariable("nit") int nit,
            @RequestBody Proveedor proveedor) {
        try {
            proveedorRepository.actualizarProveedor(
                    nit,
                    proveedor.getNombre(),
                    proveedor.getDireccion(),
                    proveedor.getNombrePersona(),
                    proveedor.getTelefonoPersona()
            );
            return new ResponseEntity<>("Proveedor actualizado exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("El proveedor no se actualizó correctamente", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/proveedor/{nit}/delete")
    public ResponseEntity<String> eliminarProveedor(@PathVariable("nit") int nit) {
        try {
            proveedorRepository.eliminarProveedor(nit);
            return new ResponseEntity<>("Proveedor eliminado exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("El proveedor no se eliminó correctamente", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
