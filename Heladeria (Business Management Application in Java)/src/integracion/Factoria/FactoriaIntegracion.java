package integracion.Factoria;

import integracion.Cliente.DAOCliente;
import integracion.Cliente.DAOClienteImp;
import integracion.Empleado.DAOEmpleado;
import integracion.Empleado.DAOEmpleadoImp;
import integracion.Facturas.DAOFacturas;
import integracion.Facturas.DAOFacturasImp;
import integracion.Facturas.DAOLineaFactura;
import integracion.Facturas.DAOLineaFacturaImp;
import integracion.Marca.DAOMarca;
import integracion.Marca.DAOMarcaImp;
import integracion.Producto.DAOProducto;
import integracion.Producto.DAOProductoImp;
import integracion.Proveedor.DAOMarcaProveedor;
import integracion.Proveedor.DAOMarcaProveedorImp;
import integracion.Proveedor.DAOProveedor;
import integracion.Proveedor.DAOProveedorImp;

public class FactoriaIntegracion extends FactoriaAbstractaIntegracion{

	@Override
	public DAOEmpleado crearDAOEmpleado() {
		return new DAOEmpleadoImp();
	}

	@Override
	public DAOProducto crearDAOProducto() {
		return new DAOProductoImp();
	}

	@Override
	public DAOMarca crearDAOMarca() {
		return new DAOMarcaImp();
	}

	@Override
	public DAOProveedor crearDAOProveedor() {
		return new DAOProveedorImp();
	}

	@Override
	public DAOMarcaProveedor crearDAOMarcaProveedor() {
		return new DAOMarcaProveedorImp();
	}

	@Override
	public DAOFacturas crearDAOFactura() {
		return new DAOFacturasImp();
	}

	@Override
	public DAOCliente crearDAOCliente() {
		return new DAOClienteImp();
	}

	@Override
	public DAOLineaFactura crearDAOLineaFactura() {
		return new DAOLineaFacturaImp();
	}

	
}
