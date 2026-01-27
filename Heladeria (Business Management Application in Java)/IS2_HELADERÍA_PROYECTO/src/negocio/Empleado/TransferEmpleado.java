package negocio.Empleado;

public abstract class TransferEmpleado {
	protected int id;
	protected String DNI;
	protected String nombre;
	protected String apellidos;
	protected Puesto puesto;
	protected int sueldo;
	protected boolean activo; //Borrado logico
	
	public TransferEmpleado() {}
	
	public TransferEmpleado(int id,String DNI,String nombre,String apellidos,Puesto puesto, int sueldo, boolean activo) {
		this.id=id;
		this.DNI=DNI;
		this.nombre=nombre;
		this.apellidos=apellidos;
		this.puesto=puesto;
		this.sueldo=sueldo;
		this.activo=activo;
	}

	//getters
	public int getSueldo() {return sueldo;}
	public String getDNI() {return DNI;}
	public String getNombre() {return nombre;}
	public String getApellido() {return apellidos;}
	public Puesto getPuesto() {return puesto;}
	public boolean estaActivo() {return activo;}
	public int getId() {return id;}
	
	//setters
	public void setSueldo(int sueldo) {this.sueldo=sueldo;}
	public void setDNI(String dni) {this.DNI=dni;}
	public void setNombre(String nombre) {this.nombre=nombre;}
	public void setApellido(String apellido) {this.apellidos=apellido;}
	public void setPuesto(Puesto p) {this.puesto=p;}
	public void setaActivo(boolean esta) {this.activo=esta;}
	public void setID(int id){this.id=id;}
	
	//setters y getters abstractos
	abstract public int getVentas();
	abstract public String getCargo();
	abstract public void setVentas(int ventas);
	abstract public void setCargo(String cargo);

}