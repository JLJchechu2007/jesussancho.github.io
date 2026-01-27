package simulator.model;

public class CityRoad extends Road{
	
	public CityRoad(String id, Junction srcJunc, Junction destJunc, int maxSpeed, int contLimit, int length, Weather weather) {
		super(id, srcJunc, destJunc, maxSpeed, contLimit, length, weather);
	}

	@Override
	public void reduceTotalContamination() {
		int x;
		if(super.getWeather()==Weather.WINDY || super.getWeather()==Weather.STORM) x=10;
		else x=2;
		super.contaminacionTotal=super.contaminacionTotal-x;
		if(super.contaminacionTotal-x<0) super.contaminacionTotal=0;
	}

	@Override
	public void updateSpeedLimit() {
		super.limiteActualVelocidad=super.getMaxSpeed();
		
	}

	@Override
	public int calculateVehicleSpeed(Vehicle v) {
		return ((11-v.getContClass())*super.limiteActualVelocidad)/11;
	}
	
	
}
