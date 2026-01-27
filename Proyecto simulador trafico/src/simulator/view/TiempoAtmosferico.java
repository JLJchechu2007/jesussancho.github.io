package simulator.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import simulator.control.Controller;
import simulator.misc.Pair;
import simulator.model.Event;
import simulator.model.Road;
import simulator.model.SetContClassEvent;
import simulator.model.SetWeatherEvent;
import simulator.model.Weather;

public class TiempoAtmosferico implements ActionListener{
	JFrame f;
	String[] r;	
	Controller c;
	public TiempoAtmosferico(JFrame f, String[] r,Controller c) {
		this.f=f;
		this.r=r;
		this.c=c;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		ChangeWeatherDialog dialog=new ChangeWeatherDialog(f,listaToStringR(c.getRoads()),c);
		dialog.setVisible(true);
		if(dialog.isOkPressed()) {
			String rID=dialog.getSelectedRoad();
			String weather=dialog.getSelectedWeather();
			int ticks = dialog.getSelectedTicks();
			Weather w = turn_into_weather(weather);
			List<Pair<String,Weather>> p= new ArrayList<Pair<String,Weather>>();
			p.add(new Pair<String, Weather>(rID,w));
			Event ev = new SetWeatherEvent(ticks+c.getTime(), p);
			c.addEvent(ev);
		}
	}
	
	String[] listaToStringR(List<Road> list){
		String[] dev=new String[list.size()];
		for(int i = 0; i < list.size(); i++) {
			dev[i] = list.get(i).toString();
		}
		return dev;
	}
	Weather turn_into_weather(String s) {
		if (s == "SUNNY") return Weather.SUNNY;
		else if (s == "CLOUDY") return Weather.CLOUDY;
		else if (s == "RAINY") return Weather.RAINY;
		else if (s == "WINDY") return Weather.WINDY;
		else return Weather.STORM;
	}
}
