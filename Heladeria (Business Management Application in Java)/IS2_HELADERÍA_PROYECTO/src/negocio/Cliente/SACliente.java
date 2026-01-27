package negocio.Cliente;

import java.util.List;

public interface SACliente {
	//This is used to create a new client and returns its ID if its created or -1 if there is an error
    int altaCliente(TransferCliente cliente);
    //This is a Borrador Logico where it finds the id of the client and sets activo to false
    boolean bajaCliente(TransferCliente cliente); 
    //This searches for a client and returns its data or null if it doesn't exist
    TransferCliente buscarCliente(TransferCliente cliente);
    //This returns a list of the clients that can then be used in the presentation to show the full list
    List<TransferCliente> listarClientes();
    //This searches to see if the client
    TransferCliente modificarClienteInitial(TransferCliente cliente);
    //This is the second step to modify a client
    boolean modificarClienteFinal(TransferCliente cliente);
}
