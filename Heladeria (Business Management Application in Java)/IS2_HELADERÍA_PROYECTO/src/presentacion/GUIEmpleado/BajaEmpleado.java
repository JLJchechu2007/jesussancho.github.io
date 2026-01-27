package presentacion.GUIEmpleado;

import javax.swing.*;

import negocio.Empleado.TransferEmpleado;
import negocio.Empleado.TransferVendedor;
import presentacion.Controlador.Controlador;
import presentacion.Controlador.ControladorImp;
import presentacion.Controlador.Eventos;
import presentacion_.IGUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BajaEmpleado extends JFrame implements IGUI {

    private JTextField campoDNI;
    private JButton botonEliminar;
    private JButton botonVolver;

    public BajaEmpleado() {
        initGUI();
    }

    private void initGUI() {
        setTitle("BAJA EMPLEADO");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel superior con el título
        JLabel titulo = new JLabel("BAJA EMPLEADO", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        add(titulo, BorderLayout.NORTH);

        // Panel central con el label y el campo de DNI
        JPanel panelCentro = new JPanel(new GridLayout(2, 2, 10, 10));
        panelCentro.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panelCentro.add(new JLabel("Introduce DNI:", SwingConstants.RIGHT));
        campoDNI = new JTextField();
        panelCentro.add(campoDNI);

        add(panelCentro, BorderLayout.CENTER);

        // Panel inferior con los botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));

        botonEliminar = new JButton("Eliminar");
        botonVolver = new JButton("Volver");

        panelBotones.add(botonEliminar);
        panelBotones.add(botonVolver);

        add(panelBotones, BorderLayout.SOUTH);

        // Listeners
        botonEliminar.addActionListener(e->{
        	this.setVisible(false);
        	String dni=campoDNI.getText();
        	TransferEmpleado t= new TransferVendedor();
        	t.setDNI(dni);
        	Controlador.getInstancia().accion(Eventos.BAJA_EMPLEADO, t);
        	});

        botonVolver.addActionListener(e->this.dispose());
        setVisible(true);
    }

	@Override
	public void actualizar(int evento, Object datos) {
		if(evento==Eventos.RES_BAJA_EMPLEADO_OK) {
			JOptionPane.showMessageDialog(this, "Empleado dado de baja correctamente. ID: " + (int) datos);
		}
		else if(evento==Eventos.RES_BAJA_EMPLEADO_KO) {
			JOptionPane.showMessageDialog(this, "Error al dar de baja el empleado o empleado no encontrado");
		}
	}


}