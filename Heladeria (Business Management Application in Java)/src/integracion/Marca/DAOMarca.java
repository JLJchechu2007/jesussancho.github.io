package integracion.Marca;

import java.util.Collection;

import negocio.Marca.TMarca;

public interface DAOMarca {
	public int create(TMarca tmarca);
	public int delete(int id);
	public TMarca read(int id);
	public Collection<TMarca> readAll();
	public int update(TMarca tmarca);
	public TMarca readByName(String name);
}