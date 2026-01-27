package simulator.model;

public class InterCityRoad extends Road{
	
	public InterCityRoad(String id, Junction srcJunc, Junction destJunc, int maxSpeed, int contLimit, int length, Weather weather) {
		super(id, srcJunc, destJunc, maxSpeed, contLimit, length, weather);
	}
	
	@Override
	public void reduceTotalContamination() {
		int x;
		if(super.getWeather()==Weather.SUNNY) x=2;
		else if(super.getWeather()==Weather.CLOUDY) x=3;
		else if(super.getWeather()==Weather.RAINY) x=10;
		else if(super.getWeather()==Weather.WINDY) x=15;
		else x=20;
		super.contaminacionTotal=((100-x)*super.contaminacionTotal)/100;
	}
	@Override
	public void updateSpeedLimit() {
		if(super.contaminacionTotal>super.getContLimit()) super.limiteActualVelocidad=super.getMaxSpeed()/2;
		else super.limiteActualVelocidad=super.getMaxSpeed();
	}

	@Override
	public int calculateVehicleSpeed(Vehicle v) {
		if(super.getWeather()==Weather.STORM) return (super.limiteActualVelocidad*8)/10;
		else return super.limiteActualVelocidad;
	}
}
