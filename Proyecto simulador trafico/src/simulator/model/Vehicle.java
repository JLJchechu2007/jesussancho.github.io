package simulator.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.json.JSONObject;
import static java.lang.Math.min;

public class Vehicle extends SimulatedObject{
	
	private List<Junction> itinerario;
	private int velocidadMaxima;
	private int velocidadActual;
	private VehicleStatus estado;
	private Road carretera;
	private int localizacion;
	private int gradoDeContaminacion;
	private int contaminacionTotal;
	private int distanciaTotalRecorrida;
	private int i;
	
	Vehicle(String id, int maxSpeed, int contClass, List<Junction> itinerary) throws IllegalArgumentException {//junction es cruces que es otra clase
		super(id);
		
		if(maxSpeed<=0) throw new IllegalArgumentException("La velocidad mexima debe ser un valor positivo");
		if(contClass<0 || contClass>10) throw new IllegalArgumentException("contClass debe ser un valor entre 0 y 10"); 
		if(itinerary.size()<2) throw new IllegalArgumentException("La lista de itinerarios debe tener una longitud de al menos dos");
		
		this.velocidadMaxima=maxSpeed;
		this.velocidadActual=0;
		this.estado=VehicleStatus.PENDING;
		this.carretera = null;
		this.localizacion=0;
		this.gradoDeContaminacion=contClass;
		this.contaminacionTotal=0;
		this.distanciaTotalRecorrida=0;
		this.itinerario=Collections.unmodifiableList(new ArrayList<>(itinerary));
		this.i=0;
		
		
		
		  // TODO complete
		}

	@Override
	void advance(int time){
		if(this.estado.equals(VehicleStatus.TRAVELING)) {
			int f=min(this.localizacion+this.velocidadActual,this.carretera.getLength())-this.localizacion;
			int a=this.localizacion;
			this.localizacion=min(this.localizacion+this.velocidadActual,this.carretera.getLength());
			this.distanciaTotalRecorrida=distanciaTotalRecorrida+this.localizacion-a;
			int c=this.gradoDeContaminacion*f;
			this.contaminacionTotal=this.contaminacionTotal+c;
			this.carretera.addContamination(c);
			if(this.localizacion>=this.carretera.getLength()) {
				//el vehı́culo entra en la cola del cruce correspondiente (llamando a un método de la clase Junction)
				this.carretera.cruceDestino.enter(this);
				this.velocidadActual=0;
				this.estado=VehicleStatus.WAITING;
			}
		}
		
	}

	@Override
	public JSONObject report() {
		JSONObject jo1 = new JSONObject();
		jo1.put("id", this._id);
		jo1.put("speed", this.velocidadActual);
		jo1.put("distance", this.distanciaTotalRecorrida);
		jo1.put("co2", this.contaminacionTotal);
		jo1.put("class", this.gradoDeContaminacion);
		jo1.put("status", this.estado.printEstado(estado));
		if (this.carretera != null && this.estado != VehicleStatus.PENDING && this.estado != VehicleStatus.ARRIVED) {
			jo1.put("road", this.carretera.getId());
			jo1.put("location", this.localizacion);
		}
		return jo1;
	}
	
	void setSpeed(int s) throws IllegalArgumentException{
		if (this.estado == VehicleStatus.TRAVELING) {
			if(s<0) throw new IllegalArgumentException("velocidad no valida"); 
			this.velocidadActual= min(s,this.velocidadMaxima);	
		}
	}
	void setContaminationClass(int c) throws IllegalArgumentException{
		if(c<0 || c>10) throw new IllegalArgumentException("contaminacion no valida"); 
		this.gradoDeContaminacion=c;
	}
	void moveToNextRoad() throws IllegalArgumentException{
		if(!this.estado.equals(VehicleStatus.PENDING)&&!this.estado.equals(VehicleStatus.WAITING)) throw new IllegalArgumentException("Mal hecho el cambio de carreteras");
		if(i<this.itinerario.size()) {
		if(this.estado.equals(VehicleStatus.PENDING)) {
			this.carretera=this.itinerario.get(0).roadTo(this.itinerario.get(1));//pongo null porque no se decir que carrera será
			this.i=i+2;
			this.localizacion=0;
			this.estado=VehicleStatus.TRAVELING;
			if (this.carretera != null) carretera.enter(this);
		}
		else if(this.estado.equals(VehicleStatus.WAITING)) {
			this.carretera.exit(this);
			this.carretera=this.carretera.getDest().roadTo(this.itinerario.get(i));
			i++;
			this.localizacion=0;
			this.estado=VehicleStatus.TRAVELING;
			this.carretera.enter(this);
		}
		
		}
		else {
			this.estado=VehicleStatus.ARRIVED;
			this.velocidadActual=0;
			if(this.carretera!=null) this.carretera.exit(this);
			this.carretera=null;
		}
	}
	public int getLocation(){return this.localizacion;}
	public int getSpeed() {return this.velocidadActual;}
	public int getMaxSpeed() {return this.velocidadMaxima;}
	public int getContClass() {return this.gradoDeContaminacion;} 
	public VehicleStatus getStatus() {return this.estado;}
	public int getTotalCO2() {return this.contaminacionTotal;}
	public List<Junction> getItinerary(){return this.itinerario;}
	public Road getRoad() {return this.carretera;}
	public int getDistance() {return this.distanciaTotalRecorrida;}
}
