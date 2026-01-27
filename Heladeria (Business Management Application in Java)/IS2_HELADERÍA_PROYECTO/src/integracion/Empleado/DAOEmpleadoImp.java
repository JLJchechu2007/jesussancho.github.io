package integracion.Empleado;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import negocio.Empleado.Puesto;
import negocio.Empleado.TransferDirectivo;
import negocio.Empleado.TransferEmpleado;
import negocio.Empleado.TransferVendedor;

public class DAOEmpleadoImp implements DAOEmpleado{
	private final String archivoDirectivo="resources/directivos.json";
	private final String archivovendedor="resources/vendedores.json";
	
	public DAOEmpleadoImp() {}
	
	@Override
	public int insertar(TransferEmpleado t) {
		JSONArray array=cargarArrayAchivo(getArchivoPorTipo(t.getPuesto().toString()));
		int id;
		if(array.length()==0) {
			id=1;
		}
		else {
			JSONObject obj=(JSONObject) array.get(array.length()-1);
			int x=obj.getInt("id");
	        x++;
	        id = x;
			}
			t.setID(id);
			array.put(empleadoAJson(t));
			guardarArrayAchivo(getArchivoPorTipo(t.getPuesto().toString()),array);
			return id;
		}
	
	@Override
	public TransferEmpleado buscar(String DNI) {
		for(String archivo:List.of(this.archivoDirectivo,this.archivovendedor)) {
		JSONArray arr=cargarArrayAchivo(archivo);
		
		for(int i=0;i<arr.length();i++) {
			JSONObject obj=arr.getJSONObject(i);
			if(obj.getString("DNI").equals(DNI)) return jsonAEmpleado(obj);
		}
		
	    }
	    return null;

	}


	@Override
	public List<TransferEmpleado> listar() {
		List <TransferEmpleado> lista=new ArrayList<>();
		for(String archivo:List.of(this.archivoDirectivo,this.archivovendedor)) {
			JSONArray arr=cargarArrayAchivo(archivo);
			for(int i=0;i<arr.length();i++) {
				JSONObject obj=arr.getJSONObject(i);
				if(obj.getBoolean("activo")) lista.add(jsonAEmpleado(obj)); //Solo muestro aquellos empleados que estan activos, pero si se hace buscar empleado tambien muestro los no activos
			}
		}
		return lista;
	}
	
	private JSONArray cargarArrayAchivo(String archivo) {
		try {
			if(!Files.exists(Path.of(archivo))) return new JSONArray();
			String contenido=Files.readString(Path.of(archivo));
			return new JSONArray(contenido);
		}
		catch(IOException e) {
			return new JSONArray();
		}
	}
	
	private Boolean guardarArrayAchivo(String archivo,JSONArray arr) {
		try(FileWriter escribe=new FileWriter(archivo)){
			escribe.write(arr.toString());
			return true;
		}
		catch(IOException e) {
			return false;
		}
	}
	
	private String getArchivoPorTipo(String tipo) {
		return tipo.equalsIgnoreCase("vendedor") ? this.archivovendedor:this.archivoDirectivo;
	}
	
	private JSONObject empleadoAJson(TransferEmpleado e) {
		JSONObject obj=new JSONObject();
		obj.put("id", e.getId());
		obj.put("DNI", e.getDNI());
		obj.put("nombre", e.getNombre());
		obj.put("apellido", e.getApellido());
		obj.put("sueldo", e.getSueldo());
		obj.put("tipo", e.getPuesto().toString());
		obj.put("activo", e.estaActivo());
		if(e.getPuesto()==Puesto.DIRECTIVO) {
			obj.put("cargo", e.getCargo());
		}
		else obj.put("numVentas", e.getVentas());
		return obj;
	}
	private TransferEmpleado jsonAEmpleado(JSONObject obj) {
		int id=obj.getInt("id");
		String tipo=obj.getString("tipo");
		String DNI=obj.getString("DNI");
		String nombre=obj.getString("nombre");
		String apellido=obj.getString("apellido");
		int sueldo=obj.getInt("sueldo");
		boolean activo=obj.getBoolean("activo");
		if(tipo.equals("vendedor")) return new TransferVendedor(id,DNI,nombre,apellido,activo,obj.getInt("numVentas"),sueldo);
		else return new TransferDirectivo(id,DNI,nombre,apellido,activo,obj.getString("cargo"),sueldo);
	}


	@Override
	public int modificar(TransferEmpleado t) {
		boolean encontrado=false;
		for(String archivo:List.of(this.archivoDirectivo,this.archivovendedor)) {
			JSONArray arr=cargarArrayAchivo(archivo);
			JSONArray nuevoArr=new JSONArray();
			
			for(int i=0;i<arr.length() && !encontrado;i++) {
				JSONObject obj=arr.getJSONObject(i);
				if(!obj.getString("DNI").equals(t.getDNI())){
					nuevoArr.put(obj);
				}
				else {
					nuevoArr.put(empleadoAJson(t));
					encontrado=true;
				}
			}
			if(encontrado) 	guardarArrayAchivo(archivo,nuevoArr);
	}
		if(!encontrado) return -1;
		else return t.getId();
	}

	@Override
	public int eliminar(String DNI) {
		int dev=-1;
		for(String archivo:List.of(this.archivoDirectivo,this.archivovendedor)) {
			JSONArray arr=cargarArrayAchivo(archivo);
			JSONArray nuevoArr=new JSONArray();
			boolean encontrado=false;
			
			for(int i=0;i<arr.length() && !encontrado;i++) {
				JSONObject obj=arr.getJSONObject(i);
				if(!obj.getString("DNI").equals(DNI)){
					nuevoArr.put(obj);
				}
				else if(obj.getBoolean("activo")){
					TransferEmpleado t=jsonAEmpleado(obj);
					t.setaActivo(false);
					nuevoArr.put(this.empleadoAJson(t));
					dev=obj.getInt("id");
					encontrado=true;
				}
			}
			if(encontrado) {
				guardarArrayAchivo(archivo,nuevoArr);
			}
	}
		return dev;
	}

	@Override
	public List<TransferEmpleado> listarDirectivos() {
		List <TransferEmpleado> lista=new ArrayList<>();
			JSONArray arr=cargarArrayAchivo(this.archivoDirectivo);
			for(int i=0;i<arr.length();i++) {
				JSONObject obj=arr.getJSONObject(i);
				if(obj.getBoolean("activo"))lista.add(jsonAEmpleado(obj));
			}
		return lista;
	}

	@Override
	public List<TransferEmpleado> listarVendedores() {
		List <TransferEmpleado> lista=new ArrayList<>();
		JSONArray arr=cargarArrayAchivo(this.archivovendedor);
		for(int i=0;i<arr.length();i++) {
			JSONObject obj=arr.getJSONObject(i);
			if(obj.getBoolean("activo"))lista.add(jsonAEmpleado(obj));
		}
	return lista;
	}
}