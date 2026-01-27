package simulator.view;

import java.util.Collection;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import simulator.control.Controller;
import simulator.model.Event;
import simulator.model.RoadMap;
import simulator.model.TrafficSimObserver;
import simulator.model.Vehicle;
import simulator.model.VehicleStatus;

public class VehiclesTableModel extends AbstractTableModel implements TrafficSimObserver{

	private List<Vehicle> vehicles;
	private String[] columNames= {"Id","Location","Itinerary","CO2 Class","Max. Speed", "Speed", "Total CO2", "Distance"};
	
	public VehiclesTableModel(Controller c) {
		this.vehicles=c.getVehicles();
		c.addObserver(this);
	}
	
@Override
public int getRowCount() {
	return vehicles.size();
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
	Vehicle v=vehicles.get(rowIndex);
	switch (columnIndex) {
	case 0: return v.getId();
	case 1: return v.getLocation();
	case 2: return v.getItinerary();
	case 3: return v.getContClass();
	case 4: return v.getMaxSpeed();
	case 5: return v.getSpeed();
	case 6: return v.getTotalCO2();
	case 7: return v.getDistance();
	default: return null;
	}
}

private String getVehicleState(Vehicle v) {
	return VehicleStatus.printEstado(v.getStatus());	
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
