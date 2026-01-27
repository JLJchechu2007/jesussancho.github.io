package simulator.view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import simulator.control.Controller;
import simulator.misc.Pair;
import simulator.model.Event;
import simulator.model.SetContClassEvent;
import simulator.model.Vehicle;

public class ContaminationCarretera implements ActionListener{
	JFrame f;
	String[] v;	
	Controller c;
	public ContaminationCarretera(JFrame frame,String[] ve, Controller c) {
		this.f=frame;
		this.v=ve;
		this.c = c;
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		ChageCO2ClassDialog dialog=new ChageCO2ClassDialog(f,listaToStringV(c.getVehicles()),c);
		dialog.setVisible(true);
		if(dialog.isOkPressed()) {
			String vID=dialog.getSelectedVehicle();
			Integer co2Class = dialog.getSelectedClass();
			int ticks = dialog.getSelectedTicks();
			List<Pair<String,Integer>> p= new ArrayList<Pair<String,Integer>>();
			p.add(new Pair<String, Integer>(vID,co2Class));
			Event ev = new SetContClassEvent(ticks+c.getTime(), p);
			c.addEvent(ev);
		}
	}
	
	String[] listaToStringV(List<Vehicle> list){
		String[] dev=new String[list.size()];
		for(int i = 0; i < list.size(); i++) {
			dev[i] = list.get(i).toString();
		}
		return dev;
	}
	
	
}