package negocio.Factoria;

import negocio.Cliente.*;
import negocio.Empleado.SAEmpleado;
import negocio.Empleado.SAEmpleadoImp;
import negocio.Facturas.*;
import negocio.Marca.SAMarca;
import negocio.Marca.SAMarcaImp;
import negocio.Producto.*;
import negocio.Proveedor.SAProveedor;
import negocio.Proveedor.SAProveedorImp;

public class FactoriaNegocio extends FactoriaAbstractaNegocio{

	@Override
	public SACliente crearSACliente() {
		return  new SAClienteImp();
	}

	@Override
	public SAProducto crearSAProducto() {
		return new SAProductoImp();
	}

	@Override
	public SAFacturas crearSAFactura() {
		return  new SAFacturasImp();
	}

	@Override
	public SAEmpleado crearSAEmpleado() {
		return  new SAEmpleadoImp();
	}

	@Override
	public SAProveedor crearSAProveedor() {
		return new SAProveedorImp();
	}

	@Override
	public SAMarca crearSAMarca() {
		return new SAMarcaImp();
	}

}
