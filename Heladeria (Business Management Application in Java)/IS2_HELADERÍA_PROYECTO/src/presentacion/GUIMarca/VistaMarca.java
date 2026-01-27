package presentacion.GUIMarca;

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
import presentacion_.IGUI;

public class VistaMarca extends JDialog implements ActionListener{

	private JButton anadir;
	private JButton eliminar;
	private JButton modificar;
	private JButton buscar;
	private JButton mostrar;
	
	public VistaMarca() {
		super();
		setTitle("Marca");
		crearcomponentes();
		configurarcomponentes();
		crearoyentes();
	}
	
	private void ponerIcono(String s, JButton boton){
		ImageIcon icono = new ImageIcon(s);
		Image imagen = icono.getImage().getScaledInstance(70, 90, Image.SCALE_SMOOTH);
		boton.setIcon(new ImageIcon(imagen));
		boton.setBackground(Color.WHITE);
		boton.setOpaque(true);
		boton.setBorderPainted(false);
		boton.setContentAreaFilled(false);
	}
	
	private void crearcomponentes() {
		anadir=new JButton();
		ponerIcono("resources/Icons/Añadirmarca.png",anadir);
		eliminar=new JButton();
		ponerIcono("resources/Icons/Eliminarmarca.png",eliminar);
		modificar=new JButton();
		ponerIcono("resources/Icons/Modificarmarca.png",modificar);
		buscar=new JButton();
		ponerIcono("resources/Icons/Buscarmarca.png",buscar);
		mostrar=new JButton();
		ponerIcono("resources/Icons/Mostrarmarcas.png",mostrar);
	}
	
	private void configurarcomponentes() {
		JPanel principal= new JPanel();
		BoxLayout horizontal= new BoxLayout(principal, BoxLayout.X_AXIS);
		principal.setLayout(horizontal);
		principal.add(anadir);
		principal.add(eliminar);
		principal.add(modificar);
		principal.add(buscar);
		principal.add(mostrar);
		this.setContentPane(principal);
	}
	
	private void crearoyentes() {
		anadir.addActionListener((e)-> {
			this.setVisible(false);
			FactoriaAbstractaPresentacion.getInstance().createVista(Eventos.AÑADIR_MARCA);});
		eliminar.addActionListener((e)-> {
			this.setVisible(false);
			FactoriaAbstractaPresentacion.getInstance().createVista(Eventos.ELIMINAR_MARCA);});
		modificar.addActionListener((e)-> {
			this.setVisible(false);
			FactoriaAbstractaPresentacion.getInstance().createVista(Eventos.MODIFICAR_MARCA);});
		buscar.addActionListener((e)-> {
			this.setVisible(false);
			FactoriaAbstractaPresentacion.getInstance().createVista(Eventos.BUSCAR_MARCA);});
		mostrar.addActionListener((e)-> {
			this.setVisible(false);
			FactoriaAbstractaPresentacion.getInstance().createVista(Eventos.MOSTRAR_MARCAS);});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.setModal(true);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

}
