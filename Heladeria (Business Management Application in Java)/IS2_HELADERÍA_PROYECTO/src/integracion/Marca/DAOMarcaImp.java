package integracion.Marca;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import negocio.Marca.TMarca;

public class DAOMarcaImp implements DAOMarca{

	@Override
	public int create(TMarca tmarca) { 
		InputStream in=null;
		File file= new File("resources/marcas.json");
	    try {
			 in= new FileInputStream(file);
		} catch (FileNotFoundException e) {}
	    JSONObject json= new JSONObject(new JSONTokener(in));
	    JSONArray marcas=json.getJSONArray("listamarcas");
    	//sumo uno al id
    	int id= json.getInt("lastid")+1;
    	json.put("lastid",id);
    	JSONObject marca= new JSONObject();
    	marca.put("idMarca",id);
    	marca.put("nombreMarca",tmarca.getNombre());
    	marca.put("nombrejefeMarca",tmarca.getNombreJefe());
    	marca.put("lugarsedeMarca",tmarca.getLugarSede());
    	marca.put("disponibilidadMarca",true);
    	marcas.put(marca);
    	try {
	    	in.close();
    	}catch(IOException e) {}
    	OutputStream out=null;
    	try {
    		out= new FileOutputStream(file);
    	}catch (FileNotFoundException e) {}
    	PrintStream p=new PrintStream(out);
		p.print(json);
	    	
	    return id;
	}

	@Override
	public int delete(int id) {
		InputStream in=null;
		File file= new File("resources/marcas.json");
	    try {
			 in= new FileInputStream(file);
		} catch (FileNotFoundException e) {}
	    JSONObject json= new JSONObject(new JSONTokener(in));
	    JSONArray marcas=json.getJSONArray("listamarcas");
	    boolean encontrado=false;
	    for(int i=0; i<marcas.length() && !encontrado;i++) {
	    	JSONObject marca= marcas.getJSONObject(i);
	    	if(marca.getInt("idMarca")==id) {
	    		marca.put("disponibilidadMarca", false); //borrado logico
	    		encontrado=true;
	    	}
	    }
	    try {
	    	in.close();
    	}catch(IOException e) {}
		OutputStream out=null;
    	try {
    		out= new FileOutputStream(file);
    	}catch (FileNotFoundException e) {}
    	PrintStream p=new PrintStream(out);
		p.print(json);
	    if (encontrado)return id;
	    else return -1;
	}

	@Override
	public TMarca read(int id) {
		TMarca tmarca=null;
		InputStream in=null;
		File file= new File("resources/marcas.json");
	    try {
			 in= new FileInputStream(file);
		} catch (FileNotFoundException e) {}
	    JSONObject json= new JSONObject(new JSONTokener(in));
	    JSONArray marcas=json.getJSONArray("listamarcas");
	    boolean b=false;
	    for(int i=0; i<marcas.length() && !b;i++) {
	    	JSONObject marca= marcas.getJSONObject(i);
	    	if(marca.getInt("idMarca")==id) {
		        tmarca= new TMarca();
		    	tmarca.setId(marca.getInt("idMarca"));
		    	tmarca.setActivo(marca.getBoolean("disponibilidadMarca"));
		    	tmarca.setNombre(marca.getString("nombreMarca"));
		    	tmarca.setNombreJefe(marca.getString("nombrejefeMarca"));
		    	tmarca.setLugarSede(marca.getString("lugarsedeMarca"));
	    	b=true;
	    	}
	    }
	    try {
	    	in.close();
    	}catch(IOException e) {}
	
		return tmarca;
	}

	@Override
	public Collection<TMarca> readAll() {
		List<TMarca> lista= new ArrayList<TMarca>();
		InputStream in=null;
		File file= new File("resources/marcas.json");
	    try {
			 in= new FileInputStream(file);
		} catch (FileNotFoundException e) {}
	    JSONObject json= new JSONObject(new JSONTokener(in));
	    JSONArray marcas=json.getJSONArray("listamarcas");
	    for(int i=0; i<marcas.length();i++) {
	    	JSONObject marca= marcas.getJSONObject(i);
	    	TMarca tmarca= new TMarca();
	    	tmarca.setId(marca.getInt("idMarca"));
	    	tmarca.setActivo(marca.getBoolean("disponibilidadMarca"));
	    	tmarca.setNombre(marca.getString("nombreMarca"));
	    	tmarca.setNombreJefe(marca.getString("nombrejefeMarca"));
	    	tmarca.setLugarSede(marca.getString("lugarsedeMarca"));
	    	if(tmarca.getActivo())lista.add(tmarca);
	    }
	    try {
	    	in.close();
    	}catch(IOException e) {}
		/*OutputStream out=null;
    	try {
    		out= new FileOutputStream(file);
    	}catch (FileNotFoundException e) {}
    	PrintStream p=new PrintStream(out);
		p.print(json);*/
	    return lista;
	}

	@Override
	public int update(TMarca tmarca) {
		InputStream in=null;
		File file= new File("resources/marcas.json");
	    try {
			 in= new FileInputStream(file);
		} catch (FileNotFoundException e) {}
	    JSONObject json= new JSONObject(new JSONTokener(in));
	    JSONArray marcas=json.getJSONArray("listamarcas");
	    boolean encontrado=false;
	    for(int i=0; i<marcas.length() && !encontrado;i++) {
	    	JSONObject marca= marcas.getJSONObject(i);
	    	if(marca.getInt("idMarca")==tmarca.getId()) {
	    		marca.put("disponibilidadMarca", tmarca.getActivo()); 
	    		marca.put("nombreMarca", tmarca.getNombre());
	    		marca.put("nombrejefeMarca", tmarca.getNombreJefe());
	    		marca.put("lugarsedeMarca", tmarca.getLugarSede());
	    		encontrado=true;
	    	}
	    }
	    try {
	    	in.close();
    	}catch(IOException e) {}
	    OutputStream out=null;
    	try {
    		out= new FileOutputStream(file);
    	}catch (FileNotFoundException e) {}
    	PrintStream p=new PrintStream(out);
		p.print(json);
		
	    if(encontrado) return tmarca.getId();
	    else return -1;
	}

	@Override
	public TMarca readByName(String name) {
		TMarca tmarca=null;
		InputStream in=null;
		File file= new File("resources/marcas.json");
	    try {
			 in= new FileInputStream(file);
		} catch (FileNotFoundException e) {}
	    JSONObject json= new JSONObject(new JSONTokener(in));
	    JSONArray marcas=json.getJSONArray("listamarcas");
	    boolean b=false;
	    for(int i=0; i<marcas.length() && !b;i++) {
	    	JSONObject marca= marcas.getJSONObject(i);
	    	if(marca.getString("nombreMarca").equals(name)) {
		        tmarca= new TMarca();
		    	tmarca.setId(marca.getInt("idMarca"));
		    	tmarca.setActivo(marca.getBoolean("disponibilidadMarca"));
		    	tmarca.setNombre(marca.getString("nombreMarca"));
		    	tmarca.setNombreJefe(marca.getString("nombrejefeMarca"));
		    	tmarca.setLugarSede(marca.getString("lugarsedeMarca"));
		    	b=true;
	    	}
	    }
	    try {
	    	in.close();
    	}catch(IOException e) {}
	
		return tmarca;
	}

}

