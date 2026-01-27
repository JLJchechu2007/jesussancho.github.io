package negocio.Empleado;

public class TransferVendedor extends TransferEmpleado{
	
	private int numVentas;
	
	public TransferVendedor() {}

	public TransferVendedor(int id,String DNI, String nombre, String apellidos, boolean activo, int ventas, int sueldo) {
		super(id,DNI, nombre, apellidos, Puesto.VENDEDOR, sueldo,activo);
		numVentas=ventas;
		
	}

	@Override
	public int getVentas() {
		return numVentas;
	}

	@Override
	public String getCargo() {
		return null;
	}

	@Override
	public void setVentas(int ventas) {
		this.numVentas=ventas;
		
	}

	@Override
	public void setCargo(String cargo) {}
}
