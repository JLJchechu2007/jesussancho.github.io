package simulator.model;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import org.json.JSONArray;
import org.json.JSONObject;

public class TrafficSimulator implements Observable<TrafficSimObserver> {
	
	private RoadMap _roadMap;
    private Queue<Event> _events;
    private int _time;
    private LinkedList<TrafficSimObserver> observadores = new LinkedList<TrafficSimObserver>();

    public TrafficSimulator() {
        _roadMap = new RoadMap();
        _events = new PriorityQueue<Event>(); //No sabemos si se hace asi el compare to
        _time = 0;
    }
    
    public void addEvent(Event e) {
    	if(e.getTime()<=_time) throw new IllegalArgumentException("Tiempo malo");
    	_events.add(e);
    	
    	for (TrafficSimObserver o: observadores) {
    		o.onEventAdded(_roadMap, _events, e, _time);
    	}
    }
    
    public void advance() throws IllegalArgumentException {
    	_time=_time+1;
    	while(!_events.isEmpty()) {
    		if(_events.peek().getTime()==_time) {
    			_events.peek().execute(_roadMap);
    			_events.poll();
    		}
    		else break; //Tampoco sabemos si esto esta bn porque no sabemos si esta bn ordenada la lista
    	}
    	List<Junction> cruces=this._roadMap.getJunctions();
    	for(Junction j: cruces) {
    		j.advance(_time);
    	}
    	List<Road> carreteras=this._roadMap.getRoads();
    	for(Road c: carreteras) {
    		c.advance(_time);
    	}
    	
    	for (TrafficSimObserver o: observadores) {
    		o.onAdvance(_roadMap, _events, _time);
    	}
    }
    
    public void reset() {
    	this._roadMap.reset();
    	this._events.clear();
    	this._time=0;
    	for (TrafficSimObserver o: observadores) {
    		o.onReset(_roadMap, _events, _time);
    	}
    }
    
    public JSONObject report() {
    	JSONObject jo1=new JSONObject();
    	jo1.put("time", this._time);
    	JSONObject j2 = this._roadMap.report();
    	jo1.put("state", j2);
    	return jo1;
    }

	@Override
	public void addObserver(TrafficSimObserver o) {
		if (o != null) { 
			observadores.add(o);
			o.onRegister(_roadMap, _events, _time);
		}
	}

	@Override
	public void removeObserver(TrafficSimObserver o) {
		observadores.remove(o);
	}
	
	public List<Vehicle> getVehicles() {
        return _roadMap.getVehicles();
    }
    
	public List<Road> getRoads() {
        return _roadMap.getRoads();
    }
	
	public List<Junction> getJunctions() {
        return _roadMap.getJunctions();
    }
	
	public Queue<Event> getEvents(){
		return this._events;
	}
	
	public int getTime() {
		return this._time;
	}
}
