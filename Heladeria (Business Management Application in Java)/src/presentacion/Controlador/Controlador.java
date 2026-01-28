package presentacion.Controlador;

public abstract class Controlador {
	 static Controlador instancia=null;
	  public static Controlador getInstancia() {
	        if (instancia == null) {
	            instancia = new ControladorImp();
	        }
	        return instancia;
	    }

    public abstract void accion(int evento, Object datos);

}

