package simulator.model;

public class NewCityRoadEvent extends Event{
	
	private String id;
	private String src;
	private String dest;
	private int len;
	private int co2;
	private int max;
	private Weather w;

	public NewCityRoadEvent(int time, String id, String srcJun, String destJunc, int length, int co2Limit, int maxSpeed, Weather weather) {
		super(time);
		this.id=id;
		this.src=srcJun;
		this.dest=destJunc;
		this.len=length;
		this.co2=co2Limit;
		this.max=maxSpeed;
		this.w=weather;
	}

	@Override
	void execute(RoadMap map) throws IllegalArgumentException {
		Junction srcJ=map.getJunction(src);
		Junction destJ=map.getJunction(dest);
		CityRoad cr=new CityRoad(id,srcJ,destJ,max,co2,len,w);
		map.addRoad(cr);
		
	}
	
	@Override
	public String toString() {
		return "New CityRoad '"+id+"'";
	}

}
