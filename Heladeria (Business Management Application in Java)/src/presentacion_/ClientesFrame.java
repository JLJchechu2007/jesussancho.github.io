package presentacion_;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import presentacion.Controlador.Eventos;
import presentacion.Factoria.FactoriaAbstractaPresentacion;

@SuppressWarnings("serial")
public class ClientesFrame extends JFrame {
private static final String IMG = "resources/Icons/";
	
	public ClientesFrame() {
		this.setTitle("Heladeria - Clientes");
		this.setSize(500, 150);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		this.initGui();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}
	
	private void initGui() {
		JPanel jP = new JPanel(new GridLayout(1, 5, 0, 0));
		
		JButton altaButton = new JButton("");
		this.AddButton(jP, altaButton, e -> { FactoriaAbstractaPresentacion.getInstance().createVista(Eventos.ALTA_CLIENTE); }, IMG + "AltaCliente.png");
		
		JButton bajaButton = new JButton("");
		this.AddButton(jP, bajaButton, e -> { FactoriaAbstractaPresentacion.getInstance().createVista(Eventos.BAJA_CLIENTE); }, IMG + "BajaCliente.png");
		
		JButton buscarButton = new JButton("");
		this.AddButton(jP, buscarButton, e -> { FactoriaAbstractaPresentacion.getInstance().createVista(Eventos.BUSCAR_CLIENTE); }, IMG + "BuscarCliente.jpg");	
		
		JButton listarButton = new JButton("");
		this.AddButton(jP, listarButton, e -> { FactoriaAbstractaPresentacion.getInstance().createVista(Eventos.LISTAR_CLIENTE); }, IMG + "MostrarCliente.jpg");
		
		JButton modificarButton = new JButton("");
		this.AddButton(jP, modificarButton, e -> { FactoriaAbstractaPresentacion.getInstance().createVista(Eventos.MODIFICAR_CLIENTE_INITIAL); }, IMG + "ModificarCliente.png");
		
		this.add(jP, BorderLayout.CENTER);

	}

	private void AddButton(JPanel jp, JButton jb, ActionListener l, String path) {
		jb.addActionListener(l);
		ImageIcon icon = new ImageIcon(path);
		Image img = icon.getImage().getScaledInstance(60, 100, Image.SCALE_SMOOTH);
		jb.setIcon(new ImageIcon(img));
		jb.setSize(75, 75);
		jb.setBackground(Color.WHITE);
		jb.setOpaque(true);
	    jb.setBorderPainted(false);
	    
	    jb.setHorizontalTextPosition(SwingConstants.CENTER);
		jb.setVerticalTextPosition(SwingConstants.BOTTOM);
	    
		jp.add(jb);
	}
}
