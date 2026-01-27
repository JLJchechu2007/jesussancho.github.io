package negocio.Marca;

import java.util.Collection;

public interface SAMarca {
	public int create(TMarca tmarca);
	public TMarca read(int id);
	public int update(TMarca tmarca);
	public int delete (int id);
	public Collection<TMarca> readAll();
}
