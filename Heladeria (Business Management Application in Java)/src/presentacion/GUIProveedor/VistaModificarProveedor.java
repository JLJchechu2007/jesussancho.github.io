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

public class VistaModificarProveedor extends JFrame implements IGUI {
	private JLabel lNombre; 
	private JTextField tNombre; 
	private JLabel lTelefono; 
	private JTextField tTelefono; 
	private JLabel lCorreo;
	private JTextField tCorreo; 
	
	private JButton ok; 
	private JButton cancel; 
	private int id; 
	
	public VistaModificarProveedor() {
		setTitle("MODIFICAR PROVEEDOR"); 
		JPanel panel = new JPanel(new GridBagLayout()); 
		GridBagConstraints gbc = new GridBagConstraints(); 
		gbc.insets= new Insets(5, 5, 5, 5); 
		gbc.anchor = GridBagConstraints.WEST; 
		this.setContentPane(panel);
		gbc.gridx= 0; 
		gbc.gridy = 0; 
		lNombre = new JLabel("Nombre: "); 
		panel.add(lNombre, gbc);
		gbc.gridx= 1; 
		gbc.gridy = 0; 
		tNombre = new JTextField(20);
		panel.add(tNombre, gbc); 
		gbc.gridx= 0; 
		gbc.gridy = 1; 
		lTelefono = new JLabel("Telefono: "); 
		panel.add(lTelefono, gbc);
		gbc.gridx= 1; 
		gbc.gridy = 1; 
		tTelefono = new JTextField(20);
		panel.add(tTelefono, gbc); 
		gbc.gridx= 0; 
		gbc.gridy = 2; 
		lCorreo = new JLabel("Correo: "); 
		panel.add(lCorreo, gbc); 
		gbc.gridx= 1; 
		gbc.gridy = 2; 
		tCorreo = new JTextField(20);
		panel.add(tCorreo, gbc); 
		gbc.gridx= 0; 
		gbc.gridy = 3; 
		ok= new JButton("ok"); 
		panel.add(ok, gbc); 
		gbc.gridx= 1; 
		gbc.gridy = 3; 
		cancel = new JButton("cancel"); 
		panel.add(cancel, gbc); 
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false); 
				TProveedor tProveedor = new TProveedor(); 
				tProveedor.setId(id); 
				String nombre, telefono, correo; 
			    nombre = tNombre.getText(); 
				tProveedor.setNombre(nombre); 
			    telefono = tTelefono.getText(); 
				tProveedor.setTelefono(telefono);
				correo = tCorreo.getText();
				tProveedor.setCorreo(correo);
				Controlador.getInstancia().accion(Eventos.MODIFICAR_PROVEEDOR, tProveedor); 
				VistaModificarProveedor.this.dispose(); 
		}
		});
		
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false); 
				VistaModificarProveedor.this.dispose(); 
			}
		}); 
		pack();
		setLocationRelativeTo(null); 
		this.setVisible(true);
	}
	
	public void setDatos(TProveedor tProveedor) {
		id = tProveedor.getId(); 
		tNombre.setText(tProveedor.getNombre());
		tTelefono.setText(tProveedor.getTelefono());
		tCorreo.setText(tProveedor.getCorreo()); 
	}
	public void actualizar(int evento, Object datos) {
		if(evento == Eventos.RES_MODIFICAR_PROVEEDOR_OK) {
			this.dispose(); 
			JOptionPane.showMessageDialog(this, "Se ha modificado correctamente el proveedor con id "
					+ (Integer)datos);
		}
		else if(evento == Eventos.RES_MODIFICAR_PROVEEDOR_KO) {
			this.dispose(); 
			JOptionPane.showMessageDialog(this, "No se ha podido modificar el proveedor");

		}
	}
}

