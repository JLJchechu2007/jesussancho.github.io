package integracion.Proveedor;

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

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import negocio.Marca.TMarca;
import negocio.Proveedor.Pair;
import negocio.Proveedor.TMarcaProveedor;
import negocio.Proveedor.TProveedor;

public class DAOMarcaProveedorImp implements DAOMarcaProveedor{

	@Override
	public boolean vinculaciones(TMarcaProveedor mp) {
		File file = new File("resources/marcaproveedor.json");
		InputStream in = null; 
		try {
	     in = new FileInputStream(file);
		}
	    catch(Exception e) {
	    	System.out.println("No encuentra fichero"); 
	    }
		JSONObject jo = new JSONObject(new JSONTokener(in)); 
		JSONArray ja = jo.getJSONArray("lista"); 
		JSONObject marcaProveedor = new JSONObject(); 
		marcaProveedor.put("idProveedor", mp.getIdProveedor()); 
		marcaProveedor.put("idMarca", mp.getIdMarca()); 
		ja.put(marcaProveedor); 
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
		return true;
	}

	@Override
	public Collection<TProveedor> ProveedoresConMarca(int idMarca) {
		ArrayList<TProveedor> aux = new ArrayList<TProveedor>(); 
		File file = new File("resources/marcaproveedor.json");
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
			if(ja.getJSONObject(i).getInt("idMarca") == idMarca){
				TProveedor tProveedor = new TProveedor();  
				tProveedor.setId(ja.getJSONObject(i).getInt("idProveedor")); 
				aux.add(tProveedor); 
			}
			i++; 	
		}
		try {
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return aux;
	}

}

