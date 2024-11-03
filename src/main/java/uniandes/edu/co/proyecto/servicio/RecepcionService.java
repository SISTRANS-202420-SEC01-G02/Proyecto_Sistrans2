package uniandes.edu.co.proyecto.servicio;


import java.util.Collection;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.Recepcion;
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

    @Transactional(isolation = Isolation.SERIALIZABLE, readOnly = true)
    public Collection<Recepcion> consusltarRfc6(int idB, int idS) throws InterruptedException {
        Collection<Recepcion> recepcions = recepcionRepository.obtenerRecepcionRfc6(idB, idS); // Consultar bar.
        System.out.println(recepcions.size());
        Thread.sleep(30000); // Simular operación larga para mantener el bloqueo.
        recepcions = recepcionRepository.obtenerRecepcionRfc6(idB, idS); // Consultar bar.
        return recepcions
        ;
    }
    
}
