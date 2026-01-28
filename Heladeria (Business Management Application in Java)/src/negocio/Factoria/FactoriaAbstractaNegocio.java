package negocio.Factoria;

import negocio.Cliente.SACliente;
import negocio.Empleado.SAEmpleado;
import negocio.Facturas.SAFacturas;
import negocio.Marca.SAMarca;
import negocio.Producto.SAProducto;
import negocio.Proveedor.SAProveedor;

public abstract class FactoriaAbstractaNegocio {
	
	private static FactoriaAbstractaNegocio instancia = null; 
	
	 public static FactoriaAbstractaNegocio getInstance() {
		 if (instancia == null)
		 instancia = new FactoriaNegocio();
		 return instancia;
		 }
	 
	 public abstract SACliente crearSACliente();
	 public abstract SAProducto crearSAProducto();
	 public abstract SAFacturas crearSAFactura();
	 public abstract SAEmpleado crearSAEmpleado();
	 public abstract SAProveedor crearSAProveedor(); 
	 public abstract SAMarca crearSAMarca();
}
