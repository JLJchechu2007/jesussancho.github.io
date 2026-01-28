package integracion.Proveedor;

import java.util.Collection;

import negocio.Proveedor.TProveedor;

public interface DAOProveedor {
		public int create(TProveedor tProveedor);
		public int delete(int id);
		public TProveedor read(int id);
		public Collection<TProveedor> readAll();
		public int update(TProveedor tProveedor);
		public TProveedor readByName(String name); 
}
