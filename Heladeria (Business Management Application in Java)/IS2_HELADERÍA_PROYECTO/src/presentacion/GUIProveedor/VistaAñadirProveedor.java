package presentacion.GUIProveedor;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import negocio.Proveedor.TProveedor;
import presentacion.Controlador.Controlador;
import presentacion.Controlador.Eventos;
import presentacion_.IGUI;

public class VistaAñadirProveedor extends JFrame implements IGUI{
	private JLabel lNombre; 
	private JTextField tNombre; 
	private JLabel lTelefono; 
	private JTextField tTelefono; 
	private JLabel lCorreo;
	private JTextField tCorreo; 
	
	private JButton ok; 
	private JButton cancel; 
	
	public VistaAñadirProveedor() {
		setTitle("AÑADIR PROVEEDOR"); 
		JPanel panel = new JPanel(new GridBagLayout()); 
		GridBagConstraints gbc = new GridBagConstraints(); 
		gbc.insets= new Insets(5, 5, 5, 5); 
		gbc.anchor = GridBagConstraints.WEST; 
		this.setContentPane(panel);
		gbc.gridx= 0; 
		gbc.gridy= 0; 
		lNombre = new JLabel("Nombre: "); 
		panel.add(lNombre, gbc);
		gbc.gridx= 1; 
		gbc.gridy= 0; 
		tNombre = new JTextField(20);
		panel.add(tNombre, gbc); 
		gbc.gridx= 0; 
		gbc.gridy= 1; 
		lTelefono = new JLabel("Telefono: "); 
		panel.add(lTelefono, gbc); 
		gbc.gridx= 1; 
		gbc.gridy= 1; 
		tTelefono = new JTextField(20);
		panel.add(tTelefono, gbc); 
		gbc.gridx= 0; 
		gbc.gridy= 2; 
		lCorreo = new JLabel("Correo: "); 
		panel.add(lCorreo, gbc); 
		gbc.gridx= 1; 
		gbc.gridy= 2; 
		tCorreo = new JTextField(20);
		panel.add(tCorreo, gbc); 
		gbc.gridx= 0; 
		gbc.gridy= 3; 
		ok= new JButton("ok"); 
		panel.add(ok, gbc); 
		gbc.gridx= 1; 
		gbc.gridy= 3; 
		cancel = new JButton("cancel"); 
		panel.add(cancel, gbc); 
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VistaAñadirProveedor.super.setVisible(false); 
				String nombre = tNombre.getText(); 
				String telefono = tTelefono.getText(); 
				String correo = tCorreo.getText(); 
				TProveedor tProveedor = new TProveedor(); 
				tProveedor.setNombre(nombre); 
				tProveedor.setTelefono(telefono);
				tProveedor.setCorreo(correo);
				
				Controlador.getInstancia().accion(Eventos.NUEVO_PROVEEDOR, tProveedor); 
				VistaAñadirProveedor.this.dispose(); 
		}
		});
		
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//VistaAñadirProveedor.super.setVisible(false); 
				VistaAñadirProveedor.this.dispose(); 
			}
		}); 
		pack(); 
		setLocationRelativeTo(null); 
		this.setVisible(true);
	}
	
	public void actualizar(int evento, Object datos) {
		if(evento == Eventos.RES_NUEVO_PROVEEDOR_OK) {
			this.dispose(); 
			JOptionPane.showMessageDialog(this, "Se ha creado correctamente el proveedor con id "
					+ datos);
		}
		else if(evento == Eventos.RES_NUEVO_PROVEEDOR_KO) {
			this.dispose(); 
			JOptionPane.showMessageDialog(this, "No se ha podido crear el proveedor");

		}
	}
	
	
}
