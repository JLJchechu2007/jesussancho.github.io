package negocio.Empleado;

public class TransferDirectivo extends TransferEmpleado{
	
	private String cargo;
	
	public TransferDirectivo() {}

	public TransferDirectivo(int id,String DNI, String nombre, String apellidos,boolean activo, String cargo, int sueldo) {
		super(id,DNI, nombre, apellidos, Puesto.DIRECTIVO, sueldo,activo);
		this.cargo=cargo;
	}
	@Override
	public String getCargo() {
		return cargo;
	}
	
	@Override
	public int getVentas() {
		return 0;
	}
	@Override
	public void setVentas(int ventas) {}
	
	@Override
	public void setCargo(String cargo) {
		this.cargo=cargo;
	}

}
