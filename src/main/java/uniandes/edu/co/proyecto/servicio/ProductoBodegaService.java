package uniandes.edu.co.proyecto.servicio;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.repositorio.OrdenCompraRepository;
import uniandes.edu.co.proyecto.repositorio.ProductoBodegaRepository;

@Service
public class ProductoBodegaService {

    private ProductoBodegaRepository productoBodegaRepository;
    private OrdenCompraRepository ordenCompraRepository;

    public ProductoBodegaService(ProductoBodegaRepository productoBodegaRepository){

        this.productoBodegaRepository = productoBodegaRepository;

    }

    @Transactional(isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
    public void actualizarCostoPromedio(int id_bodega, int producto_codigobarras){

    }
    
}
