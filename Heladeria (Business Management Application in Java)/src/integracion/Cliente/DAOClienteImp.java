package integracion.Cliente;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import messages.Messages;
import negocio.Cliente.TransferCliente;

public class DAOClienteImp implements DAOCliente {
    private static final String FILE_PATH = "resources/clientes.json";

    
    //-------CONSTRUCTORES-------//    
    
    public DAOClienteImp() {}
    
    

    //-------CASOS DE USO-------//    
    
	@Override
	public int insertarCliente(TransferCliente cliente) {
		int newId = 1;
		List<TransferCliente> datosClientes = new ArrayList<>(); 
		this.cargarCliente(datosClientes);
		
		if (datosClientes.size() > 0) {
			newId = datosClientes.get(datosClientes.size() - 1).getId() + 1; 
		}
		
		cliente.setId(newId);
		datosClientes.add(cliente);
		this.guardarCliente(datosClientes);
		
		return cliente.getId();
	}

	@Override
	public boolean eleminarCliente(TransferCliente cliente) {
		int id = cliente.getId();
		List<TransferCliente> datosClientes = new ArrayList<>(); 
		this.cargarCliente(datosClientes);
		
		for (TransferCliente c : datosClientes) {
			if (c.getId() == id && c.isActivo()) {
				c.setActivo(false);
				guardarCliente(datosClientes);
				return true;
			}
		}
		
		return false;
	}

	@Override
	public TransferCliente buscarCliente(TransferCliente cliente) {
		int id = cliente.getId();
		List<TransferCliente> datosClientes = new ArrayList<>(); 
		this.cargarCliente(datosClientes);
		
		for (TransferCliente c : datosClientes) {
			if (c.getId() == id && c.isActivo()) return c;
		}
		
		return null;
	}


	@Override
	public List<TransferCliente> listarClientes() {
		List<TransferCliente> datosClientes = new ArrayList<>(); 
		this.cargarCliente(datosClientes);
		
		List<TransferCliente> activeClientes = new ArrayList<>();
		for (TransferCliente c : datosClientes) {
			if (c.isActivo()) activeClientes.add(c); 
		}
		
		return activeClientes;
	}

	@Override
	public boolean modificarCliente(TransferCliente cliente) {
		List<TransferCliente> datosClientes = new ArrayList<>(); 
		this.cargarCliente(datosClientes);
		
		for (TransferCliente c : datosClientes) {
			if (c.getId() == cliente.getId() && c.isActivo()) {
				c.setDNI(cliente.getDNI());
				c.setNombre(cliente.getNombre());
				c.setApellido(cliente.getApellido());
				c.setAge(cliente.getAge());
				this.guardarCliente(datosClientes);
				
				return true;
			}
		}
		
		return false;
	}

	
    //------- PRIVATE-------//    
	
	private void guardarCliente(List<TransferCliente> datosClientes) {
		this.sortClientes(datosClientes);
		JSONObject data = new JSONObject();
		JSONArray ja = new JSONArray();
		for (TransferCliente c : datosClientes) {
			JSONObject cliente = new JSONObject();
			cliente.put("id", c.getId());
			cliente.put("DNI", c.getDNI());
			cliente.put("nombre", c.getNombre());
			cliente.put("apellido", c.getApellido());
			cliente.put("age", c.getAge());
			cliente.put("activo", c.isActivo());
			ja.put(cliente);
		}
		
		data.put("Clientes", ja);
		try {
			FileWriter file = new FileWriter(FILE_PATH);
			file.write(data.toString(4));
			file.close();
		} catch(IOException | JSONException e) {
			System.out.println(Messages.WRITE_JSONCL_ERROR);
		}
	}
	
	
	private void cargarCliente(List<TransferCliente> datosClientes) { 
		try {
			//If the file is empty or there is nothing to be read then we end as there is nothing to read
			InputStream in = new FileInputStream(FILE_PATH);
			if(in.available() == 0) {
				in.close();
				return;
			}
			
			JSONObject clientes = new JSONObject(new JSONTokener(in));

			
			if(clientes.has("Clientes")) {
				JSONArray ja = clientes.getJSONArray("Clientes");
				for (int i = 0; i < ja.length(); ++i) {
					JSONObject jo = ja.getJSONObject(i);
					
					int id = jo.getInt("id");
					String DNI = jo.getString("DNI");
				    String nombre = jo.getString("nombre");
				    String apellido = jo.getString("apellido");
				    int age = jo.getInt("age");
				    boolean activo = jo.getBoolean("activo");
				    
				    TransferCliente tc = new TransferCliente(id, DNI, nombre, apellido, age, activo);
				    datosClientes.add(tc);
				}
				
				this.sortClientes(datosClientes);
			}
			else {
				System.out.println(Messages.NO_CLIENTES);
			}
			in.close();
			
		} catch (IOException | JSONException e) {
			System.out.println(Messages.READ_JSONCL_ERROR);
		}
	}

	private void sortClientes(List<TransferCliente> datosClientes) {
		for (int i = 1; i < datosClientes.size(); ++i) {
			int j = i - 1;
			TransferCliente aux = datosClientes.get(i);
			
			while(j >= 0 && aux.getId() < datosClientes.get(j).getId()){
				datosClientes.set(j + 1, datosClientes.get(j));
				--j;
			}
			datosClientes.set(j + 1, aux);
		}
	}
	
	
	//-------FOR OTHER SUBSISTEMS-------//    
	
	//This is a function created for facturas by facturas and is only used by them
	//it is just like the buscarCliente function but works based off of id instead of a transfer
	@Override
	public TransferCliente buscarCliente(int id) {
	    List<TransferCliente> datosClientes = new ArrayList<>();
	    this.cargarCliente(datosClientes);

	    for (TransferCliente c : datosClientes) {
	        if (c.getId() == id && c.isActivo()) return c;
	    }

	    return null;
	}
}
