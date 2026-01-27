package negocio.Cliente;

public class TransferCliente {
	//Holds all of the information for a single client
	private int id;
	private String DNI;
    private String nombre;
    private String apellido;
    private int age;
    private boolean activo;
    
    
    //-------CONSTRUCTORES-------//
    
    public TransferCliente() {
    	this.id = -1;
    	this.DNI = "";
        this.nombre = "";
        this.apellido = "";
        this.age = -1;
        this.activo = true;
    }
    
    //The following 2 constructors are used to create a TransferCliente for when it is known or unknown if its active
    public TransferCliente(String DNI, String nombre, String apellido, int age) {
        this.id = -1;
        this.DNI = DNI;
        this.nombre = nombre;
        this.apellido = apellido;
        this.age = age;
        this.activo = true;
    }

    public TransferCliente(int id, String DNI, String nombre, String apellido, int age, boolean activo) {
        this.id = id;
        this.DNI = DNI;
        this.nombre = nombre;
        this.apellido = apellido;
        this.age = age;
        this.activo = activo;
    }
    
    
    //-------GETTERS-------//
    
    //The getters are used to get individual information for a client
    public int getId() { return this.id; }
    public String getDNI() { return this.DNI; }
    public String getNombre() { return this.nombre; }
    public String getApellido() { return this.apellido; }
    public int getAge() { return this.age; }
    public boolean isActivo() { return this.activo; }

    
    //-------SETTERS-------//
    
    //The setters are used to modify a client
    public void setId(int id) { this.id = id; }
    public void setDNI(String DNI) { this.DNI = DNI; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setApellido(String apellido) { this.apellido = apellido; }
    public void setAge(int age) { this.age = age; }
    public void setActivo(boolean activo) { this.activo = activo; }
}
