package negocio.Proveedor;

public class TProveedor {
	private int idProveedor; 
	private String nombre; 
	private String telefono; 
	private String correo;
	
	public TProveedor() {
		nombre = ""; 
		telefono = ""; 
		correo = ""; 
		idProveedor = -1; 
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public int getId() {
		return idProveedor;
	}
	public void setId(int id) {
		this.idProveedor = id;
	} 
	
}