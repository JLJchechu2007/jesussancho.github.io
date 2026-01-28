package negocio.Producto;

import java.util.Collection;

import integracion.Factoria.FactoriaAbstractaIntegracion;
import integracion.Marca.DAOMarca;
import integracion.Producto.DAOProducto;
import negocio.Marca.TMarca;

public class SAProductoImp implements SAProducto {

	@Override
	public int nuevoProducto(TProducto prod) {
	  int id=-1;
	  if(prod!=null && prod.getCantidad()>=0 && prod.getPrecioProducto()>=0) {
		  if(prod.getIdMarca()!=-1) {
				DAOMarca daomarca = FactoriaAbstractaIntegracion.getInstance().crearDAOMarca();
			    TMarca marca=daomarca.read(prod.getIdMarca());
			    if(marca==null)return id;
			}
		DAOProducto daoproducto= FactoriaAbstractaIntegracion.getInstance().crearDAOProducto();
		TProducto leido = daoproducto.readbyname(prod.getTipoProducto());
		if(leido==null)id=daoproducto.nuevoProducto(prod);
		else if(!leido.isAlta()) {
		prod.setId(leido.getId());
		prod.setAlta(true);
		id=daoproducto.update(prod);
		}
		else {
			prod.setId(leido.getId());
			prod.setCantidad(prod.getCantidad()+leido.getCantidad());
			id=daoproducto.update(prod);
		}
	 }
	  return id;
	}

	@Override
	public TProducto read(int id) {
		TProducto leido;
		DAOProducto daoproducto= FactoriaAbstractaIntegracion.getInstance().crearDAOProducto();
		leido= daoproducto.read(id);
		if(leido!=null && !leido.isAlta())leido=null;
		return leido;
	}

	@Override
	public int update(TProducto prod) {
		int id=-1;
		if(prod!=null) {
		TProducto leido=read(prod.getId());
		if(leido!=null && prod.getCantidad()>=0 && prod.getPrecioProducto()>=0 && leido.isAlta()) {
		if(prod.getIdMarca()!=-1) {
			DAOMarca daomarca = FactoriaAbstractaIntegracion.getInstance().crearDAOMarca();
		    TMarca marca=daomarca.read(prod.getIdMarca());
		    if(marca==null)return id;
		} 
		DAOProducto daoproducto= FactoriaAbstractaIntegracion.getInstance().crearDAOProducto();
		id=daoproducto.update(prod);
		 }
		}
		return id;
	}

	@Override
	public int delete(int id) {
		int id2=-1;
		TProducto leido=read(id);
		if(leido!=null) {
		DAOProducto daoproducto= FactoriaAbstractaIntegracion.getInstance().crearDAOProducto();
		id2=daoproducto.delete(id);
		}
		return id2;
	}

	@Override
	public Collection<TProducto> readall() {
		Collection<TProducto> leido;
		DAOProducto daoproducto= FactoriaAbstractaIntegracion.getInstance().crearDAOProducto();
		leido=daoproducto.readall();
		if(leido.size()==0)return null;
		return leido;
	}

	@Override
	public Collection<TProducto> readallbymarca(int id) {
		Collection<TProducto> leido;
		DAOProducto daoproducto= FactoriaAbstractaIntegracion.getInstance().crearDAOProducto();
		leido=daoproducto.readallbymarca(id);
		if(leido.size()==0)return null;
		return leido;
	}

}