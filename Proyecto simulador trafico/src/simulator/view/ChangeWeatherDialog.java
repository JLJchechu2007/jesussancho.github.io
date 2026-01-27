package simulator.view;

import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import simulator.control.Controller;
import simulator.model.Event;
import simulator.model.Road;
import simulator.model.RoadMap;
import simulator.model.TrafficSimObserver;
import simulator.model.Weather;

public class ChangeWeatherDialog extends JDialog{
    private JComboBox<String> weatherComboBox;
    private JComboBox<String> roadComboBox;
    private JSpinner ticksSpinner;
    private JButton okButton;
    private JButton cancelButton;
    private Controller c;

    private boolean okPressed = false;
    

    public ChangeWeatherDialog(Frame parent, String[] carreteras, Controller c) {
        super(parent, "Cambiar Clase de Contaminación", true);
        setLayout(new GridLayout(4, 2));
        
        this.c=c;

        // Componentes del diálogo
        add(new JLabel("Road:"));
        roadComboBox = new JComboBox<>(carreteras);
        add(roadComboBox);

        add(new JLabel("Weather:"));
        weatherComboBox = new JComboBox<>(Weather.listaToStringW());
        add(weatherComboBox);

        add(new JLabel("Ticks:"));
        ticksSpinner = new JSpinner(new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1));
        add(ticksSpinner);

        okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                okPressed = true;
                setVisible(false);
            }
        });
        add(okButton);

        cancelButton = new JButton("Cancelar");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        add(cancelButton);

        pack();
        setLocationRelativeTo(parent);
    }

    public boolean isOkPressed() {
        return okPressed;
    }

    public String getSelectedRoad() {
        return roadComboBox.getSelectedItem().toString();
    }
    
    public String getSelectedWeather() {
        return (String) weatherComboBox.getSelectedItem();
    }

    public int getSelectedTicks() {
        return (int) ticksSpinner.getValue();
    }
    
    
}