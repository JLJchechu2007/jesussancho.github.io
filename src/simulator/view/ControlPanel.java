package simulator.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import simulator.control.Controller;
import simulator.model.Event;
import simulator.model.Road;
import simulator.model.RoadMap;
import simulator.model.SimulatedObject;
import simulator.model.TrafficSimObserver;
import simulator.model.Vehicle;
import simulator.model.Weather;

import javax.swing.*;

public class ControlPanel extends JPanel implements TrafficSimObserver{
	
	JFrame frame;
	
	JButton file_button;
	JButton contamination_button;
	JButton weather_button;
	JButton run_button;
	JButton stop_button;
	JButton exit_button;
	JSpinner ticks_spinner;
	
	Controller c;
	
	boolean _stopped;
	int ticks;
	
	public ControlPanel(Controller c) {
		this.c = c;
		_stopped = false; // Indicar que la simulación está en ejecución
		frame = new JFrame("Traffic Simulator");
		frame.setSize(400, 300);
		setLayout(new BorderLayout());
		
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		
		add(p1, BorderLayout.WEST);
		add(p2, BorderLayout.EAST);
		
		file_button = new JButton();
		file_button.setIcon(new ImageIcon("resources/icons/open.png"));
		file_button.addActionListener(new FileChooserCarretera(frame, c));
		p1.add(file_button);
		
		contamination_button = new JButton();
		contamination_button.setIcon(new ImageIcon("resources/icons/co2class.png"));
		contamination_button.addActionListener(new ContaminationCarretera(frame,listaToStringV(c.getVehicles()),c));
		p1.add(contamination_button);
		
		weather_button = new JButton();
		weather_button.setIcon(new ImageIcon("resources/icons/weather.png"));
		weather_button.addActionListener(new TiempoAtmosferico(frame,listaToStringV(c.getVehicles()),c));	
		p1.add(weather_button);
		
		run_button = new JButton();
		run_button.setIcon(new ImageIcon("resources/icons/run.png"));
		run_button.addActionListener(e -> {
			_stopped=false;
		    this.ticks = (Integer) ticks_spinner.getValue(); // Obtener el valor del spinner
		   
		    run_sim(ticks);
		
		    
		    
		    //_stopped = false; // Indicar que la simulación está en ejecución
		    
		});
		p1.add(run_button);
		
		stop_button = new JButton();
		stop_button.setIcon(new ImageIcon("resources/icons/stop.png"));
		stop_button.addActionListener(e -> {
		    _stopped = true; // Indicar que la simulación debe detenerse
		    run_button.setEnabled(true); // Habilitar el botón RUN
		    stop_button.setEnabled(false); // Deshabilitar el botón STOP
		    ticks_spinner.setEnabled(true); // Habilitar el spinner
		});
		p1.add(stop_button);
		
        p1.add(new JLabel("Ticks:"));
		ticks_spinner = new JSpinner(new SpinnerNumberModel(10, 1, Integer.MAX_VALUE, 1));
		p1.add(ticks_spinner);
		
		exit_button = new JButton();
		exit_button.setIcon(new ImageIcon("resources/icons/exit.png"));
		exit_button.addActionListener(new Exit());
		p2.add(exit_button);
	}

	@Override
	public void onAdvance(RoadMap map, Collection<Event> events, int time) {}

	@Override
	public void onEventAdded(RoadMap map, Collection<Event> events, Event e, int time) {}

	@Override
	public void onReset(RoadMap map, Collection<Event> events, int time) {}

	@Override
	public void onRegister(RoadMap map, Collection<Event> events, int time) {}
	
	String[] listaToStringV(List<Vehicle> list){
		String[] dev=new String[list.size()];
		for(int i = 0; i < list.size(); i++) {
			dev[i] = list.get(i).toString();
		}
		return dev;
	}
	
	String[] listaToStringR(List<Road> list){
		String[] dev=new String[list.size()];
		for(int i = 0; i < list.size(); i++) {
			dev[i] = list.get(i).toString();
		}
		return dev;
	}
	
	private void run_sim(int n) {
		if (n > 0 && !_stopped) {
			try {
				c.run(1);
	         		SwingUtilities.invokeLater(() -> run_sim(n - 1));
	         		run_button.setEnabled(false); // Deshabilitar el botón RUN
	    		    stop_button.setEnabled(true); // Habilitar el botón STOP
	    		    ticks_spinner.setEnabled(false); // Deshabilitar el spinner
			} catch (Exception e) {
				// TODO show error message
				_stopped = true;
				// TODO enable the toolbar
			}
		} else {
			_stopped = true;
			run_button.setEnabled(true); // Habilitar el botón RUN
		    stop_button.setEnabled(false); // Deshabilitar el botón STOP
		    ticks_spinner.setEnabled(true); // Habilitar el spinner
	                // TODO enable the toolbar
		}
		/*try {
			Thread.sleep(100);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}*/
	}
}
