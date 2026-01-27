package simulator.view;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Queue;

import javax.swing.table.AbstractTableModel;

import simulator.control.Controller;
import simulator.model.Event;
import simulator.model.RoadMap;
import simulator.model.TrafficSimObserver;

public class EventsTableModel extends AbstractTableModel implements TrafficSimObserver{

	private List<String> columnNames;
	private Queue<Event> events;
	
	public EventsTableModel(Controller c) {
		columnNames = new ArrayList<String> ();
		events = c.getEvents();
		columnNames.add("Time");
		columnNames.add("Desc.");
		c.addObserver(this);
	}
	
	@Override
	public int getRowCount() {
		return events.size();
	}
	
	@Override
	public String getColumnName(int index) {
		return columnNames.get(index);
	}

	@Override
	public int getColumnCount() {
		return 2;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		ArrayList<Event> eventsList = new ArrayList<Event> (events);
		Event event = eventsList.get(rowIndex);
		switch(columnIndex) {
		case 0: return event.getTime();
		case 1: return event.toString();
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
