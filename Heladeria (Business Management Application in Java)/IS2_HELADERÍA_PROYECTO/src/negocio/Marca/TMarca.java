package negocio.Marca;

public class TMarca {
	private int id;
	private String nombre;
	private String nombrejefe;
	private String lugarsede;
	private boolean activo;
	
	
	public void setId(int _id) {
		id=_id;
	}
	
	public void setActivo(boolean _activo) {
		activo=_activo;
	}
	
	public void setNombre(String _nombre) {
		nombre=_nombre;
	}
	
	public void setNombreJefe(String _jefe) {
		nombrejefe=_jefe;
	}
	
	public void setLugarSede(String _sede) {
		lugarsede=_sede;
	}
	
	public int getId() {
		return id;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public String getNombreJefe() {
		return nombrejefe;
	}
	
	public String getLugarSede() {
		return lugarsede;
	}
	
	public boolean getActivo(){
		return activo;
	}
}
