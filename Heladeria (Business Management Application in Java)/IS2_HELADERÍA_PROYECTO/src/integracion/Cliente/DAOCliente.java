package integracion.Cliente;

import negocio.Cliente.TransferCliente;
import java.util.List;

public interface DAOCliente {
	int insertarCliente(TransferCliente cliente);
	boolean eleminarCliente(TransferCliente cliente);
	TransferCliente buscarCliente(TransferCliente cliente);
	List<TransferCliente> listarClientes();
    boolean modificarCliente(TransferCliente cliente);
	//for Facturas
    TransferCliente buscarCliente(int id);
}
