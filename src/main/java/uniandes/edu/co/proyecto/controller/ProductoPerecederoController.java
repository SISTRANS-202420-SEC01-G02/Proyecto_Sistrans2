package uniandes.edu.co.proyecto.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import uniandes.edu.co.proyecto.modelo.ProductoPerecedero;
import uniandes.edu.co.proyecto.repositorio.ProductoPerecederoRepository;

@RestController
public class ProductoPerecederoController {

    @Autowired
    private ProductoPerecederoRepository productoPerecederoRepository;

    @GetMapping("/productoperecedero")
    public Collection<ProductoPerecedero> productos(){
        return productoPerecederoRepository.darProductosPerecederos();
    }
    
}
