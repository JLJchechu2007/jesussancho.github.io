package simulator.control;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.Queue;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import simulator.factories.Factory;
import simulator.model.Event;
import simulator.model.Junction;
import simulator.model.Observable;
import simulator.model.Road;
import simulator.model.TrafficSimObserver;
import simulator.model.TrafficSimulator;
import simulator.model.Vehicle;

public class Controller implements Observable<TrafficSimObserver> {
	TrafficSimulator traffic_simulator;
	Factory<Event> events_factory;
	public Controller(TrafficSimulator sim, Factory<Event> eventsFactory) throws IllegalArgumentException{
		  if (sim == null || eventsFactory == null) throw new IllegalArgumentException("Parametros controller mal");
		  this.traffic_simulator = sim;
		  this.events_factory = eventsFactory;
	}
	public void loadEvents(InputStream in) {
		try {
            JSONObject jo = new JSONObject(new JSONTokener(in));
            JSONArray eventsArray = jo.getJSONArray("events");

            for (int i = 0; i < eventsArray.length(); i++) {
                JSONObject eventJson = eventsArray.getJSONObject(i);
                Event event = events_factory.createInstance(eventJson);
                traffic_simulator.addEvent(event);
            }
        } catch (JSONException e) {
            throw new IllegalArgumentException("Invalid JSON format for events", e);};
        }
		
		public void run(int n, OutputStream out) {
	        JSONArray statesArray = new JSONArray();

	        for (int i = 0; i < n; i++) {
	        	traffic_simulator.advance();
	            statesArray.put(traffic_simulator.report());
	        }

	        JSONObject result = new JSONObject();
	        result.put("states", statesArray);
	        
	        
	        try (PrintWriter pw = new PrintWriter(out)) {
	            pw.println(result.toString());
	        }
	        
	    }
		
	    public void reset() {
	    	traffic_simulator.reset();
	    }
	    
		@Override
		public void addObserver(TrafficSimObserver o) {
			traffic_simulator.addObserver(o);
			
		}
		@Override
		public void removeObserver(TrafficSimObserver o) {
			traffic_simulator.removeObserver(o);
		}
		
		public void addEvent(Event e){
			this.traffic_simulator.addEvent(e);
		}
		
		public void run(int n) {
			for (int i = 0; i < n; i++) {
	        	traffic_simulator.advance();	        }
		}
		
		public List<Vehicle> getVehicles() {
	        return traffic_simulator.getVehicles();
	    }
		
		public List<Road> getRoads() {
	        return traffic_simulator.getRoads();
	    }
		
		public List<Junction> getJunction(){
			return this.traffic_simulator.getJunctions();
		}
		
		public Queue<Event> getEvents(){
			return this.traffic_simulator.getEvents();
		}
		
		public int getTime() {
			return this.traffic_simulator.getTime();
		}
}
