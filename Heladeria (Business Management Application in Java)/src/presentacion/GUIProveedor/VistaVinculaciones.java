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

import negocio.Proveedor.TMarcaProveedor;
import negocio.Proveedor.TProveedor;
import presentacion.Controlador.Controlador;
import presentacion.Controlador.Eventos;
import presentacion_.IGUI;

public class VistaVinculaciones extends JFrame implements IGUI{
	private JLabel lIdProv; 
	private JTextField tIdProv; 
	private JLabel lIdMarca; 
	private JTextField tIdMarca; 
	
	private JButton ok; 
	private JButton cancel; 
	public VistaVinculaciones() {
		setTitle("VINCULACIONES MARCA-PROVEEDOR"); 
		JPanel panel = new JPanel(new GridBagLayout()); 
		GridBagConstraints gbc = new GridBagConstraints(); 
		gbc.insets= new Insets(5, 5, 5, 5); 
		gbc.anchor = GridBagConstraints.WEST; 
		this.setContentPane(panel);
		gbc.gridx= 0; 
		gbc.gridy= 0; 
		lIdProv = new JLabel("Id Proveedor: "); 
		panel.add(lIdProv, gbc);
		gbc.gridx= 1; 
		gbc.gridy= 0; 
		tIdProv = new JTextField(20);
		panel.add(tIdProv, gbc); 
		gbc.gridx= 0; 
		gbc.gridy= 1; 
		lIdMarca = new JLabel("Id Marca: "); 
		panel.add(lIdMarca, gbc); 
		gbc.gridx= 1; 
		gbc.gridy= 1; 
		tIdMarca = new JTextField(20);
		panel.add(tIdMarca, gbc); 
		gbc.gridx= 0; 
		gbc.gridy= 2; 
		ok= new JButton("ok"); 
		panel.add(ok, gbc); 
		gbc.gridx= 1; 
		gbc.gridy= 2; 
		cancel = new JButton("cancel"); 
		panel.add(cancel, gbc); 
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false); 
				int idMarca = Integer.parseInt(tIdMarca.getText()); 
				int idProv = Integer.parseInt(tIdProv.getText()); 
				TMarcaProveedor tMarcaProveedor = new TMarcaProveedor(); 
				tMarcaProveedor.setIdMarca(idMarca);
				tMarcaProveedor.setIdProveedor(idProv);
				
				Controlador.getInstancia().accion(Eventos.VINCULACIONES, tMarcaProveedor); 
				VistaVinculaciones.this.dispose(); 
		}
		});
		
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false); 
				VistaVinculaciones.this.dispose(); 
			}
		}); 
		pack(); 
		setLocationRelativeTo(null); 
		this.setVisible(true);
	}
	
	
	@Override
	public void actualizar(int evento, Object datos) {
		if(evento == Eventos.RES_VINCULACIONES_PROVEEDOR_OK) {
			this.dispose(); 
			JOptionPane.showMessageDialog(this, "Se ha vinculado el proveedor"+ (Integer)(((TMarcaProveedor) datos).getIdProveedor())+" con la marca"+(Integer)(((TMarcaProveedor) datos).getIdMarca()));
		}
		else if(evento == Eventos.RES_VINCULACIONES_PROVEEDOR_KO) {
			this.dispose(); 
			JOptionPane.showMessageDialog(this, "No se ha podido vincular"); 
		}
		
	}
}
