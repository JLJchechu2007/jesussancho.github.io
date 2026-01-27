package simulator.view;

import java.util.Collection;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import simulator.control.Controller;
import simulator.model.Event;
import simulator.model.Junction;
import simulator.model.RoadMap;
import simulator.model.TrafficSimObserver;

public class JunctiosTableModel extends AbstractTableModel implements TrafficSimObserver{
	
	public List<Junction> junc;
	private String[] columNames= {"Id","Green", "Queues"};
	
	public JunctiosTableModel(Controller c) {
		this.junc=c.getJunction();
		c.addObserver(this);
	}

	@Override
	public int getRowCount() {
		return junc.size();
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
		Junction j= junc.get(rowIndex);
		switch(columnIndex) {
		case 0: return j.getId();
		case 1 : return j.getGreen();
		case 2: return j.getColas();
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
