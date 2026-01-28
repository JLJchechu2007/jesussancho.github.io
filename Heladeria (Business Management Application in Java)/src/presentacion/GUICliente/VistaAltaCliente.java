package presentacion.GUICliente;
 
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import messages.Messages;
import presentacion.Controlador.Controlador;
import presentacion.Controlador.ControladorImp;
import presentacion.Controlador.Eventos;
import presentacion_.IGUI;
import negocio.Cliente.TransferCliente;

@SuppressWarnings("serial")
public class VistaAltaCliente extends JFrame implements IGUI {
	private JTextField FieldDNI;
	private JTextField FieldNombre;
	private JTextField FieldApellido;
	private JTextField FieldAge;
	private Controlador ctrl;
	
	
    //-------CONSTRUCTORES-------//   
	
	public VistaAltaCliente() {
		this.setTitle("ALTA CLIENTES");
		this.setSize(300, 350);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.ctrl = ControladorImp.getInstancia();
		
		this.InitGui();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	@SuppressWarnings("unused")
	private void InitGui() {
		JPanel jp = new JPanel(new GridLayout(5, 2, 10, 10));
		jp.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		
		jp.add(new JLabel("DNI:"));
		this.FieldDNI = new JTextField();
		jp.add(this.FieldDNI);
		
		jp.add(new JLabel("Nombre:"));
		this.FieldNombre = new JTextField();
		jp.add(this.FieldNombre);
		
		jp.add(new JLabel("Apellido:"));
		this.FieldApellido = new JTextField();
		jp.add(this.FieldApellido);
		
		jp.add(new JLabel("Edad:"));
		this.FieldAge = new JTextField();
		jp.add(this.FieldAge);

		JButton save = new JButton("Guardar");
		save.addActionListener(e -> {
			try {
				String DNI = FieldDNI.getText();
				
				String nombre = FieldNombre.getText();
				if (isAlpha(nombre)) nombre = nombre.substring(0, 1).toUpperCase() + nombre.substring(1).toLowerCase();
				else throw new Exception();
				
				String apellido = FieldApellido.getText();
				if (isAlpha(apellido)) apellido = apellido.substring(0, 1).toUpperCase() + apellido.substring(1).toLowerCase();
				else throw new Exception();
				
				int age = Integer.parseInt(FieldAge.getText());
				
				TransferCliente tCliente = new TransferCliente(DNI, nombre, apellido, age);
				ctrl.accion(Eventos.ALTA_CLIENTE, tCliente);
			} catch (Exception ex) { JOptionPane.showMessageDialog(null, Messages.CREAR_CLIENTE_SIN_EXITO); }	
			this.dispose();
		});
		JButton cancel = new JButton("Cancelar");
		cancel.addActionListener(e -> { this.dispose(); });
		
		jp.add(cancel);
		jp.add(save);
		
		this.add(jp);
	}
	
	
    //-------PRIVATE-------//   
	
	private boolean isAlpha(String name) { return name.matches("[a-zA-Z]+"); }

	
	//-------ACTUALIZAR GUI-------//   
	
	@Override
	public void actualizar(int evento, Object datos) {
		this.dispose();
		if(evento == Eventos.EXITO_ALTA_CLIENTE) {
			int id = (int)datos;
			JOptionPane.showMessageDialog(null, Messages.CREAR_CLIENTE_CON_EXITO + id);
		}
		else JOptionPane.showMessageDialog(null, Messages.CREAR_CLIENTE_SIN_EXITO);
	}
}
