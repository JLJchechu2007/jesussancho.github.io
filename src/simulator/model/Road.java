package simulator.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.json.*;

public abstract class Road extends SimulatedObject{
protected Junction cruceOrigen;
protected Junction cruceDestino;
protected int longitud;
private int velocidadMaxima;
protected int limiteActualVelocidad;
private int alarmaContaminacionExcesiva;
private Weather condicionesAmbientales;
protected int contaminacionTotal;
protected List<Vehicle> vehiculos;

Road(String id, Junction srcJunc, Junction destJunc, int maxSpeed, int contLimit, int length, Weather weather) throws IllegalArgumentException{
	super(id);
	if(maxSpeed<=0 || contLimit<0 || length<=0 || srcJunc==null || destJunc ==null || weather==null) {
		throw new IllegalArgumentException("Carretera mal creada");
	}
	 this.cruceOrigen=srcJunc;
	 this.cruceDestino=destJunc;
	 this.velocidadMaxima=maxSpeed;
	 this.limiteActualVelocidad=maxSpeed;
	 this.longitud=length;
	 this.alarmaContaminacionExcesiva=contLimit;
	 this.condicionesAmbientales=weather;
	 this.contaminacionTotal=0;
	 this.vehiculos = new ArrayList<Vehicle>();
	 srcJunc.addOutGoingRoad(this);
}
public void enter(Vehicle v) throws IllegalArgumentException{
	if (v.getLocation()!=0||v.getSpeed()!=0) {
	throw new IllegalArgumentException("No puede insertare en la carretera el vehiculo");
	}
	vehiculos.add(v);
	ItemComparator comp = new ItemComparator();
	vehiculos.sort(comp);
}
public void exit(Vehicle v) {
	vehiculos.remove(v);
}
public void setWeather(Weather w) throws IllegalArgumentException{
	if(w==null) throw new IllegalArgumentException("No puede cambiarse el tiempo atmosferico");
	condicionesAmbientales=w;
	this.updateSpeedLimit();
	}
public void addContamination(int c) throws IllegalArgumentException{
	if(c<0) throw new IllegalArgumentException("No puede cambiarse la contaminacion"); 
	this.contaminacionTotal=this.contaminacionTotal+c;
	}
public abstract void reduceTotalContamination();
public abstract void updateSpeedLimit();
public abstract int calculateVehicleSpeed(Vehicle v);
void advance(int currTime) {
	reduceTotalContamination();
	updateSpeedLimit();
	for(Vehicle v: vehiculos) {
	v.setSpeed(calculateVehicleSpeed(v));
	v.advance(currTime); //Mirar si este es el parametro
	}
	ItemComparator comp = new ItemComparator();
	vehiculos.sort(comp);
	//Metodo ordenar lista en diapositivas
	}
public JSONObject report() {
	JSONObject jo1 = new JSONObject();
	
	jo1.put("id", this._id);
	jo1.put("speedlimit", this.limiteActualVelocidad);
	jo1.put("weather", this.condicionesAmbientales.toString());
	jo1.put("co2", this.contaminacionTotal);
	
	JSONArray j2 = new JSONArray();
	
	for(int i=0;i<this.vehiculos.size();i++) {
		j2.put(this.vehiculos.get(i).getId());
	}
	jo1.put("vehicles", j2);
	return jo1;
	}

public int getLength() {
	return longitud;
}
public Junction getDest() {
	return cruceDestino;
}
public Junction getSrc() {
	return cruceOrigen;
}
public Weather getWeather() {
	return condicionesAmbientales;
}
public int getContLimit(){
	return alarmaContaminacionExcesiva;
}
public int getMaxSpeed(){
	return velocidadMaxima;
}
public int getTotalCO2() {
	return contaminacionTotal;
}
public int getSpeedLimit() {
	return limiteActualVelocidad;
}
public List<Vehicle> getVehicles() {
	return Collections.unmodifiableList(this.vehiculos);
}
}
