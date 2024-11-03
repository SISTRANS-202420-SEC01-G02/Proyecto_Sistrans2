package uniandes.edu.co.proyecto.servicio;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.repositorio.OrdenCompraRepository;
import uniandes.edu.co.proyecto.repositorio.RecepcionRepository;

@Service
public class RecepcionService {

    private RecepcionRepository recepcionRepository;

    private OrdenCompraRepository ordenCompraRepository;

    public RecepcionService(RecepcionRepository recepcionRepository){

        this.recepcionRepository = recepcionRepository;

    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void recepcionRF10(int ordencompra_id, int bodega_id){

        try{

            recepcionRepository.insertarRecepcion(ordencompra_id, bodega_id);

        }catch (Exception e){
            System.out.println("Error al registrar la recepcion de productos.");
        }

    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void actualizarProductoBodega(int ordencompra_id, int bodega_id){

        try{
            recepcionRepository.actualizarProductoBodega(bodega_id, ordencompra_id);
            ordenCompraRepository.actualizarOrdenCompra(ordencompra_id, "Entregada");
        }catch (Exception e){
            System.out.println("No se puede actualizar la recepcion de productos");
        }

    }
    
}
