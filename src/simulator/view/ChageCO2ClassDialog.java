package simulator.view;

import javax.swing.*;

import simulator.control.Controller;
import simulator.model.Event;
import simulator.model.RoadMap;
import simulator.model.TrafficSimObserver;
import simulator.model.Vehicle;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.List;

public class ChageCO2ClassDialog extends JDialog{
    private JComboBox<String> vehicleComboBox;
    private JSpinner classSpinner;
    private JSpinner ticksSpinner;
    private JButton okButton;
    private JButton cancelButton;
    private Controller c;

    private boolean okPressed = false;

    public ChageCO2ClassDialog(Frame parent, String[] vehicles,Controller c) {
        super(parent, "Cambiar Clase de Contaminación", true);
        setLayout(new GridLayout(4, 2));
        
        this.c=c;

        // Componentes del diálogo
        add(new JLabel("Vehicle:"));
        vehicleComboBox = new JComboBox<>(vehicles);
        add(vehicleComboBox);

        add(new JLabel("CO2 Class:"));
        classSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 10, 1));
        add(classSpinner);

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

    public String getSelectedVehicle() {
        return vehicleComboBox.getSelectedItem().toString();
    }

    public int getSelectedClass() {
        return (int) classSpinner.getValue();
    }

    public int getSelectedTicks() {
        return (int) ticksSpinner.getValue();
    }
    
    
}