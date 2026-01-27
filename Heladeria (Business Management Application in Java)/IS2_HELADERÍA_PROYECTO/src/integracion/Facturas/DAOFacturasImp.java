package integracion.Facturas;

import java.util.ArrayList;
import java.util.List;
import negocio.Facturas.TFacturas;
import negocio.Facturas.TLineaFactura;
import negocio.Producto.TProducto;

import java.io.*;
import org.json.*;

import integracion.Cliente.DAOCliente;
import integracion.Factoria.FactoriaAbstractaIntegracion;


public class DAOFacturasImp implements DAOFacturas {
	//Elementos Privados:
    private static final String FILE_PATH = "resources/facturas.json";
    
    //No hay ningún atributo que inicializar, así que no necesita redefinir el constructor
    
    //MÉTODOS PÚBLICOS
    @Override
    public int insertar(TFacturas f) {//Para guardar la factura en la bd, al DAOLineaFactura lo llama el SA para obtener los ids correspondientes
    	int id=-1;
    	//1.Abro el archivo con las facturas
    	File file = new File(FILE_PATH);
    	try (InputStream in = new FileInputStream(file)) {
    		JSONObject data= new JSONObject(new JSONTokener(in));
    		
    		//2.Calculo el id de la nueva factura, actualizo el contador de ids y se lo asigno
    		if (!data.isEmpty())  id= data.getInt("cont")+1;
        	else id=1;
    		data.put("cont",id);
    		f.setIdFactura(id);
    		
    		//3.Extraigo el array de facturas, si no existe lo creo
    		JSONArray facturas;
    		if (!data.has("facturas"))facturas = new JSONArray();
    		else facturas=data.getJSONArray("facturas");
    		
	    	//4.Creo un JSONObject con la factura a insertar (las lineas de factura se crearán a  parte en su propio archivo, con el id de esta factura)
	        JSONObject factura = new JSONObject();
	        factura.put("idFactura", f.getIdFactura());
	        factura.put("idCliente", f.getIdCliente());
	        factura.put("idVendedor", f.getIdVendedor());
	        factura.put("fecha", f.getFecha()); 
	        factura.put("importeTotal", f.getImporte());
	        factura.put("activo", f.isActivo());
	        factura.put("direccion", f.getDireccion());
	        facturas.put(factura);
	        data.put("facturas", facturas);
	        
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
    public TFacturas buscar(int id) {
    	File file = new File(FILE_PATH);
		try (InputStream in = new FileInputStream(file)) {
    		JSONObject data= new JSONObject(new JSONTokener(in));
    		JSONArray fs=data.getJSONArray("facturas");
    		for(int i=0; i<fs.length();i++) {
    			JSONObject f= fs.getJSONObject(i);
	    	if(f.getInt("idFactura")==id) {
	    		int idCliente=f.getInt("idCliente");
		        String idVendedor=f.getString("idVendedor");
		        String fecha=f.getString("fecha");
		        String direccion=f.getString("direccion");
		        float importe=f.getFloat("importeTotal");
		        boolean activo=f.getBoolean("activo");
		        DAOLineaFactura daoLineas = FactoriaAbstractaIntegracion.getInstance().crearDAOLineaFactura();
		        List<TLineaFactura> lineas=daoLineas.buscarPorFactura(id);
		        return new TFacturas(id, idCliente, idVendedor, fecha, importe, activo, direccion, lineas);
	    	}
    		}
		   
		}catch (IOException e) {
	    	System.out.println("No se encontró el archivo con las facturas");
	    }
        return null;
    }

    @Override
    public List<TFacturas> listar() {
    	List<TFacturas> fs=new ArrayList<TFacturas>();
    	File file = new File(FILE_PATH);
		try (InputStream in = new FileInputStream(file)) {
    		JSONObject data= new JSONObject(new JSONTokener(in));
    		JSONArray jo=data.getJSONArray("facturas");
    		for(int i=0; i<jo.length();i++) {
    			JSONObject f= jo.getJSONObject(i);
    			int idFactura=f.getInt("idFactura");
    			int idCliente=f.getInt("idCliente");
		        String idVendedor=f.getString("idVendedor");
		        String fecha=f.getString("fecha");
		        String direccion=f.getString("direccion");
		        float importe=f.getFloat("importeTotal");
		        boolean activo=f.getBoolean("activo");
		        DAOLineaFactura daoLineas = FactoriaAbstractaIntegracion.getInstance().crearDAOLineaFactura();
		        List<TLineaFactura> lineas=daoLineas.buscarPorFactura(idFactura);
		        fs.add(new TFacturas(idFactura, idCliente, idVendedor, fecha, importe, activo, direccion, lineas));
	    	}
		   
		}catch (IOException e) {
	    	System.out.println("No se encontró el archivo con las facturas");
	    }
        return fs;
    }

	@Override
	public int actualizar(TFacturas factura) {
		File file = new File(FILE_PATH);
		int id=-1;
    	try (InputStream in = new FileInputStream(file)) {
    		JSONObject data= new JSONObject(new JSONTokener(in));
    		JSONArray fs=data.getJSONArray("facturas");
    		for(int i=0; i<fs.length() && id==-1;i++) {
    			JSONObject f= fs.getJSONObject(i);
	    	if(f.getInt("idFactura")==factura.getIdFactura()) {
	    		id=factura.getIdFactura();
	    		f.put("idFactura", factura.getIdFactura());
		        f.put("idCliente", factura.getIdCliente());
		        f.put("idVendedor", factura.getIdVendedor());
		        f.put("fecha", factura.getFecha()); 
		        f.put("importeTotal", factura.getImporte());
		        f.put("activo", factura.isActivo());
		        f.put("direccion", factura.getDireccion());
	    	}
    		}
		    try (FileWriter writer = new FileWriter(FILE_PATH)) {
		        writer.write(data.toString(6));
		    } catch (IOException | JSONException e) {
		        System.out.println("Error al guardar las facturas: " + e.getMessage());
		    }
	    }catch (IOException e) {
	    	System.out.println("No se encontró el archivo con las facturas");
	    }
	    return id;
	}
	
	@Override
	public List<TFacturas> buscarPorCliente(int idCliente) {
		List<TFacturas> fs=new ArrayList<TFacturas>();
    	File file = new File(FILE_PATH);
		try (InputStream in = new FileInputStream(file)) {
    		JSONObject data= new JSONObject(new JSONTokener(in));
    		JSONArray jo=data.getJSONArray("facturas");
    		for(int i=0; i<jo.length();i++) {
    			JSONObject f= jo.getJSONObject(i);
    			if(f.getInt("idCliente")==idCliente) {
	    			int idFactura=f.getInt("idFactura");
			        String idVendedor=f.getString("idVendedor");
			        String fecha=f.getString("fecha");
			        String direccion=f.getString("direccion");
			        float importe=f.getFloat("importeTotal");
			        boolean activo=f.getBoolean("activo");
			        DAOLineaFactura daoLineas = FactoriaAbstractaIntegracion.getInstance().crearDAOLineaFactura();
			        List<TLineaFactura> lineas=daoLineas.buscarPorFactura(idFactura);
			        fs.add(new TFacturas(idFactura, idCliente, idVendedor, fecha, importe, activo, direccion, lineas));
    			}
	    	}
		   
		}catch (IOException e) {
	    	System.out.println("No se encontró el archivo con las facturas");
	    }
        return fs;
	}
}