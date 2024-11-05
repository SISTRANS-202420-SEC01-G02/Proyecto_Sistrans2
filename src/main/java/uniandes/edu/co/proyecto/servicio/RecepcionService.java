package uniandes.edu.co.proyecto.servicio;


import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.Bodega;
import uniandes.edu.co.proyecto.modelo.OrdenCompra;
import uniandes.edu.co.proyecto.modelo.ProductoCompra;
import uniandes.edu.co.proyecto.modelo.Recepcion;
import uniandes.edu.co.proyecto.repositorio.BodegaRepository;
import uniandes.edu.co.proyecto.repositorio.OrdenCompraRepository;
import uniandes.edu.co.proyecto.repositorio.RecepcionRepository;

@Service
public class RecepcionService {


    @Autowired
    private RecepcionRepository recepcionRepository;

    @Autowired 
    private BodegaRepository bodegaRepository;


    @Autowired
    private OrdenCompraRepository ordenCompraRepository;

    public RecepcionService(RecepcionRepository recepcionRepository){

        this.recepcionRepository = recepcionRepository;

    }

    @Transactional(isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
    public void recepcionRF10(int ordencompra_id, int bodega_id){

        try{

            OrdenCompra ordenCompra = ordenCompraRepository.darOrdenCompra(ordencompra_id);
            Date fechaRecepcion = ordenCompra.getFechaEsperada();
            String sucursal_nombre = ordenCompra.getSucursal().getNombre();
            String proveedor_nombre = ordenCompra.getProveedor().getNombre();
            Bodega bodega = bodegaRepository.darBodega(bodega_id);
            String bodega_nombre = bodega.getNombre();


            recepcionRepository.insertarRecepcionRF10(ordencompra_id, bodega_id, fechaRecepcion, sucursal_nombre,proveedor_nombre, bodega_nombre);
            ordenCompraRepository.actualizarOrdenCompra(ordencompra_id, "Entregada");
            Collection<ProductoCompra> productosCompra = recepcionRepository.darInfoProductoCompra(ordenCompra.getId());
            for (ProductoCompra prod : productosCompra) {
                System.out.println("Producto: " + prod.getPk().getProducto_codigobarras().getNombre());
            }

            for(ProductoCompra prod: productosCompra){
                int producto_codigobarras = prod.getPk().getProducto_codigobarras().getCodigoBarras();
                int cantidad = prod.getCantidad();
                int precioacordado = prod.getPrecioAcordado();
                recepcionRepository.actualizarProducto(bodega_id, producto_codigobarras, cantidad, precioacordado);
            }

        }catch (Exception e){
            System.out.println("Error al registrar la recepcion de productos.");
        }

    }

    @Transactional(isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
    public void actualizarProductoBodega(int ordencompra_id, int bodega_id){

        try{

            Collection<ProductoCompra> productosCompra = recepcionRepository.darInfoProductoCompra(ordencompra_id);

            for(ProductoCompra prod: productosCompra){
                int producto_codigobarras = prod.getPk().getProducto_codigobarras().getCodigoBarras();
                System.out.println(producto_codigobarras);
                int cantidad = prod.getCantidad();
                int precioacordado = prod.getPrecioAcordado();
                recepcionRepository.actualizarProducto(bodega_id, producto_codigobarras, cantidad, precioacordado);
            }
        }catch (Exception e){
            System.out.println("No se puede actualizar la recepcion de productos");
        }

    }

    @Transactional(isolation = Isolation.SERIALIZABLE, readOnly = true)
    public Collection<Recepcion> consusltarRfc6(int idB, int idS) throws InterruptedException {
        Collection<Recepcion> recepcions = recepcionRepository.obtenerRecepcion(idB, idS); // Consultar bar.
        System.out.println(recepcions.size());
        Thread.sleep(30000); // Simular operación larga para mantener el bloqueo.
        recepcions = recepcionRepository.obtenerRecepcion(idB, idS); // Consultar bar.
        return recepcions
        ;
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
    public Collection<Recepcion> consusltarRfc7(int idB, int idS) throws InterruptedException {
        Collection<Recepcion> recepcions = recepcionRepository.obtenerRecepcion(idB, idS); // Consultar bar.
        System.out.println(recepcions.size());
        Thread.sleep(30000); // Simular operación larga para mantener el bloqueo.
        recepcions = recepcionRepository.obtenerRecepcion(idB, idS); // Consultar bar.
        return recepcions
        ;
    }
    
}
