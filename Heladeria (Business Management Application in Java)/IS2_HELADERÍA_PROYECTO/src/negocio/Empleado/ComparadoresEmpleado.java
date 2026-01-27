package negocio.Empleado;

import java.util.Comparator;

public enum ComparadoresEmpleado {
	POR_APELLIDO_NOMBRE(Comparator.comparing(TransferEmpleado::getApellido, String.CASE_INSENSITIVE_ORDER).thenComparing(TransferEmpleado::getNombre,String.CASE_INSENSITIVE_ORDER)),
	POR_DNI (Comparator.comparing(TransferEmpleado::getDNI,String.CASE_INSENSITIVE_ORDER)),
	POR_SUELDO(Comparator.comparing(TransferEmpleado::getSueldo).reversed());
	
	private final Comparator<TransferEmpleado> comparador;

	ComparadoresEmpleado(Comparator<TransferEmpleado> comparador) {
		this.comparador=comparador;
	}
	public Comparator <TransferEmpleado> get(){
		return comparador;
	}
}