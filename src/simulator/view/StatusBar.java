package simulator.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.util.Collection;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import simulator.control.Controller;
import simulator.model.Event;
import simulator.model.RoadMap;
import simulator.model.TrafficSimObserver;

public class StatusBar extends JPanel implements TrafficSimObserver{
	
	private JLabel timeLabel;
	private JTextArea eventText;
	
	public StatusBar(Controller c) {
		setLayout(new BorderLayout());
		
		timeLabel = new JLabel("Tiempo: 0");
		add(timeLabel, BorderLayout.WEST);
		
		eventText = new JTextArea();
		eventText.setFont(new Font("Arial", Font.BOLD, 12));
		eventText.setLineWrap(true);
		eventText.setWrapStyleWord(true);
		//eventText.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(eventText);
		add(scrollPane, BorderLayout.EAST);
		eventText.setPreferredSize(new Dimension(1300, 25));
		eventText.setText("Welcome!");
		eventText.setMargin(new Insets(6, 10, 0, 10));
		c.addObserver(this);
	}
	
	@Override
	public void onAdvance(RoadMap map, Collection<Event> events, int time) {
		timeLabel.setText("Tiempo: " + time);
		eventText.setVisible(false);
	}

	@Override
	public void onEventAdded(RoadMap map, Collection<Event> events, Event e, int time) {
		timeLabel.setText("Tiempo: " + time);
		eventText.setText("Event Added (" + e.toString() + ")");
		eventText.setVisible(true);
		
	}

	@Override
	public void onReset(RoadMap map, Collection<Event> events, int time) {
		timeLabel.setText("Tiempo: " + time);
	}

	@Override
	public void onRegister(RoadMap map, Collection<Event> events, int time) {
		timeLabel.setText("Tiempo: " + time);
	}
	
}
