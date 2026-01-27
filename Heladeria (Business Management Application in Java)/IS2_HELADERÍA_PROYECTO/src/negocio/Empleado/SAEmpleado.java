package negocio.Empleado;

import java.util.Comparator;
import java.util.List;

public interface SAEmpleado {
	int altaEmpleado(TransferEmpleado t);
	int bajaEmpleado(String DNI);
	int modificar(TransferEmpleado t);
	
	List<TransferEmpleado> mostrarEmpleados();
	List<TransferEmpleado> mostrarVendedores();
	List<TransferEmpleado> mostrarDirectivos();
	
	TransferEmpleado buscarEmpleado(String DNI);
	
	List<TransferEmpleado> ordenarEmpleados(Comparator<TransferEmpleado> comparador);
}
