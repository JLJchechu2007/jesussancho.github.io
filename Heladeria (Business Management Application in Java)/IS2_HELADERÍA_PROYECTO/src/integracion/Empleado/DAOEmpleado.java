package integracion.Empleado;

import java.util.List;

import negocio.Empleado.TransferDirectivo;
import negocio.Empleado.TransferEmpleado;
import negocio.Empleado.TransferVendedor;

public interface DAOEmpleado {
	int insertar(TransferEmpleado t);
	int eliminar (String DNI);
	TransferEmpleado buscar(String DNI);
	List<TransferEmpleado> listar(); //Esto seria un TOA
	List<TransferEmpleado> listarDirectivos();
	List<TransferEmpleado> listarVendedores();
	int modificar(TransferEmpleado t);
}