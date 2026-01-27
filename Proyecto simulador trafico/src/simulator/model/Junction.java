package simulator.model;

import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class Junction extends SimulatedObject{
	private List<Road> incomingRoads;
    private Map<Junction, Road> outgoingRoads;
    private List<List<Vehicle>> queues;
    private Map<Road, List<Vehicle>> roadToQueueMap;
    private int currGreen;
    private int lastSwitchingTime;
    private LightSwitchingStrategy lightSwitchingStrategy;
    private DequeuingStrategy dequeuingStrategy;
    private int xCoor;
    private int yCoor;

    Junction(String id, LightSwitchingStrategy lsStrategy, DequeuingStrategy dqStrategy, int xCoor, int yCoor) {
        super(id);
        if (lsStrategy == null || dqStrategy == null) {
            throw new IllegalArgumentException("Las estrategias no pueden ser null.");
        }
        if (xCoor < 0 || yCoor < 0) {
            throw new IllegalArgumentException("Las coordenadas no pueden ser negativas.");
        }
        this.incomingRoads = new ArrayList<>();
        this.outgoingRoads = new HashMap<>();
        this.queues = new ArrayList<>();
        this.roadToQueueMap = new HashMap<>();
        this.currGreen = -1;
        this.lastSwitchingTime = 0;
        this.lightSwitchingStrategy = lsStrategy;
        this.dequeuingStrategy = dqStrategy;
        this.xCoor = xCoor;
        this.yCoor = yCoor;
    }

    @Override
    void advance(int currTime) throws IllegalArgumentException {
        // Mover vehículos de la cola con semáforo en verde
        if (currGreen != -1) {
            List<Vehicle> vehiclesToMove = dequeuingStrategy.dequeue(queues.get(currGreen));
            for (Vehicle vehicle : vehiclesToMove) {
                vehicle.moveToNextRoad();
                queues.get(currGreen).remove(vehicle);
            }
        }

        // Cambiar el semáforo si es necesario
        int nextGreen = lightSwitchingStrategy.chooseNextGreen(incomingRoads, queues, currGreen, lastSwitchingTime, currTime);
        if (nextGreen != currGreen) {
            currGreen = nextGreen;
            lastSwitchingTime = currTime;
        }
    }

    @Override
    public JSONObject report() {
        JSONObject junctionReport = new JSONObject();
        

        if (currGreen == -1) {
            junctionReport.put("green", "none");
        } else {
            junctionReport.put("green", incomingRoads.get(currGreen).getId());
        }

        JSONArray queuesArray = new JSONArray();
        for (int i = 0; i < incomingRoads.size(); i++) {
            JSONObject queueInfo = new JSONObject();
            queueInfo.put("road", incomingRoads.get(i).getId());

            JSONArray vehiclesArray = new JSONArray();
            for (Vehicle vehicle : queues.get(i)) {
                vehiclesArray.put(vehicle.getId());
            }
            queueInfo.put("vehicles", vehiclesArray);

            queuesArray.put(queueInfo);
        }
        junctionReport.put("queues", queuesArray);
        junctionReport.put("id", getId());

        return junctionReport;
    }
	
	void addIncommingRoad(Road r) {
		if (!r.getDest().equals(this)) {
            throw new IllegalArgumentException("La carretera no tiene este cruce como destino.");
        }
        incomingRoads.add(r);
        List<Vehicle> queue = new LinkedList<>();
        queues.add(queue);
        roadToQueueMap.put(r, queue);
	}
	
	void addOutGoingRoad(Road r) {
        Junction destJunction = r.getDest();
        if (outgoingRoads.containsKey(destJunction)) {
            throw new IllegalArgumentException("Ya existe una carretera hacia el cruce " + destJunction.getId());
        }
        if (!r.getSrc().equals(this)) {
            throw new IllegalArgumentException("La carretera no tiene este cruce como origen.");
        }
        outgoingRoads.put(destJunction, r);
    }
	
	void enter(Vehicle v) {
        Road currentRoad = v.getRoad();
        if (!roadToQueueMap.containsKey(currentRoad)) {
            throw new IllegalArgumentException("La carretera no es una carretera entrante válida.");
        }
        roadToQueueMap.get(currentRoad).add(v);
    }
	
	Road roadTo(Junction j) {
        return outgoingRoads.get(j);
    }
	
	public int getX() {
		return this.xCoor;
	}
	
	public int getY() {
		return this.yCoor;
	}

	public int getGreenLightIndex() {
		return this.currGreen;
	}
	
	public String getColas() {
		String dev="";
		for(int i=0;i<this.incomingRoads.size();i++) {
			dev=dev+incomingRoads.get(i)._id+":[";
			for (int a=0;a<queues.get(i).size();a++) {
				dev=dev+queues.get(i).get(a).getId();
				if (a < queues.get(i).size()-1)dev += ",";
			}
			dev=dev+"] ";
		}
		return dev;
	}
	
	public String getGreen() {
		if(this.currGreen==-1) return "NONE";
		else return this.incomingRoads.get(currGreen).getId();
	}

	public List<Road> getInRoads() {
		return incomingRoads;
	}
	

}
