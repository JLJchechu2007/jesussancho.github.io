package integracion.Proveedor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import negocio.Proveedor.TProveedor;

public class DAOProveedorImp implements DAOProveedor {
	
	@Override
	public int create(TProveedor tProveedor) {
		File file = new File("resources/proveedor.json");
		InputStream in = null; 
		try {
	     in = new FileInputStream(file);
		}
	    catch(Exception e) {
	    	System.out.println("No encuentra fichero"); 
	    }
		JSONObject jo = new JSONObject(new JSONTokener(in)); 
		int ultimoId = jo.getInt("ultimoId"); 
		jo.put("ultimoId", ultimoId+1); 
		JSONArray ja = jo.getJSONArray("lista"); 
		JSONObject proveedor = new JSONObject(); 
		proveedor.put("idProveedor", ultimoId+1); 
		proveedor.put("nombre", tProveedor.getNombre()); 
		proveedor.put("telefono", tProveedor.getTelefono()); 
		proveedor.put("correo", tProveedor.getCorreo()); 
		ja.put(proveedor); 
		try {
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		OutputStream out = null;
		try {
			out = new FileOutputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		PrintStream p = new PrintStream(out);
		p.print(jo);
		return ultimoId+1;
	}

	@Override
	public int delete(int id) {
		File file = new File("resources/proveedor.json");
	    InputStream in = null; 
		try {
			in = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		JSONObject jo = new JSONObject(new JSONTokener(in));
		JSONArray ja = jo.getJSONArray("lista"); 
		int i = 0; 
		while(i < ja.length()) {
			if(ja.getJSONObject(i).getInt("idProveedor") == id){
				ja.remove(i); 
			}
			else i++; 
		}
		try {
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		OutputStream out = null;
		try {
			out = new FileOutputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		PrintStream p = new PrintStream(out);
		p.print(jo);
		return id; 
	}

	@Override
	public TProveedor read(int id) {
		File file = new File("resources/proveedor.json");
	    InputStream in = null;
		try {
			in = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		JSONObject jo = new JSONObject(new JSONTokener(in));
		JSONArray ja = jo.getJSONArray("lista"); 
		TProveedor tProveedor = new TProveedor(); 
		int i = 0;
		boolean encontrado = false; 
		while(!encontrado && i < ja.length() ) {
			if(ja.getJSONObject(i).getInt("idProveedor") == id){
				tProveedor.setNombre(ja.getJSONObject(i).getString("nombre")); 
				tProveedor.setId(ja.getJSONObject(i).getInt("idProveedor")); 
				tProveedor.setCorreo(ja.getJSONObject(i).getString("correo")); 
				tProveedor.setTelefono(ja.getJSONObject(i).getString("telefono"));
				encontrado = true; 
			}	
			i++; 
		}
		try {
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		if(!encontrado) return null; 
		return tProveedor;
	}

	@Override
	public Collection<TProveedor> readAll() {
		ArrayList<TProveedor> aux = new ArrayList<TProveedor>(); 
		File file = new File("resources/proveedor.json");
	    InputStream in = null;
		try {
			in = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject jo = new JSONObject(new JSONTokener(in));
		JSONArray ja = jo.getJSONArray("lista"); 
		int i = 0;
		while(i < ja.length() ) {
			TProveedor tProveedor = new TProveedor(); 
			tProveedor.setNombre(ja.getJSONObject(i).getString("nombre")); 
			tProveedor.setId(ja.getJSONObject(i).getInt("idProveedor")); 
			tProveedor.setCorreo(ja.getJSONObject(i).getString("correo")); 
			tProveedor.setTelefono(ja.getJSONObject(i).getString("telefono"));
			aux.add(tProveedor); 
			i++; 
				
		}
		try {
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return aux;
	}

	@Override
	public int update(TProveedor tProveedor) {
		File file = new File("resources/proveedor.json");
		InputStream in = null; 
		try {
	     in = new FileInputStream(file);
		}
	    catch(Exception e) {
	    	System.out.println("No encuentra fichero"); 
	    }
		JSONObject jo = new JSONObject(new JSONTokener(in)); 
		int ultimoId = jo.getInt("ultimoId"); 
		jo.put("ultimoId", ultimoId+1); 
		JSONArray ja = jo.getJSONArray("lista"); 
		int i = 0;
		boolean encontrado = false; 
		while(!encontrado && i < ja.length() ) {
			if(ja.getJSONObject(i).getInt("idProveedor") == tProveedor.getId()){
				ja.getJSONObject(i).put("nombre", tProveedor.getNombre()); 
				ja.getJSONObject(i).put("correo", tProveedor.getCorreo()); 
				ja.getJSONObject(i).put("telefono", tProveedor.getTelefono());
				encontrado = true; 
			}
			i++; 
		}
		
		try {
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		OutputStream out = null;
		try {
			out = new FileOutputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		PrintStream p = new PrintStream(out);
		p.print(jo); 
		return tProveedor.getId(); 
	
	}

	@Override
	public TProveedor readByName(String name) {
		File file = new File("resources/proveedor.json");
	    InputStream in = null;
		try {
			in = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject jo = new JSONObject(new JSONTokener(in));
		JSONArray ja = jo.getJSONArray("lista"); 
		TProveedor tProveedor = new TProveedor(); 
		int i = 0;
		boolean encontrado = false; 
		while(!encontrado && i < ja.length() ) {
			if(ja.getJSONObject(i).getString("nombre") == name){
				tProveedor.setNombre(ja.getJSONObject(i).getString("nombre")); 
				tProveedor.setId(ja.getJSONObject(i).getInt("idProveedor")); 
				tProveedor.setCorreo(ja.getJSONObject(i).getString("correo")); 
				tProveedor.setTelefono(ja.getJSONObject(i).getString("telefono"));
				encontrado = true; 
			}	
			i++; 
		}
		try {
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		if(i== ja.length()) return null; 
		return tProveedor;
		
	}


}

