package simulator.factories;

import org.json.JSONObject;

import simulator.model.LightSwitchingStrategy;
import simulator.model.MostCrowdedStrategy;

public class MostCrowdedStrategyBuilder extends Builder<LightSwitchingStrategy>{

	public MostCrowdedStrategyBuilder() {
		super("most_crowded_lss", "Most crowded light switching strategy");
	}

	@Override
	protected LightSwitchingStrategy createInstance(JSONObject data) {
		
		//return new MostCrowdedStrategy(data.getInt("timeslot"));
		int timeslot = data.has("timeslot") ? data.getInt("timeslot") : 1;
        return new MostCrowdedStrategy(timeslot);
	}

}
