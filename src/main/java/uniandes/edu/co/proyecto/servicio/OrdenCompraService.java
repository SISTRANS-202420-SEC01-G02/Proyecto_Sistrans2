package uniandes.edu.co.proyecto.servicio;

import java.util.Collection;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.Bodega;
import uniandes.edu.co.proyecto.modelo.ProductoCompra;
import uniandes.edu.co.proyecto.modelo.Proveedor;
import uniandes.edu.co.proyecto.modelo.Sucursal;
import uniandes.edu.co.proyecto.repositorio.OrdenCompraRepository;

@Service
public class OrdenCompraService {


    private OrdenCompraRepository ordenCompraRepository;

    public OrdenCompraService(OrdenCompraRepository ordenCompraRepository) {

        this.ordenCompraRepository = ordenCompraRepository;

    }

    @Transactional(isolation = Isolation.SERIALIZABLE, readOnly = true)
    public Sucursal darSucursal (int id_orden) throws InterruptedException{
        Sucursal sucursal = ordenCompraRepository.darSucursalOrdenCompra(id_orden);
        return sucursal;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE, readOnly = true)
    public Collection<Bodega> darBodegas (int id_orden){
        Collection<Bodega> bodegas = ordenCompraRepository.darBodegasSucursalOrdenCompra(id_orden);
        return bodegas;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE, readOnly = true)
    public Proveedor darProveedor(int id_orden){
        Proveedor proveedor = ordenCompraRepository.darProveedorOrdenCompra(id_orden);
        return proveedor;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE, readOnly = true)
    public Collection<ProductoCompra> darProductos(int id_orden){
        Collection<ProductoCompra> productos = ordenCompraRepository.darProductosOrdenCompra(id_orden);
        return productos;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
    public void actualizarOrdenCompra(int id_orden){
        try{
            ordenCompraRepository.actualizarOrdenCompra(id_orden, "Entregada");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    
}
