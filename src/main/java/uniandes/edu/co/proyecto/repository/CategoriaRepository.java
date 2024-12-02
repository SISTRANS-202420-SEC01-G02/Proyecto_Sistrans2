package uniandes.edu.co.proyecto.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import uniandes.edu.co.proyecto.modelo.*;

@Repository
public interface CategoriaRepository extends MongoRepository<Categoria, Integer> {
}