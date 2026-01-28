package messages;

public class Messages {
	//JSON error messages
	public static final String READ_JSONCL_ERROR = "Error: error reading JSONClientes file";
	public static final String WRITE_JSONCL_ERROR = "Error: error writing JSONClientes file";
	
	public static final String NO_CLIENTES = "Error: no hay clientes";

	//JOptionPanes if the operation worked or not
	public static final String CREAR_CLIENTE_CON_EXITO = "Cliente creado con exito, tiene el id:";
	public static final String ELIMINAR_CLIENTE_CON_EXITO = "Cliente eliminado con exito, tenia el id:";
	public static final String MODIFICAR_CLIENTE_CON_EXITO = "Cliente modificado con exito, tiene el id:";
	
	public static final String CREAR_CLIENTE_SIN_EXITO = "Error creadando el cliente";
	public static final String ELIMINAR_CLIENTE_SIN_EXITO = "Error eliminiando el cliente";
	public static final String MODIFICAR_CLIENTE_SIN_EXITO = "Error modificado el cliente";
	public static final String BUSCAR_CLIENTE_SIN_EXITO = "Error buscando el cliente";
	public static final String LISTAR_CLIENTE_SIN_EXITO = "No hay clientes en la lista";
	
	//Messages to show that an input was invalid
	public static final String INVALID_NOMBRE_APELLIDO = "El nombre y/o apellido que metiste no es valido";
	public static final String INVALID_AGE = "La edad que metiste no es valido";
	public static final String INVALID_ID = "El Id que metiste no es valido";
}
