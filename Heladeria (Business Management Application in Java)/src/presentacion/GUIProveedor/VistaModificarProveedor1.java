package presentacion.GUIProveedor;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JFrame;

import negocio.Proveedor.TProveedor;
import presentacion.Controlador.Controlador;
import presentacion.Controlador.Eventos;
import presentacion_.IGUI;

public class VistaModificarProveedor1 extends JFrame implements IGUI{
	private JLabel lId; 
	private JTextField tId; 
	 
	
	private JButton ok; 
	private JButton cancel; 
	
	public VistaModificarProveedor1() {
		setTitle("MODIFICAR PROVEEDOR"); 
		JPanel panel = new JPanel(new GridBagLayout()); 
		GridBagConstraints gbc = new GridBagConstraints(); 
		gbc.insets= new Insets(5, 5, 5, 5); 
		gbc.anchor = GridBagConstraints.WEST; 
		this.setContentPane(panel);
		gbc.gridx= 0; 
		gbc.gridy= 0; 
		lId = new JLabel("Id: "); 
		panel.add(lId, gbc);
		gbc.gridx= 1; 
		gbc.gridy= 0; 
		tId = new JTextField(20);
		panel.add(tId, gbc); 
		gbc.gridx= 0; 
		gbc.gridy= 1; 
		ok= new JButton("ok"); 
		panel.add(ok, gbc); 
		gbc.gridx= 1; 
		gbc.gridy= 1; 
		cancel = new JButton("cancel"); 
		panel.add(cancel, gbc); 
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false); 
				int id = Integer.parseInt(tId.getText()); 
				TProveedor tProveedor = new TProveedor(); 
				tProveedor.setId(id); 
				
				Controlador.getInstancia().accion(Eventos.MODIFICAR_PROVEEDOR1, tProveedor); 
				VistaModificarProveedor1.this.dispose(); 
		}
		});
		
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false); 
				VistaModificarProveedor1.this.dispose(); 
			}
		}); 
		pack(); 
		setLocationRelativeTo(null); 
		this.setVisible(true);
	}
	
	public void actualizar(int evento, Object datos) {
		this.dispose(); 
		if(evento == Eventos.RES_MODIFICAR_PROVEEDOR1_KO) {
			JOptionPane.showMessageDialog(this, "No se ha encontrado ese proveedor");

		}
	}
	
}


