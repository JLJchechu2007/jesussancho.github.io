package negocio.Proveedor;

import java.util.ArrayList;
import java.util.Collection;

import integracion.Factoria.FactoriaAbstractaIntegracion;
import integracion.Marca.DAOMarca;
import integracion.Proveedor.DAOProveedor;
import negocio.Marca.TMarca;

public class SAProveedorImp implements SAProveedor{

	@Override
	public int create(TProveedor pr) {
		int id = -1; 
		if(pr!=null && pr.getTelefono().length()==9) {
			DAOProveedor daoProveedor = FactoriaAbstractaIntegracion.getInstance().crearDAOProveedor(); 
			TProveedor leido = daoProveedor.readByName(pr.getNombre());
			if(leido==null)
				id= daoProveedor.create(pr);
			return id; 
		}
		return id;
	}

	@Override
	public TProveedor read(int id) {
		return FactoriaAbstractaIntegracion.getInstance().crearDAOProveedor().read(id);
	}

	@Override
	public int update(TProveedor pr) {
		int id = -1;
		TProveedor tleido = read(pr.getId()); 
		if(pr!= null && (pr.getTelefono().length()==9) && tleido != null) {
			DAOProveedor daoProveedor = FactoriaAbstractaIntegracion.getInstance().crearDAOProveedor(); 
			id = daoProveedor.update(pr); 
		}
		return id;
	}

	@Override
	public int delete(int id) {
		TProveedor tleido = read(id);
		if(tleido != null) return FactoriaAbstractaIntegracion.getInstance().crearDAOProveedor().delete(id);
		return -1; 
	}

	@Override
	public Collection<TProveedor> readAll() {
		return FactoriaAbstractaIntegracion.getInstance().crearDAOProveedor().readAll();
	}

	@Override
	public boolean vinculaciones(TMarcaProveedor tMarcaProveedor) {
		DAOProveedor daoProveedor = FactoriaAbstractaIntegracion.getInstance().crearDAOProveedor(); 
		DAOMarca daoMarca = FactoriaAbstractaIntegracion.getInstance().crearDAOMarca(); 
		if((daoProveedor.read(tMarcaProveedor.getIdProveedor()) != null) && (daoMarca.read(tMarcaProveedor.getIdMarca())!= null) && (daoMarca.read(tMarcaProveedor.getIdMarca()).getActivo())) {
			return FactoriaAbstractaIntegracion.getInstance().crearDAOMarcaProveedor().vinculaciones(tMarcaProveedor); 
		}
		return false;
	}

	@Override
	public Pair<TMarca, Collection<TProveedor>> ProveedoresConMarca(int idMarca) {
		DAOMarca daoMarca = FactoriaAbstractaIntegracion.getInstance().crearDAOMarca(); 
		if(daoMarca.read(idMarca) != null && daoMarca.read(idMarca).getActivo()) {
			return new Pair<TMarca, Collection<TProveedor>>(daoMarca.read(idMarca), FactoriaAbstractaIntegracion.getInstance().crearDAOMarcaProveedor().ProveedoresConMarca(idMarca)); 
		}
		return null;
	}

	@Override
	public Collection<TProveedor> datosProveedores(Collection<TProveedor> proveedores) {
		ArrayList<TProveedor> aux = new ArrayList<TProveedor>(); 
		for(TProveedor pro: proveedores) {
			aux.add(read(pro.getId())) ;
		}
		return aux;
	}

}
