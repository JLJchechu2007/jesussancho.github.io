package negocio.Marca;

import java.util.Collection;

import integracion.Factoria.FactoriaAbstractaIntegracion;
import integracion.Marca.DAOMarca;

public class SAMarcaImp implements SAMarca{
	@Override
	public int create(TMarca tmarca) {
		int id=-1;
		if(tmarca!=null) {
			DAOMarca daomarca= FactoriaAbstractaIntegracion.getInstance().crearDAOMarca();
			TMarca leido= daomarca.readByName(tmarca.getNombre());
			if(leido==null) id=daomarca.create(tmarca);
			else {
				tmarca.setId(leido.getId());
				tmarca.setActivo(true);
				id=daomarca.update(tmarca);
			}
		}
		return id;
	}

	@Override
	public TMarca read(int id) {
		DAOMarca daomarca= FactoriaAbstractaIntegracion.getInstance().crearDAOMarca();
		TMarca marca= daomarca.read(id);
		if(marca ==null || marca.getActivo()==false)return null;
		else return marca;
	}

	@Override
	public int update(TMarca tmarca) {
		int id=-1;
		if(tmarca!=null) {
			TMarca leido= read(tmarca.getId());
			if(leido!=null && leido.getActivo()) {
				DAOMarca daomarca= FactoriaAbstractaIntegracion.getInstance().crearDAOMarca();
				id=daomarca.update(tmarca);
			}
		}
		return id;
	}

	@Override
	public int delete(int id) {
		int _id=-1;
		DAOMarca daomarca= FactoriaAbstractaIntegracion.getInstance().crearDAOMarca();
		TMarca leido= daomarca.read(id);
		if(leido!=null && leido.getActivo()) {
			_id=daomarca.delete(id);
		}
		return _id;
	}

	@Override
	public Collection<TMarca> readAll() {
		DAOMarca daomarca= FactoriaAbstractaIntegracion.getInstance().crearDAOMarca();
		return daomarca.readAll();
	}

}
