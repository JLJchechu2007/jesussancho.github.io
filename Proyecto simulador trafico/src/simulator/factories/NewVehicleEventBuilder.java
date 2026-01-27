package simulator.factories;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import simulator.model.Event;
import simulator.model.NewVehicleEvent;

public class NewVehicleEventBuilder extends Builder<Event> {
    public NewVehicleEventBuilder() {
        super("new_vehicle", "Creates a new vehicle");
    }

    @Override
    protected Event createInstance(JSONObject data) {
        int time = data.getInt("time");
        String id = data.getString("id");
        int maxspeed = data.getInt("maxspeed");
        int vehicleClass = data.getInt("class");
        List<String> itinerary = new ArrayList<>();
        data.getJSONArray("itinerary").forEach(item -> itinerary.add((String) item));

        return new NewVehicleEvent(time, id, maxspeed, vehicleClass, itinerary);
    }
}