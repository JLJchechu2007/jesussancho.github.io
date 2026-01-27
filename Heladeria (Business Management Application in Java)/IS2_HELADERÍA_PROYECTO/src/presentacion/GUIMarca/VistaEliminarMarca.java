package presentacion.GUIMarca;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import negocio.Marca.TMarca;
import presentacion.Controlador.Controlador;
import presentacion.Controlador.Eventos;
import presentacion_.IGUI;


public class VistaEliminarMarca extends JFrame implements IGUI{
	
	private JLabel lid;
	private JTextField id;
	private JButton aceptar;
	private JButton cancelar;
	
	public VistaEliminarMarca() {
		super();
		setTitle("ELIMINAR MARCA");
		crearcomponentes();
		configurarcomponenetes();
		crearoyentes();
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	

	private void crearcomponentes() {
		lid=new JLabel("Id de la marca:");
		id= new JTextField(20);
		aceptar=new JButton("Aceptar");
		cancelar= new JButton ("Cancelar");
		
	}
	
	private void configurarcomponenetes() {
		JPanel p1= new JPanel();
		BoxLayout horizontal=new BoxLayout(p1, BoxLayout.X_AXIS);
		p1.setLayout(horizontal);
		p1.add(lid);
		p1.add(id);
		
		JPanel p2= new JPanel();
		BoxLayout horizontal2=new BoxLayout(p2, BoxLayout.X_AXIS);
		p2.setLayout(horizontal2);
		p2.add(cancelar);
		p2.add(aceptar);
		
		JPanel principal= new JPanel();
		BoxLayout vertical= new BoxLayout(principal, BoxLayout.Y_AXIS);
		principal.setLayout(vertical);
		principal.add(p1);
		principal.add(p2);
		this.setContentPane(principal);
	}

	private void crearoyentes() {
		aceptar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				VistaEliminarMarca.super.setVisible(false);
				VistaEliminarMarca.this.dispose();
				int _id=Integer.parseInt(id.getText());
				TMarca tmarca= new TMarca();
				tmarca.setId(_id);
				Controlador.getInstancia().accion(Eventos.ELIMINAR_MARCA, tmarca);
			}
		});
		cancelar.addActionListener( (e)->{this.dispose();});
	}

	@Override
	public void actualizar(int evento, Object datos) {
		if(evento==Eventos.RES_ELIMINAR_MARCA_OK) {
			this.dispose();
			JOptionPane.showMessageDialog(this, "Se ha eliminado correctamente la marca con id "+ (Integer)datos);
		}
		else if(evento==Eventos.RES_ELIMINAR_MARCA_KO) {
			this.dispose();
			JOptionPane.showMessageDialog(this, "No se ha podido eliminar la marca");
		}
		
	}


}
