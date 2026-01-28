package presentacion.Factoria;

import presentacion.Controlador.Eventos;
import presentacion_.IGUI;

public abstract class FactoriaAbstractaPresentacion {
	private static FactoriaAbstractaPresentacion instancia=null;
	
	public static FactoriaAbstractaPresentacion getInstance() {
		if(instancia==null) instancia=new FactoriaPresentacion();
		return instancia;
	}
	
	public abstract IGUI createVista(int idEvento);
}
