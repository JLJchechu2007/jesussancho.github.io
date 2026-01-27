package simulator.factories;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import simulator.misc.Pair;
import simulator.model.Event;
import simulator.model.SetContClassEvent;

public class SetContClassEventBuilder extends Builder<Event> {
    public SetContClassEventBuilder() {
        super("set_cont_class", "Sets the contamination class of a vehicle");
    }

    @Override
    protected Event createInstance(JSONObject data) {
        int time = data.getInt("time");
        List<Pair<String, Integer>> info = new ArrayList<>();
        data.getJSONArray("info").forEach(item -> {
            JSONObject vehicleClass = (JSONObject) item;
            info.add(new Pair<>(vehicleClass.getString("vehicle"), vehicleClass.getInt("class")));
        });

        return new SetContClassEvent(time, info);
    }
}