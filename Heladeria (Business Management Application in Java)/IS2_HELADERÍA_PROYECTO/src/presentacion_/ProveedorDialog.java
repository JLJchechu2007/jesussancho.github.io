package presentacion_;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import presentacion.Controlador.Eventos;
import presentacion.Factoria.FactoriaAbstractaPresentacion;

public class ProveedorDialog extends JDialog{
	JButton añadirProveedor; 
	JButton buscarProveedor;
	JButton modificarProveedor; 
	JButton mostrarProveedores; 
	JButton eliminarProveedor; 
	JButton vinculaciones; 
	
	public ProveedorDialog() {
		super(new JFrame(), "Proveedor");  
		initGUI(); 
		pack(); 
		setLocationRelativeTo(null);
	}
	private void initGUI() {
		JPanel panel = new JPanel(); 
		this.setContentPane(panel);
		BoxLayout horizontal= new BoxLayout(panel,BoxLayout.X_AXIS);
		panel.setLayout(horizontal);
		añadirProveedor = new JButton("Añadir Proveedor"); 
		añadirProveedor.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				FactoriaAbstractaPresentacion.getInstance().createVista(Eventos.NUEVO_PROVEEDOR); 	
			}
		}); 
		panel.add(añadirProveedor); 
		buscarProveedor = new JButton("Leer Proveedor"); 
		buscarProveedor.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				FactoriaAbstractaPresentacion.getInstance().createVista(Eventos.BUSCAR_PROVEEDOR); 	
			}
		}); 
		panel.add(buscarProveedor); 
		modificarProveedor = new JButton("Modificar Proveedor"); 
		modificarProveedor.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				FactoriaAbstractaPresentacion.getInstance().createVista(Eventos.MODIFICAR_PROVEEDOR1); 	
			}
		}); 
		panel.add(modificarProveedor);
	 
		mostrarProveedores = new JButton("Mostrar Proveedores"); 
		mostrarProveedores.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				FactoriaAbstractaPresentacion.getInstance().createVista(Eventos.MOSTRAR_PROVEEDOR); 	
			}
		}); 
		panel.add(mostrarProveedores); 
		eliminarProveedor = new JButton("Eliminar Proveedor"); 
		eliminarProveedor.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				FactoriaAbstractaPresentacion.getInstance().createVista(Eventos.ELIMINAR_PROVEEDOR); 	
			}
		}); 
		panel.add(eliminarProveedor); 
		vinculaciones = new JButton("Vinculación Marca-Proveedor"); 
		vinculaciones.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				FactoriaAbstractaPresentacion.getInstance().createVista(Eventos.VINCULACIONES); 	
			}
		}); 
		panel.add(vinculaciones); 
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

}
