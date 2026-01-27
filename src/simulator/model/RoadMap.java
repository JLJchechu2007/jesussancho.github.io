package simulator.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

public class RoadMap {
	private List<Junction> junctions;
    private List<Road> roads;
    private List<Vehicle> vehicles;
    private Map<String, Junction> junctionMap;
    private Map<String, Road> roadMap;
    private Map<String, Vehicle> vehicleMap;
    
    RoadMap(){
    	junctions=new ArrayList<Junction>();
    	roads=new ArrayList<Road>();
    	vehicles=new ArrayList<Vehicle>();
    	junctionMap=new HashMap<String, Junction>();
    	roadMap = new HashMap<String, Road>();
        vehicleMap = new HashMap<String, Vehicle>();
    }
    
    void addJunction(Junction j) throws IllegalArgumentException {
    	if(this.junctionMap.containsKey(j.getId())) throw new IllegalArgumentException("No se puede anyadir un cruce que ya esta");
    	junctions.add(j);
		junctionMap.put(j.getId(), j);
    }
    
    void addRoad(Road r) throws IllegalArgumentException{
    	if(this.roadMap.containsKey(r.getId())) throw new IllegalArgumentException("Carretera ya en el mapa");
    	if(!(this.junctionMap.containsKey(r.getSrc().getId()))||!(this.junctionMap.containsKey(r.getDest().getId()))) throw new IllegalArgumentException("No esta el cruce destino o el cruce origen");
    	
    	roads.add(r);
    	roadMap.put(r.getId(), r);
    	r.getDest().addIncommingRoad(r);   
    	}
    
    void addVehicle(Vehicle v) throws IllegalArgumentException{
    	if(this.vehicleMap.containsKey(v.getId())) throw new IllegalArgumentException("Vehiculo ya en el mapa");
    	List <Junction> itinerario=v.getItinerary();
    	for(int i=0;i<itinerario.size()-1;i++) {
    		Junction orig= itinerario.get(i);
    		Junction dest=itinerario.get(i+1);
    		boolean bien=false;
    		for(Road road: roads) {
    			if(road.getSrc().equals(orig)&&road.getDest().equals(dest)) {
    				bien=true;
    				break;
    			}
    		}
    		if(!bien) {
    			throw new IllegalArgumentException("No hay carretera que conecte los cruces");
    		}
    	}
    	vehicles.add(v);
    	this.vehicleMap.put(v.getId(), v);
    }
    
    
    public Junction getJunction(String id) {
        return junctionMap.get(id);
    }

    public Road getRoad(String id) {
        return roadMap.get(id);
    }

    public Vehicle getVehicle(String id) {
        return vehicleMap.get(id);
    }

    // Métodos para obtener listas de solo lectura
    public List<Junction> getJunctions() {
        return Collections.unmodifiableList(junctions);
    }

    public List<Road> getRoads() {
        return Collections.unmodifiableList(roads);
    }

    public List<Vehicle> getVehicles() {
        return Collections.unmodifiableList(vehicles);
    }
    
    void reset() {
    	this.junctions.clear();
    	this.roads.clear();
    	this.junctionMap.clear();
    	this.vehicles.clear();
    	this.vehicleMap.clear();
    	this.roadMap.clear();
    }
    
    public JSONObject report() {
    	
    	JSONObject jo = new JSONObject();
    	JSONArray joV = new JSONArray();
    	JSONArray joR = new JSONArray();
    	JSONArray joJ = new JSONArray();
    	
    	for(Vehicle v:vehicles) {
    		joV.put(v.report());
    	}
    	for(Road r:roads) {
    		joR.put(r.report());
    	}
    	for(Junction j:junctions) {
    		joJ.put(j.report());
    	}
    	jo.put("junctions", joJ);
    	jo.put("roads", joR);
    	jo.put("vehicles", joV);
    	
    	return jo;
    }
}
