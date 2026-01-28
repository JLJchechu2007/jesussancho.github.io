package integracion.Producto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import negocio.Producto.TProducto;

public class DAOProductoImp implements DAOProducto {

	@Override
	public int nuevoProducto(TProducto prod) {
		File file= new File("resources/productos.json");
		InputStream in; int lastid=0;
	    try {
		in = new FileInputStream(file);
	    JSONObject jo= new JSONObject(new JSONTokener(in));
	    lastid= jo.getInt("ultimoId");
	    jo.put("ultimoId",lastid+1);
		JSONArray productos=jo.getJSONArray("productos");
        JSONObject ultprod= new JSONObject();
        ultprod.put("idProducto",lastid+1);
        ultprod.put("TipoDeProducto",prod.getTipoProducto());
        ultprod.put("cantidadProducto",prod.getCantidad());
        ultprod.put("precioProducto",prod.getPrecioProducto());
        ultprod.put("idMarca",prod.getIdMarca());
        ultprod.put("disponibilidad",prod.isAlta());
        productos.put(ultprod);
        in.close();
        OutputStream out= new FileOutputStream(file);
        PrintStream p=new PrintStream(out);
        p.print(jo);
	    } catch (FileNotFoundException e) {}
	      catch(IOException e) {}
	    return lastid+1;
	}

	@Override 
	public TProducto read(int id) {
		TProducto tp = null; 
		File file= new File("resources/productos.json");
		InputStream in;
	    try {
		in= new FileInputStream(file);
	    JSONObject jo= new JSONObject(new JSONTokener(in));
	    JSONArray productos=jo.getJSONArray("productos");
	    boolean b=false;
	    for(int i=0; i<productos.length() && !b;i++) {
	    	JSONObject ob= productos.getJSONObject(i);
	    	if(ob.getInt("idProducto")==id) {
	    		tp=new TProducto();
	    		tp.setId(id);
	    		tp.setTipoProducto(ob.getString("TipoDeProducto"));
	    		tp.setCantidad(ob.getInt("cantidadProducto"));
	    		tp.setPrecioProducto(ob.getFloat("precioProducto"));
	    		tp.setIdMarca(ob.getInt("idMarca"));
	    		tp.setAlta(ob.getBoolean("disponibilidad"));
	    		b=true;
	    	}
	    }
	    in.close();
	    } catch (FileNotFoundException e) {}
	    catch(IOException e) {}
		return tp; 
 }

	@Override
	public int update(TProducto prod) {
		File file= new File("resources/productos.json");
		InputStream in;
	    try {
		in= new FileInputStream(file);
	    JSONObject jo= new JSONObject(new JSONTokener(in));
	    JSONArray productos=jo.getJSONArray("productos");
	    boolean b=false;
	    for(int i=0; i<productos.length() && !b;i++) {
	    	JSONObject ob= productos.getJSONObject(i);
	    	if(ob.getInt("idProducto")==prod.getId()) {
	    		if(prod.getTipoProducto()!="") ob.put("tipoProducto",prod.getTipoProducto());
	    		if(prod.getCantidad()!=-1)ob.put("cantidadProducto",prod.getCantidad());
	    		if(prod.getPrecioProducto()!=-1)ob.put("precioProducto",prod.getPrecioProducto());
	    		if(prod.getIdMarca()!=-1) ob.put("idMarca",prod.getIdMarca());
	    		ob.put("disponibilidad",prod.isAlta());
	    		b=true;
	    	}
	    }
	    in.close();
        OutputStream out= new FileOutputStream(file);
        PrintStream p=new PrintStream(out);
        p.print(jo);
	    } catch (FileNotFoundException e) {}
	      catch(IOException e) {}
	    return prod.getId();
	}

	@Override
	public int delete(int id) {
		File file= new File("resources/productos.json");
		InputStream in;
	    try {
	     in= new FileInputStream(file);
	    JSONObject jo= new JSONObject(new JSONTokener(in));
	    JSONArray productos=jo.getJSONArray("productos");
	    boolean b=false;
	    for(int i=0; i<productos.length() && !b;i++) {
	    	JSONObject ob= productos.getJSONObject(i);
	    	if(ob.getInt("idProducto")==id) {
	    		ob.put("disponibilidad",false);
	    		b=true;
	    	}
	    }
	    in.close();
        OutputStream out= new FileOutputStream(file);
        PrintStream p=new PrintStream(out);
        p.print(jo);
	    } catch (FileNotFoundException e) {}
	      catch(IOException e) {}
	    return id;
		
	}

	 @Override
	 public Collection<TProducto> readall() {
	 ArrayList<TProducto> ts= new ArrayList<TProducto>();
	 File file= new File("resources/productos.json");
		InputStream in;
	    try {
		in= new FileInputStream(file);
	    JSONObject jo= new JSONObject(new JSONTokener(in));
	    JSONArray productos=jo.getJSONArray("productos");
	    for(int i=0; i<productos.length();i++) {
	    	JSONObject ob= productos.getJSONObject(i);
	    	if(ob.getBoolean("disponibilidad")==true) {
	    	TProducto p = new TProducto();
	    	p.setId(ob.getInt("idProducto"));
	    	p.setCantidad(ob.getInt("cantidadProducto"));
	    	p.setIdMarca(ob.getInt("idMarca"));
	    	p.setTipoProducto(ob.get("TipoDeProducto").toString());
	    	p.setPrecioProducto(ob.getInt("precioProducto"));
	    	p.setAlta(ob.getBoolean("disponibilidad"));
	    	ts.add(p);
	    	}
	    }
	    in.close();
	    } catch (FileNotFoundException e) {}
	      catch(IOException e) {}
	 return ts;
	}

	@Override
	public TProducto readbyname(String tipoProducto) {
		TProducto tp = null; 
		File file= new File("resources/productos.json");
		InputStream in;
	    try {
		in= new FileInputStream(file);
	    JSONObject jo= new JSONObject(new JSONTokener(in));
	    JSONArray productos=jo.getJSONArray("productos");
	    boolean b=false;
	    for(int i=0; i<productos.length() && !b;i++) {
	    	JSONObject ob= productos.getJSONObject(i);
	    	if(ob.getString("TipoDeProducto").equals(tipoProducto)) {
	    		tp=new TProducto();
	    		tp.setId(ob.getInt("idProducto"));
	    		tp.setTipoProducto(ob.getString("TipoDeProducto"));
	    		tp.setCantidad(ob.getInt("cantidadProducto"));
	    		tp.setPrecioProducto(ob.getFloat("precioProducto"));
	    		tp.setIdMarca(ob.getInt("idMarca"));
	    		tp.setAlta(ob.getBoolean("disponibilidad"));
	    		b=true;
	    	}
	    }
	    in.close();
	    } catch (FileNotFoundException e) {}
	    catch(IOException e) {}
		return tp; 
	}

	@Override
	public Collection<TProducto> readallbymarca(int id) {
		    ArrayList<TProducto> ts= new ArrayList<TProducto>();
		    File file= new File("resources/productos.json");
			InputStream in;
		    try {
			in= new FileInputStream(file);
		    JSONObject jo= new JSONObject(new JSONTokener(in));
		    JSONArray productos=jo.getJSONArray("productos");
		    for(int i=0; i<productos.length();i++) {
		    	JSONObject ob= productos.getJSONObject(i);
		    	if(ob.getBoolean("disponibilidad")==true && ob.getInt("idMarca")==id) {
		    	TProducto p = new TProducto();
		    	p.setId(ob.getInt("idProducto"));
		    	p.setCantidad(ob.getInt("cantidadProducto"));
		    	p.setIdMarca(ob.getInt("idMarca"));
		    	p.setTipoProducto(ob.get("TipoDeProducto").toString());
		    	p.setPrecioProducto(ob.getInt("precioProducto"));
		    	p.setAlta(ob.getBoolean("disponibilidad"));
		    	ts.add(p);
		    	}
		    }
		    in.close();
		    } catch (FileNotFoundException e) {}
		      catch(IOException e) {}
		 return ts;
	}

}
