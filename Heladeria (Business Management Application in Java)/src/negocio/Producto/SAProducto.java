package negocio.Producto;

import java.util.Collection;

public interface SAProducto {
	public int nuevoProducto(TProducto prod);
	public TProducto read(int id);
	public int update(TProducto prod);
	public int delete(int id);
	public Collection<TProducto> readall();
	public Collection<TProducto> readallbymarca(int id);
}