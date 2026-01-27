package presentacion.GUIProveedor;

import java.awt.Component;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import negocio.Proveedor.TProveedor;
import presentacion.Controlador.Controlador;
import presentacion.Controlador.Eventos;
import presentacion.GUIProveedor.TablaProveedores;
import presentacion_.IGUI;

public class VistaMostrarProveedor extends JFrame implements IGUI{
	JLabel pregunta; 
	JButton ok; 
	JButton cancel; 
	public VistaMostrarProveedor() {
		setTitle("MOSTRAR PROVEEDORES"); 
		JPanel panel = new JPanel(new GridBagLayout()); 
		GridBagConstraints gbc = new GridBagConstraints(); 
		gbc.insets= new Insets(5, 5, 5, 5); 
		gbc.anchor = GridBagConstraints.WEST; 
		this.setContentPane(panel);
		gbc.gridy= 0; 
		pregunta = new JLabel("¿Quiere ver todos los proveedores?"); 
		panel.add(pregunta,gbc); 
		gbc.gridx= 0; 
		gbc.gridy= 1; 
		gbc.anchor = GridBagConstraints.CENTER; 
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
				Controlador.getInstancia().accion(Eventos.MOSTRAR_PROVEEDOR, null); 
				VistaMostrarProveedor.this.dispose(); 
		}
		});
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false); 
				VistaMostrarProveedor.this.dispose(); 
			}
		}); 
		pack(); 
		setLocationRelativeTo(null); 
		this.setVisible(true);
	}
	//datos va a tener Collection<TProducto>
	public void actualizar(int evento, Object datos) {
		if(evento == Eventos.RES_MOSTRAR_PROVEEDOR_OK) {
			this.dispose(); 
			AbstractTableModel tabla = new TablaProveedores(datos); 
			JScrollPane sp = new JScrollPane(new JTable(tabla)); 
			JOptionPane.showMessageDialog(null, sp, "Tabla de datos", JOptionPane.INFORMATION_MESSAGE);
		}
		else if(evento == Eventos.RES_MOSTRAR_PROVEEDOR_KO) {
			this.dispose(); 
			JOptionPane.showMessageDialog(this, "No se ha podido mostrar los proveedores");

		}
	}
	
}

