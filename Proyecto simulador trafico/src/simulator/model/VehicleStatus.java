package simulator.model;

public enum VehicleStatus {
	PENDING, TRAVELING, WAITING, ARRIVED;
	
	public static String printEstado(VehicleStatus s) {
		if(s.equals(PENDING)) return "PENDING";
		else if(s.equals(TRAVELING)) return "TRAVELING";
		else if(s.equals(WAITING)) return "WAITING";
		else return "ARRIVED";
	}
}
