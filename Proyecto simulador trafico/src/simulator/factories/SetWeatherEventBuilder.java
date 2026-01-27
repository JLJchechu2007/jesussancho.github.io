package simulator.factories;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import simulator.misc.Pair;
import simulator.model.Event;
import simulator.model.SetWeatherEvent;
import simulator.model.Weather;

public class SetWeatherEventBuilder extends Builder<Event> {
    public SetWeatherEventBuilder() {
        super("set_weather", "Sets the weather of a road");
    }

    @Override
    protected Event createInstance(JSONObject data) {
        int time = data.getInt("time");
        List<Pair<String, Weather>> info = new ArrayList<>();
        data.getJSONArray("info").forEach(item -> {
            JSONObject roadWeather = (JSONObject) item;
            String tiempo = roadWeather.getString("weather");
            Weather weather;
            if(tiempo.equals("SUNNY")) weather=Weather.SUNNY;
            else if(tiempo.equals("CLOUDY")) weather=Weather.CLOUDY;
            else if(tiempo.equals("RAINY")) weather=Weather.RAINY;
            else if(tiempo.equals("WINDY")) weather=Weather.WINDY;
            else weather=Weather.STORM;
            info.add(new Pair<>(roadWeather.getString("road"), weather));
        });

        return new SetWeatherEvent(time, info);
    }
}