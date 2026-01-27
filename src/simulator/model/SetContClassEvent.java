package simulator.model;

import java.util.List;

import simulator.misc.Pair;

public class SetContClassEvent extends Event{

	private List<Pair<String,Integer>> cs1;
	
	public SetContClassEvent(int time, List<Pair<String,Integer>> cs) {
		super(time);
		if(cs==null) throw new IllegalArgumentException("Lista cont vacia");
		cs1=cs;
		// TODO Auto-generated constructor stub
	}

	@Override
	void execute(RoadMap map) throws IllegalArgumentException {
		for(Pair<String, Integer> i: cs1) {
			if(map.getVehicle(i.getFirst())==null) throw new IllegalArgumentException("No se encontro el vehiculo");
			map.getVehicle(i.getFirst()).setContaminationClass(i.getSecond());
		}
		
	}@Override
	public String toString() {
		return "Change CO2 class: "+lista_pair_toString();
	}
	
	String lista_pair_toString() {
		String res = "[";
		for (Pair<String,Integer> w: cs1) {
			res += "("+w.getFirst()+","+w.getSecond()+"),";
		}
		res = res.substring(0, res.length()-1);
		res += "]";
		return res;
	}

}
