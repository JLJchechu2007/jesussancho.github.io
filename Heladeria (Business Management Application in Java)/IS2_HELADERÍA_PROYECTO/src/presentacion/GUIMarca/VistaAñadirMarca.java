package presentacion.GUIMarca;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import negocio.Marca.TMarca;
import presentacion.Controlador.Controlador;
import presentacion.Controlador.Eventos;
import presentacion_.IGUI;

public class VistaAñadirMarca extends JFrame implements IGUI{
	
	private JLabel lnombre;
	private JTextField nombre;
	private JLabel lnombrejefe;
	private JTextField nombrejefe;
	private JLabel llugarsede;
	private JTextField lugarsede;
	private JButton aceptar;
	private JButton cancelar;
	
	public VistaAñadirMarca() {
		super();
		setTitle("AÑADIR MARCA");
		crearcomponentes();
		configurarcomponenetes();
		crearoyentes();
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	

	private void crearcomponentes() {
		lnombre=new JLabel("Nombre de la marca:");
		nombre= new JTextField(20);
		lnombrejefe=new JLabel("Nombre del jefe de la marca:");
		nombrejefe= new JTextField(20);
		llugarsede=new JLabel("Lugar de la sede de la marca:");
		lugarsede= new JTextField(20);
		aceptar=new JButton("Aceptar");
		cancelar= new JButton ("Cancelar");
		
	}
	
	private void configurarcomponenetes() {
		JPanel p1= new JPanel();
		BoxLayout horizontal=new BoxLayout(p1, BoxLayout.X_AXIS);
		p1.setLayout(horizontal);
		p1.add(lnombre);
		p1.add(nombre);
		
		JPanel p3= new JPanel();
		BoxLayout horizontal3=new BoxLayout(p3, BoxLayout.X_AXIS);
		p3.setLayout(horizontal3);
		p3.add(lnombrejefe);
		p3.add(nombrejefe);
		
		JPanel p4= new JPanel();
		BoxLayout horizontal4=new BoxLayout(p4, BoxLayout.X_AXIS);
		p4.setLayout(horizontal4);
		p4.add(llugarsede);
		p4.add(lugarsede);
		
		JPanel p2= new JPanel();
		BoxLayout horizontal2=new BoxLayout(p2, BoxLayout.X_AXIS);
		p2.setLayout(horizontal2);
		p2.add(cancelar);
		p2.add(aceptar);
		
		JPanel principal= new JPanel();
		BoxLayout vertical= new BoxLayout(principal, BoxLayout.Y_AXIS);
		principal.setLayout(vertical);
		principal.add(p1);
		principal.add(p3);
		principal.add(p4);
		principal.add(p2);
		this.setContentPane(principal);
		
	}

	private void crearoyentes() {
		aceptar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				VistaAñadirMarca.super.setVisible(false);
				VistaAñadirMarca.this.dispose();
				TMarca tmarca= new TMarca();
				try {
					String _nombre= nombre.getText();
					String _nombrejefe= nombrejefe.getText();
					String _lugarsede= lugarsede.getText();
					tmarca.setNombre(_nombre);
					tmarca.setNombreJefe(_nombrejefe);
					tmarca.setLugarSede(_lugarsede);
				}
				catch(NullPointerException ex) {}
				Controlador.getInstancia().accion(Eventos.AÑADIR_MARCA, tmarca);
			}
		});
		cancelar.addActionListener( (e)->{this.dispose();});
	}

	@Override
	public void actualizar(int evento, Object datos) {
		if(evento==Eventos.RES_AÑADIR_MARCA_OK) {
			this.dispose();
			JOptionPane.showMessageDialog(this, "Se ha añadido correctamente la marca con id "+ (Integer)datos);
		}
		else if(evento==Eventos.RES_AÑADIR_MARCA_KO) {
			this.dispose();
			JOptionPane.showMessageDialog(this, "No se ha podido añadir la marca");
		}
		
	}



}