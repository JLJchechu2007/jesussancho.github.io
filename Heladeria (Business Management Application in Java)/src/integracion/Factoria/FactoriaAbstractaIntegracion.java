package integracion.Factoria;

import integracion.Cliente.DAOCliente;
import integracion.Empleado.DAOEmpleado;
import integracion.Facturas.DAOFacturas;
import integracion.Facturas.DAOLineaFactura;
import integracion.Marca.DAOMarca;
import integracion.Producto.DAOProducto;
import integracion.Proveedor.DAOMarcaProveedor;
import integracion.Proveedor.DAOProveedor;

public abstract class FactoriaAbstractaIntegracion {
	private static FactoriaAbstractaIntegracion instancia;

    public static FactoriaAbstractaIntegracion getInstance() {
        if (instancia == null)
            instancia = new FactoriaIntegracion();
        return instancia;
    }

    public abstract DAOEmpleado crearDAOEmpleado();
    public abstract DAOProducto crearDAOProducto();
    public abstract DAOMarca crearDAOMarca();
    public abstract DAOProveedor crearDAOProveedor(); 
    public abstract DAOMarcaProveedor crearDAOMarcaProveedor();
    public abstract DAOFacturas crearDAOFactura();
    public abstract DAOLineaFactura crearDAOLineaFactura();
    public abstract DAOCliente crearDAOCliente();
}
