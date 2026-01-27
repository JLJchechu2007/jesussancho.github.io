package simulator.factories;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import simulator.model.DequeuingStrategy;
import simulator.model.Event;
import simulator.model.LightSwitchingStrategy;

public class EventFactory implements Factory<Event> {

    private BuilderBasedFactory<Event> builderBasedFactory;

    // Constructor
    public EventFactory(Factory<LightSwitchingStrategy> lssFactory, Factory<DequeuingStrategy> dqsFactory) {
        // Lista de builders para los eventos
        List<Builder<Event>> eventBuilders = new ArrayList<>();

        // Añadir los builders de eventos
        eventBuilders.add(new NewJunctionEventBuilder(lssFactory, dqsFactory));
        eventBuilders.add(new NewCityRoadEventBuilder());
        eventBuilders.add(new NewInterCityRoadEventBuilder());
        eventBuilders.add(new NewVehicleEventBuilder());
        eventBuilders.add(new SetWeatherEventBuilder());
        eventBuilders.add(new SetContClassEventBuilder());

        // Crear la factoría basada en builders
        this.builderBasedFactory = new BuilderBasedFactory<>(eventBuilders);
    }

    @Override
    public Event createInstance(JSONObject info) {
        return builderBasedFactory.createInstance(info);
    }

    @Override
    public List<JSONObject> getInfo() {
        return builderBasedFactory.getInfo();
    }
}