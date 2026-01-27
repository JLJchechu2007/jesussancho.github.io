package simulator.model;

import java.util.List;

import simulator.misc.Pair;

public class SetWeatherEvent extends Event{
	
	private List<Pair<String,Weather>> wsG;

	public SetWeatherEvent(int time, List<Pair<String,Weather>> ws) throws IllegalArgumentException {
		super(time);
		if(ws==null) throw new IllegalArgumentException("Lista weather vacia");
		this.wsG=ws;
	}

	@Override
	void execute(RoadMap map) throws IllegalArgumentException {
		for(Pair<String,Weather> w:wsG) {
			map.getRoad(w.getFirst()).setWeather(w.getSecond());
		}
		
	}
	
	@Override
	public String toString() {
		return "Change Weather: "+lista_pair_toString();
	}
	
	String lista_pair_toString() {
		String res = "[";
		for (Pair<String,Weather> w: wsG) {
			res += "("+w.getFirst()+","+w.getSecond()+"),";
		}
		res = res.substring(0, res.length()-1);
		res += "]";
		return res;
	}
	

}
