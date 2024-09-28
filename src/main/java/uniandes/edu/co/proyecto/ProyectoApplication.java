package uniandes.edu.co.proyecto;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import uniandes.edu.co.proyecto.modelo.Bodega;
import uniandes.edu.co.proyecto.modelo.Categoria;
import uniandes.edu.co.proyecto.modelo.Ciudad;
import uniandes.edu.co.proyecto.modelo.Empaque;
import uniandes.edu.co.proyecto.modelo.OrdenCompra;
import uniandes.edu.co.proyecto.modelo.Producto;
import uniandes.edu.co.proyecto.modelo.ProductoBodega;
import uniandes.edu.co.proyecto.modelo.ProductoCompra;
import uniandes.edu.co.proyecto.modelo.ProductoProveedor;
import uniandes.edu.co.proyecto.modelo.Proveedor;
import uniandes.edu.co.proyecto.modelo.Recepcion;
import uniandes.edu.co.proyecto.modelo.Sucursal;
import uniandes.edu.co.proyecto.repositorio.BodegaRepository;
import uniandes.edu.co.proyecto.repositorio.CategoriaRepository;
import uniandes.edu.co.proyecto.repositorio.CiudadRepository;
import uniandes.edu.co.proyecto.repositorio.EmpaqueRepository;
import uniandes.edu.co.proyecto.repositorio.OrdenCompraRepository;
import uniandes.edu.co.proyecto.repositorio.ProductoBodegaRepository;
import uniandes.edu.co.proyecto.repositorio.ProductoCompraRepository;
import uniandes.edu.co.proyecto.repositorio.ProductoProveedorRepository;
import uniandes.edu.co.proyecto.repositorio.ProductoRepository;
import uniandes.edu.co.proyecto.repositorio.ProveedorRepository;
import uniandes.edu.co.proyecto.repositorio.RecepcionRepository;
import uniandes.edu.co.proyecto.repositorio.SucursalRepository;

@SpringBootApplication
public class ProyectoApplication implements CommandLineRunner{

	@Autowired
	private ProductoRepository productoRepository;
	@Autowired
	private BodegaRepository bodegaRepository;
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private CiudadRepository ciudadRepository;
	@Autowired
	private EmpaqueRepository empaqueRepository;
	@Autowired
	private OrdenCompraRepository ordenCompraRepository;
	@Autowired
	private ProductoBodegaRepository productoBodegaRepository;
	@Autowired
	private ProductoCompraRepository productoCompraRepository;
	@Autowired
	private ProductoProveedorRepository productoProveedorRepository;
	@Autowired
	private ProveedorRepository proveedorRepository;
	@Autowired
	private RecepcionRepository recepcionRepository;
	@Autowired
	private SucursalRepository sucursalRepository;

	public static void main(String[] args) {
		SpringApplication.run(ProyectoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Collection<Producto> productos = productoRepository.darProductos();
		for(Producto p: productos){
			System.out.println(p);
			
		}

		Collection<Bodega> bodegas = bodegaRepository.darBodegas();
		for(Bodega p: bodegas){
			System.out.println(p);
			
		}

		Collection<Categoria> categorias = categoriaRepository.darCategorias();
		for(Categoria p: categorias){
			System.out.println(p);
			
		}

		// Itera y muestra todas las ciudades
		Collection<Ciudad> ciudades = ciudadRepository.findAll();
		for (Ciudad c : ciudades) {
			System.out.println(c);
		}

		// Itera y muestra todos los empaques
		Collection<Empaque> empaques = empaqueRepository.findAll();
		for (Empaque e : empaques) {
			System.out.println(e);
		}

		// Itera y muestra todas las órdenes de compra
		Collection<OrdenCompra> ordenesCompra = ordenCompraRepository.findAll();
		for (OrdenCompra o : ordenesCompra) {
			System.out.println(o);
		}

		// Itera y muestra todas las relaciones Producto-Bodega
		Collection<ProductoBodega> productosBodega = productoBodegaRepository.findAll();
		for (ProductoBodega pb : productosBodega) {
			System.out.println(pb);
		}

		// Itera y muestra todas las relaciones Producto-Compra
		Collection<ProductoCompra> productosCompra = productoCompraRepository.findAll();
		for (ProductoCompra pc : productosCompra) {
			System.out.println(pc);
		}

		// Itera y muestra todas las relaciones Producto-Proveedor
		Collection<ProductoProveedor> productosProveedor = productoProveedorRepository.findAll();
		for (ProductoProveedor pp : productosProveedor) {
			System.out.println(pp);
		}

		// Itera y muestra todos los proveedores
		Collection<Proveedor> proveedores = proveedorRepository.findAll();
		for (Proveedor p : proveedores) {
			System.out.println(p);
		}

		// Itera y muestra todas las recepciones
		Collection<Recepcion> recepciones = recepcionRepository.findAll();
		for (Recepcion r : recepciones) {
			System.out.println(r);
		}

		// Itera y muestra todas las sucursales
		Collection<Sucursal> sucursales = sucursalRepository.findAll();
		for (Sucursal s : sucursales) {
			System.out.println(s);
		}

	}
}
