package integracion.Producto;

import java.util.Collection;

import negocio.Producto.TProducto;

public interface DAOProducto {
	public int nuevoProducto(TProducto prod);
	public TProducto read(int id);
	public int update(TProducto prod);
	public int delete(int id);
	public Collection<TProducto> readall();
	public TProducto readbyname(String tipoProducto);
	public Collection<TProducto> readallbymarca(int id);
}
