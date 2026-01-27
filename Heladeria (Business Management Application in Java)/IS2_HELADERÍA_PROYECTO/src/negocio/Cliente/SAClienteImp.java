package negocio.Cliente;

import java.util.List;

import integracion.Cliente.*;
import integracion.Factoria.FactoriaAbstractaIntegracion;

public class SAClienteImp implements SACliente {
	
    //-------CONSTRUCTORES-------//  
	
	public SAClienteImp() {}
	
	
    //-------CASOS DE USO-------//
	
	@Override
	public int altaCliente(TransferCliente cliente) {
		if(cliente == null || !this.validDNI(cliente.getDNI())) return -1;
		
		DAOCliente daoClientes = FactoriaAbstractaIntegracion.getInstance().crearDAOCliente();
		List<TransferCliente> clientes = daoClientes.listarClientes();
		for (TransferCliente c : clientes) { if(c.getDNI().equalsIgnoreCase(cliente.getDNI())) return -1; }
		
		return daoClientes.insertarCliente(cliente);
	}

	@Override
	public boolean bajaCliente(TransferCliente cliente) {
		if (cliente == null || cliente.getId() <= 0) return false;
		return FactoriaAbstractaIntegracion.getInstance().crearDAOCliente().eleminarCliente(cliente);
	}

	@Override
	public TransferCliente buscarCliente(TransferCliente cliente) {
		if (cliente == null || cliente.getId() <= 0) return null;
		return FactoriaAbstractaIntegracion.getInstance().crearDAOCliente().buscarCliente(cliente);
	}

	@Override
	public List<TransferCliente> listarClientes() {
		return FactoriaAbstractaIntegracion.getInstance().crearDAOCliente().listarClientes();
	}

	@Override
	public TransferCliente modificarClienteInitial(TransferCliente cliente) {
		if (cliente == null || cliente.getId() <= 0) return null;
		return FactoriaAbstractaIntegracion.getInstance().crearDAOCliente().buscarCliente(cliente);
	}


	@Override
	public boolean modificarClienteFinal(TransferCliente cliente) {
		if (cliente == null || cliente.getId() <= 0 || !this.validDNI(cliente.getDNI())) return false;
		
		DAOCliente daoClientes = FactoriaAbstractaIntegracion.getInstance().crearDAOCliente();
		List<TransferCliente> clientes = daoClientes.listarClientes();
		for (TransferCliente c : clientes) { 
			if(c.getDNI().equalsIgnoreCase(cliente.getDNI()) && c.getId() != cliente.getId()) return false; 
		}
		
		return daoClientes.modificarCliente(cliente);
	}
	
	
    //-------PRIVATE-------//
	
	private boolean validDNI(String dni) {
		if(dni.length() != 9) return false;
		
		String dniNum = dni.substring(0, 8);
		try { Integer.parseInt(dniNum); } 
		catch (NumberFormatException e) { return false; }
		
		char dniLetter = dni.charAt(8);
		if (dniLetter >= 'A' && dniLetter <= 'Z') return true;
		else return false;
	}
	
}
