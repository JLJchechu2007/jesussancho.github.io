package simulator.factories;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import simulator.model.LightSwitchingStrategy;

public class SwitchingStrategyFactory implements Factory<LightSwitchingStrategy>{
	
	private BuilderBasedFactory<LightSwitchingStrategy> builderBasedFactory;

    // Constructor
    public SwitchingStrategyFactory() {
        List<Builder<LightSwitchingStrategy>> builders = new ArrayList<>();
        builders.add(new RoundRobinStrategyBuilder());
        builders.add(new MostCrowdedStrategyBuilder());

        this.builderBasedFactory = new BuilderBasedFactory<>(builders);
    }

    @Override
    public LightSwitchingStrategy createInstance(JSONObject info) {
        return builderBasedFactory.createInstance(info);
    }

    @Override
    public List<JSONObject> getInfo() {
        return builderBasedFactory.getInfo();
    }
}
