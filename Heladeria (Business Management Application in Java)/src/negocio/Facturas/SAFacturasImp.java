package negocio.Facturas;

import java.util.ArrayList;
import java.util.List;
import static presentacion.GUIFacturas.carrito.carrito;//Para poder gestionar el carrito hasta que se cree la factura

import integracion.Cliente.DAOCliente;
import integracion.Cliente.DAOClienteImp;
import integracion.Empleado.DAOEmpleado;
import integracion.Empleado.DAOEmpleadoImp;
import integracion.Factoria.FactoriaAbstractaIntegracion;
import integracion.Facturas.DAOFacturas;
import integracion.Facturas.DAOFacturasImp;
import integracion.Facturas.DAOLineaFactura;
import integracion.Producto.DAOProducto;
import integracion.Producto.DAOProductoImp;
import negocio.Producto.TProducto;

public class SAFacturasImp implements SAFacturas {

	//Guardado como atributo por comodidad, dado que se usa en todos los métodos prácticamente
	private DAOFacturas daoFacturas = FactoriaAbstractaIntegracion.getInstance().crearDAOFactura();

	
	 private String mensajeError = "";
	 
	 
	 @Override
	    public String getMensajeError() {
	        return mensajeError;
	    }

	    private void setMensajeError(String mensaje) {
	        this.mensajeError = mensaje;
	        System.out.println(mensaje);  // si aún quieres que salga en consola
	    }
	
    @Override
	public int abrirVenta() {//Solamente se inicializa el carrito
    	if (carrito != null && !carrito.isEmpty()) {
    		setMensajeError("Ya hay una venta abierta.");
    	    return -1;
    	}
    	carrito=new ArrayList<TLineaFactura>();
    	System.out.println("Carrito inicializado");
    	return 0;//De momento no hay factura creada cuyo id devolver, se creará al cerrarVenta
	}
    
    @Override
    public boolean añadirProducto(TLineaFactura linea) {
        // 1. Compruebo que existe el producto a añadir
    	DAOProducto daoProducto = FactoriaAbstractaIntegracion.getInstance().crearDAOProducto();
        TProducto producto = daoProducto.read(linea.getIdProducto());

        if (producto == null) {
        	setMensajeError("No hay ningún producto registrado con ese ID.");
            return false;
        }

        // 2. Verifico el stock
        int stockDisponible = producto.getCantidad();
        int cantidadDeseada = linea.getCantidad();

        if (stockDisponible <= 0) {
        	setMensajeError("No hay stock disponible para el producto con ID: " + linea.getIdProducto());
            return false;
        }

        if (cantidadDeseada > stockDisponible) {
        	setMensajeError("Stock insuficiente. Solo se añadirán " + stockDisponible + " unidades al carrito.");
            linea.setCantidad(stockDisponible); // ajustar cantidad
        }

      //El id de la factura se agregará al cerrar la venta y obtenerlo, de momento no es relevante

        // 3. Añado la nueva línea al carrito
        carrito.add(linea);
        System.out.println("Producto con id: "+ producto.getId() + "añadido en: " + linea.getCantidad() + " cantidad" +
        "en la linea: " + linea.getIdLinea() + " con precio: " + linea.getPrecio() + "Tamaño del carrito: " + carrito.size());
        return true;
    }

    
    @Override
    public int cerrarVenta(TFacturas f) {
        if (carrito == null || carrito.isEmpty()) {
        	setMensajeError("El carrito está vacío. No se puede cerrar la venta.");
            return -1;
        }

        DAOCliente daoCliente = FactoriaAbstractaIntegracion.getInstance().crearDAOCliente();
        DAOEmpleado daoVendedor = FactoriaAbstractaIntegracion.getInstance().crearDAOEmpleado();

        if (daoCliente.buscarCliente(f.getIdCliente()) == null) {
        	setMensajeError("No hay ningún cliente registrado con ese id");
            return -1;
        }

        if (daoVendedor.buscar(f.getIdVendedor()) == null) {
        	setMensajeError("No hay ningún vendedor registrado con ese id");
            return -1;
        }
        System.out.println("Cliente registrado: " + daoCliente.buscarCliente(f.getIdCliente()) + "Vendedor registrado: " + daoVendedor.buscar(f.getIdVendedor()) );
        String fecha = java.time.LocalDate.now().toString();
        f.setFecha(fecha);
        
        float importeTotal = 0;
        for (TLineaFactura l : carrito)
            importeTotal += l.getCantidad() * l.getPrecio();
        f.setImporte(importeTotal);
        f.setActivo(true);
        
        System.out.println("Importe total = " + importeTotal);
        int id = daoFacturas.insertar(f);
        if (id <= 0) return -1;
        System.out.println("idFactura  = " + id);
        
        DAOLineaFactura daoLinea = FactoriaAbstractaIntegracion.getInstance().crearDAOLineaFactura();
        DAOProducto daoProducto = FactoriaAbstractaIntegracion.getInstance().crearDAOProducto();

        for (TLineaFactura l : carrito) {
            TProducto p = daoProducto.read(l.getIdProducto());
            if (p.getCantidad() < l.getCantidad()) {
            	setMensajeError("Stock insuficiente para el producto con id " + p.getId());
                return -1;
            }

            l.setIdFactura(id);
            int lineaId = daoLinea.insertar(l);
            if (lineaId > 0) {
                p.setCantidad(p.getCantidad() - l.getCantidad());
                daoProducto.update(p);
                l.setIdLinea(lineaId);
            } else {
            	setMensajeError("No se ha podido añadir el producto con id " + l.getIdProducto() + " a la venta");
                return -1;
            }
        }

        f.setLineas(carrito);
        daoFacturas.actualizar(f);
        carrito.clear();

        return id;
    }


    
    @Override
    public List<TFacturas> mostrarfacturas() {
        return daoFacturas.listar();
    }
    
    @Override
    public TFacturas buscarFactura(int id) {
        return daoFacturas.buscar(id);
    }

	@Override
	public List<TFacturas> buscarFacturasPorCliente(int idCliente) {
		return daoFacturas.buscarPorCliente(idCliente);
	}
}
