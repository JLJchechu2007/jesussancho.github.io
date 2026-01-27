package integracion.Proveedor;

import java.util.Collection;

import negocio.Marca.TMarca;
import negocio.Proveedor.Pair;
import negocio.Proveedor.TMarcaProveedor;
import negocio.Proveedor.TProveedor;

public interface DAOMarcaProveedor {
	public boolean vinculaciones(TMarcaProveedor mp);
	public Collection<TProveedor> ProveedoresConMarca(int idMarca); 
}
