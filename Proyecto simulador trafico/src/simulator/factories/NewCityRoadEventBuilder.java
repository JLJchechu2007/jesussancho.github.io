package simulator.factories;

import org.json.JSONObject;

import simulator.model.Event;
import simulator.model.NewCityRoadEvent;
import simulator.model.Weather;

public class NewCityRoadEventBuilder extends Builder<Event> {
    public NewCityRoadEventBuilder() {
        super("new_city_road", "Creates a new city road");
    }

    @Override
    protected Event createInstance(JSONObject data) {
        int time = data.getInt("time");
        String id = data.getString("id");
        String src = data.getString("src");
        String dest = data.getString("dest");
        int length = data.getInt("length");
        int co2limit = data.getInt("co2limit");
        int maxspeed = data.getInt("maxspeed");
        
        String tiempo = data.getString("weather");
        Weather weather;
        if(tiempo.equals("SUNNY")) weather=Weather.SUNNY;
        else if(tiempo.equals("CLOUDY")) weather=Weather.CLOUDY;
        else if(tiempo.equals("RAINY")) weather=Weather.RAINY;
        else if(tiempo.equals("WINDY")) weather=Weather.WINDY;
        else weather=Weather.STORM;

        return new NewCityRoadEvent(time, id, src, dest, length, co2limit, maxspeed, weather);
    }
}