package simulator.model;

import java.util.List;

public enum Weather {
	SUNNY, CLOUDY, RAINY, WINDY, STORM;
	
	public String toString() {
		if (this.equals(SUNNY)) return "SUNNY";
		else if(this.equals(CLOUDY)) return "CLOUDY";
		else if(this.equals(RAINY)) return "RAINY";
		else if(this.equals(WINDY)) return "WINDY";
		else return "STORM";
	}
	
	public String toStringView() {
		if (this.equals(SUNNY)) return "sun";
		else if(this.equals(CLOUDY)) return "cloud";
		else if(this.equals(RAINY)) return "rain";
		else if(this.equals(WINDY)) return "wind";
		else return "storm";
	}
	
	public static String[] listaToStringW(){
		Weather[] w=Weather.values();
		String[] dev=new String[w.length];
		for(int i = 0; i < w.length; i++) {
			dev[i] = w[i].toString();
		}
		return dev;
	}
}

