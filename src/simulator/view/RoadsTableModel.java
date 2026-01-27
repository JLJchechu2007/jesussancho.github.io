package simulator.view;

import java.util.Collection;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import simulator.control.Controller;
import simulator.model.Event;
import simulator.model.Road;
import simulator.model.RoadMap;
import simulator.model.TrafficSimObserver;

public class RoadsTableModel extends AbstractTableModel implements TrafficSimObserver{
	
	private List<Road> roads;
	private String[] columNames= {"Id","Lenght","Weather","Max. Speed", "Speed Limit", "Total CO2","CO2 Limit"};
	
	public RoadsTableModel(Controller c) {
		this.roads=c.getRoads();
		c.addObserver(this);
	}

	@Override
	public int getRowCount() {
		return roads.size();
	}

	@Override
	public int getColumnCount() {
		return columNames.length;
	}
	
	@Override
	public String getColumnName(int i) {
		return columNames[i];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Road r=roads.get(rowIndex);
		switch(columnIndex) {
		case 0: return r.getId();
		case 1: return r.getLength();
		case 2: return r.getWeather();
		case 3: return r.getMaxSpeed();
		case 4: return r.getSpeedLimit();
		case 5: return r.getTotalCO2();
		case 6: return r.getContLimit();
		default: return null;
		}
	}

	@Override
	public void onAdvance(RoadMap map, Collection<Event> events, int time) {
		fireTableStructureChanged();
	}

	@Override
	public void onEventAdded(RoadMap map, Collection<Event> events, Event e, int time) {
		fireTableStructureChanged();	
	}

	@Override
	public void onReset(RoadMap map, Collection<Event> events, int time) {
		fireTableStructureChanged();
	}

	@Override
	public void onRegister(RoadMap map, Collection<Event> events, int time) {
		fireTableStructureChanged();
	}

}
