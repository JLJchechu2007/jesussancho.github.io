package integracion.Facturas;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import integracion.Factoria.FactoriaAbstractaIntegracion;
import negocio.Facturas.TFacturas;
import negocio.Facturas.TLineaFactura;

public class DAOLineaFacturaImp implements DAOLineaFactura{ //no hay métodos de modificar ni eliminar facturas, porque no se admite devolución de un helado
	
    private static final String FILE_PATH = "resources/lineafactura.json";
    
  //No hay ningún atributo que inicializar, así que no necesita redefinir el constructor
    
    //MÉTODOS PÚBLICOS
    @Override
    public int insertar(TLineaFactura l) {
    	int id=-1;
    	//1.Abro el archivo con las líneas
    	File file = new File(FILE_PATH);
    	try (InputStream in = new FileInputStream(file)) {
    		JSONObject data= new JSONObject(new JSONTokener(in));
    		
    		//2.Calculo el id de la nueva línea, actualizo el contador de ids y se lo asigno
    		if (!data.isEmpty())  id= data.getInt("cont")+1;
        	else id=1;
    		data.put("cont",id);
    		l.setIdLinea(id);
    		
    		//3.Extraigo el array de líneas de factura, si no existe lo creo
    		JSONArray lineas;
    		if (!data.has("lineas"))lineas = new JSONArray();
    		else lineas=data.getJSONArray("lineas");
    		
	    	//4.Creo un JSONObject con todos los atributos de la línea de factura a insertar
	        JSONObject linea = new JSONObject();
	        linea.put("idLinea", l.getIdLinea());
	        linea.put("idFactura", l.getIdFactura());
	        linea.put("idProducto", l.getIdProducto());
	        linea.put("cantidad", l.getCantidad()); 
	        linea.put("precio", l.getPrecio());
	        //Las activas serán aquellas cuyo idFactura coincida con el de una factura activa
	        lineas.put(linea);
	        data.put("lineas", lineas);
	        
	        //5.Escribo en el archivo
		    try (FileWriter writer = new FileWriter(FILE_PATH)) {
		        writer.write(data.toString(6));
		    } catch (IOException | JSONException e) {
		        System.out.println("Error al guardar las facturas: " + e.getMessage());
		    }
    	}catch (IOException e) {
	        System.out.println("No se ha encontrado el archivo con las facturas");
	    }
    	return id;
    }

    @Override
    public TLineaFactura buscar(int id) {
    	File file = new File(FILE_PATH);
		try (InputStream in = new FileInputStream(file)) {
    		JSONObject data= new JSONObject(new JSONTokener(in));
    		JSONArray ls=data.getJSONArray("lineas");
    		for(int i=0; i<ls.length();i++) {
    			JSONObject l= ls.getJSONObject(i);
	    	if(l.getInt("idLinea")==id) {
	    		int idFactura=l.getInt("idFactura");
		        int idProducto=l.getInt("idProducto");
		        int cantidad=l.getInt("cantidad");
		        float precio=l.getFloat("precio");
		        return new TLineaFactura(id,idFactura,idProducto,cantidad,precio);
	    	}
    		}
		   
		}catch (IOException e) {
	    	System.out.println("No se encontró el archivo con las líneas de factura");
	    }
        return null;
    }

    @Override
    public List<TLineaFactura> listar() {
    	File file = new File(FILE_PATH);
    	List<TLineaFactura> lineas=new ArrayList<TLineaFactura>();
		try (InputStream in = new FileInputStream(file)) {
    		JSONObject data= new JSONObject(new JSONTokener(in));
    		JSONArray ls=data.getJSONArray("lineas");
    		for(int i=0; i<ls.length();i++) {
    			JSONObject l= ls.getJSONObject(i);
    			int idLinea=l.getInt("idLinea");
    			int idFactura=l.getInt("idFactura");
		        int idProducto=l.getInt("idProducto");
		        int cantidad=l.getInt("cantidad");
		        float precio=l.getFloat("precio");
		        lineas.add(new TLineaFactura(idLinea,idFactura,idProducto,cantidad,precio));
    		}
		   
		}catch (IOException e) {
	    	System.out.println("No se encontró el archivo con las líneas de factura");
	    }
        return lineas;
    }
    
    
    
	@Override
	public List<TLineaFactura> buscarPorFactura(int idFactura) {
		File file = new File(FILE_PATH);
		List<TLineaFactura> lineas=new ArrayList<TLineaFactura>();
		try (InputStream in = new FileInputStream(file)) {
    		JSONObject data= new JSONObject(new JSONTokener(in));
    		JSONArray ls=data.getJSONArray("lineas");
    		for(int i=0; i<ls.length();i++) {
    			JSONObject l= ls.getJSONObject(i);
	    	if(l.getInt("idFactura")==idFactura) {
	    		int id=l.getInt("idLinea");
		        int idProducto=l.getInt("idProducto");
		        int cantidad=l.getInt("cantidad");
		        float precio=l.getFloat("precio");
		        lineas.add(new TLineaFactura(id,idFactura,idProducto,cantidad,precio));
	    	}
    		}
		   
		}catch (IOException e) {
	    	System.out.println("No se encontró el archivo con las líneas de factura");
	    }
        return lineas;
	}
}