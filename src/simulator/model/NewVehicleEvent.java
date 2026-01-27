package simulator.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class NewVehicleEvent extends Event{
	
	private String id;
	private int max;
	private int cont;
	private List<String> itinerary;

	public NewVehicleEvent(int time, String id, int maxSpeed, int contClass, List<String> itinerary) {
		super(time);
		this.id=id;
		this.max=maxSpeed;
		this.cont=contClass;
		this.itinerary=itinerary;
	}

	@Override
	void execute(RoadMap map) throws IllegalArgumentException {
		List<Junction> nuevoItinerario=new ArrayList<Junction>();
		for(String s: itinerary) {
			nuevoItinerario.add(map.getJunction(s));
		}
		Vehicle v=new Vehicle(id,max,cont,nuevoItinerario);
		map.addVehicle(v);
		v.moveToNextRoad();
		
	}
	@Override
	public String toString() {
		return "New Vehicle '"+id+"'";
	}

}
