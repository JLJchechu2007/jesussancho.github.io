package negocio.Proveedor;

import java.util.Collection;

import negocio.Marca.TMarca;

public interface SAProveedor {
	public int create(TProveedor pr);
	public TProveedor read(int id);
	public int update(TProveedor pr);
	public int delete (int id);
	public Collection<TProveedor> readAll();
	public boolean vinculaciones(TMarcaProveedor mp); 
	public Pair<TMarca, Collection<TProveedor>> ProveedoresConMarca(int idMarca); 
	public Collection<TProveedor> datosProveedores(Collection<TProveedor> proveedores); 
}
