package simulator.factories;

import org.json.JSONObject;

import simulator.model.LightSwitchingStrategy;
import simulator.model.RoundRobinStrategy;

public class RoundRobinStrategyBuilder extends Builder<LightSwitchingStrategy>{

	public RoundRobinStrategyBuilder() {
		super("round_robin_lss", "Round-robin light switching strategy");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected LightSwitchingStrategy createInstance(JSONObject data) {
		//return new RoundRobinStrategy(data.getInt("timeslot"));
		int timeslot = data.has("timeslot") ? data.getInt("timeslot") : 1;
        return new RoundRobinStrategy(timeslot);
	}

}
