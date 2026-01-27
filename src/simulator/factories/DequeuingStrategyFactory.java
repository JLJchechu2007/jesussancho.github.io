package simulator.factories;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import simulator.model.DequeuingStrategy;

public class DequeuingStrategyFactory extends BuilderBasedFactory{

	private BuilderBasedFactory<DequeuingStrategy> builderBasedFactory;

    // Constructor
    public DequeuingStrategyFactory() {
        List<Builder<DequeuingStrategy>> builders = new ArrayList<>();
        builders.add(new MoveFirstStrategyBuilder());
        builders.add(new MoveAllStrategyBuilder());

        this.builderBasedFactory = new BuilderBasedFactory<>(builders);
    }

    @Override
    public DequeuingStrategy createInstance(JSONObject info) {
        return builderBasedFactory.createInstance(info);
    }

    @Override
    public List<JSONObject> getInfo() {
        return builderBasedFactory.getInfo();
    }
}
