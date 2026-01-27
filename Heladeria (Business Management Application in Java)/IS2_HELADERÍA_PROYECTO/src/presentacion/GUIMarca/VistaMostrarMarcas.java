package presentacion.GUIMarca;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import negocio.Marca.TMarca;
import presentacion.Controlador.Controlador;
import presentacion.Controlador.Eventos;
import presentacion_.IGUI;



public class VistaMostrarMarcas extends JFrame implements IGUI{
	
	private JLabel mensaje;
	private JButton aceptar;
	private JButton cancelar;
	
	public VistaMostrarMarcas() {
		super();
		setTitle("MOSTRAR MARCAS");
		crearcomponentes();
		configurarcomponenetes();
		crearoyentes();
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	

	private void crearcomponentes() {
		mensaje=new JLabel("¿Desea mostrar todas las marcas?");
		aceptar=new JButton("Aceptar");
		cancelar= new JButton ("Cancelar");
		
	}
	
	private void configurarcomponenetes() {
		JPanel p2= new JPanel();
		BoxLayout horizontal2=new BoxLayout(p2, BoxLayout.X_AXIS);
		p2.setLayout(horizontal2);
		p2.add(cancelar);
		p2.add(aceptar);
		
		JPanel principal= new JPanel();
		BoxLayout vertical= new BoxLayout(principal, BoxLayout.Y_AXIS);
		principal.setLayout(vertical);
		principal.add(mensaje);
		principal.add(p2);
		this.setContentPane(principal);
		
	}

	private void crearoyentes() {
		aceptar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				VistaMostrarMarcas.super.setVisible(false);
				VistaMostrarMarcas.this.dispose();
				TMarca tmarca= new TMarca();
				Controlador.getInstancia().accion(Eventos.MOSTRAR_MARCAS, tmarca);
			}
		});
		cancelar.addActionListener( (e)->{this.dispose();});
	}

	
	@Override
	public void actualizar(int evento, Object datos) {
		if(evento==Eventos.RES_MOSTRAR_MARCAS_OK) {
			this.dispose();
			List<TMarca> listamarcas = (ArrayList<TMarca>)datos;
			JTable tablamarcas= new JTable(new TablaMarcas(listamarcas));
			JScrollPane scroll = new JScrollPane(tablamarcas);
			JOptionPane.showMessageDialog(null, scroll, "Marcas listadas:", JOptionPane.INFORMATION_MESSAGE);
	    }
		else if(evento==Eventos.RES_MOSTRAR_MARCAS_KO) {
			this.dispose();
			JOptionPane.showMessageDialog(this, "No se han podido mostrar las marcas");
		}
		
	}


}
