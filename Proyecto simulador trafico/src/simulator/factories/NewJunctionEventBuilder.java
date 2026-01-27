package simulator.factories;

import org.json.JSONObject;

import simulator.model.DequeuingStrategy;
import simulator.model.Event;
import simulator.model.LightSwitchingStrategy;
import simulator.model.NewJunctionEvent;

public class NewJunctionEventBuilder extends Builder<Event> {
    private Factory<LightSwitchingStrategy> lssFactory;
    private Factory<DequeuingStrategy> dqsFactory;

    public NewJunctionEventBuilder(Factory<LightSwitchingStrategy> lssFactory, Factory<DequeuingStrategy> dqsFactory) {
        super("new_junction", "Creates a new junction");
        this.lssFactory = lssFactory;
        this.dqsFactory = dqsFactory;
    }

    @Override
    protected Event createInstance(JSONObject data) {
        int time = data.getInt("time");
        String id = data.getString("id");
        int x = data.getJSONArray("coor").getInt(0);
        int y = data.getJSONArray("coor").getInt(1);
        LightSwitchingStrategy lss = lssFactory.createInstance(data.getJSONObject("ls_strategy"));
        DequeuingStrategy dqs = dqsFactory.createInstance(data.getJSONObject("dq_strategy"));

        return new NewJunctionEvent(time, id, lss, dqs, x, y);
    }
}

