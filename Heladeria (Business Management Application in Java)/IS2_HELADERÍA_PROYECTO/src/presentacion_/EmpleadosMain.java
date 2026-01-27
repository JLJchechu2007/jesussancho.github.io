package presentacion_;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import presentacion.Controlador.Eventos;
import presentacion.Factoria.FactoriaAbstractaPresentacion;

public class EmpleadosMain extends JDialog implements ActionListener {
	private JButton botonAlta;
    private JButton botonBaja;
    private JButton botonMostrar;
    private JButton botonBuscar;
    private JButton botonModificar;
    
	public EmpleadosMain() {
		setTitle("Gestion de Empleados");
		JPanel panel= new JPanel();
		
		botonAlta= new JButton();
		this.AddButton(panel, botonAlta, e->{
			this.setVisible(false);
			FactoriaAbstractaPresentacion.getInstance().createVista(Eventos.ALTA_EMPLEADO);
		}, "resources/Icons/altaEmpleado.png");
		
		botonBaja= new JButton();
		this.AddButton(panel, botonBaja, e->{
			this.setVisible(false);
			FactoriaAbstractaPresentacion.getInstance().createVista(Eventos.BAJA_EMPLEADO);
		}, "resources/Icons/eliminarEmpleado.png");
		
		botonMostrar=new JButton();
		this.AddButton(panel, botonMostrar, e->{		
			this.setVisible(false);
			FactoriaAbstractaPresentacion.getInstance().createVista(Eventos.MOSTRAR_EMPLEADO);
		}, "resources/Icons/mostrarEmpleado.png");
		
		botonBuscar=new JButton();
		this.AddButton(panel, botonBuscar, e->{
			this.setVisible(false);
			FactoriaAbstractaPresentacion.getInstance().createVista(Eventos.BUSCAR_EMPLEADO);
			}, "resources/Icons/buscarEmpleado.png");
		
		botonModificar= new JButton();
		this.AddButton(panel, botonModificar, e->{
			this.setVisible(false);
			FactoriaAbstractaPresentacion.getInstance().createVista(Eventos.MODIFICAR_EMPLEADO);
			}, "resources/Icons/modificarEmpleados.png");

		
		
		BoxLayout horizontal= new BoxLayout(panel,BoxLayout.X_AXIS);
		panel.setLayout(horizontal);
		panel.add(botonAlta);
		panel.add(botonBaja);
		panel.add(botonMostrar);
		panel.add(botonBuscar);
		panel.add(botonModificar);
		this.setContentPane(panel);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		this.setModal(true);
	    this.pack();
	    this.setLocationRelativeTo(null);
	    this.setVisible(true);
	}
	
	private void AddButton(JPanel jp, JButton jb, ActionListener l, String path) {
		jb.addActionListener(l);

		ImageIcon icon = new ImageIcon(path);
		Image img = icon.getImage().getScaledInstance(60, 100, Image.SCALE_SMOOTH);
		jb.setIcon(new ImageIcon(img));


		jb.setBackground(Color.WHITE);
		jb.setOpaque(true);
		jb.setBorderPainted(false);

		jb.setContentAreaFilled(false);

		jp.add(jb);
	}

}
