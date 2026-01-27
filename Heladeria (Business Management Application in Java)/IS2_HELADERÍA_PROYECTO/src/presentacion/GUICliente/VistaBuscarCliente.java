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
import negocio.Cliente.TransferCliente;
import presentacion.Controlador.Controlador;
import presentacion.Controlador.ControladorImp;
import presentacion.Controlador.Eventos;
import presentacion_.IGUI;

@SuppressWarnings("serial")
public class VistaBuscarCliente extends JFrame implements IGUI {
	private JTextField FieldId;
	private Controlador ctrl;
	
	
    //-------CONSTRUCTORES-------//   
	
	public VistaBuscarCliente() {
		this.setTitle("BUSCAR CLIENTES");
		this.setSize(300, 150);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.ctrl = ControladorImp.getInstancia();
		
		this.InitGui();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}
	
	@SuppressWarnings("unused")
	private void InitGui() {
		JPanel jp = new JPanel(new GridLayout(2, 2, 10, 10));
		jp.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		
		jp.add(new JLabel("Id:"));
		this.FieldId = new JTextField();
		jp.add(this.FieldId);		
		
		JButton save = new JButton("Ok");
		save.addActionListener(e -> {
			try {
				int id = Integer.parseInt(FieldId.getText());
				TransferCliente tCliente = new TransferCliente();
				tCliente.setId(id);
				ctrl.accion(Eventos.BUSCAR_CLIENTE, tCliente);
			} catch(Exception ex) { JOptionPane.showMessageDialog(null, Messages.BUSCAR_CLIENTE_SIN_EXITO); }
			this.dispose();
		});
		JButton cancel = new JButton("Cancelar");
		cancel.addActionListener(e -> { this.dispose(); });
		
		jp.add(cancel);
		jp.add(save);
		
		this.add(jp);
	}

	
	//-------ACTUALIZAR GUI-------//   
	
	@Override
	public void actualizar(int evento, Object datos) {
		this.dispose();
		if (evento == Eventos.EXITO_BUSCAR_CLIENTE) {
			TransferCliente tC = (TransferCliente)datos;
			JPanel panel = new JPanel(new GridLayout(5, 1));
			
			panel.add(new JLabel("Id: " + tC.getId()));
			panel.add(new JLabel("DNI: " + tC.getDNI()));
			panel.add(new JLabel("Nombre: " + tC.getNombre()));
			panel.add(new JLabel("Apellido: " + tC.getApellido()));
			panel.add(new JLabel("Age: " + tC.getAge()));
			
			JOptionPane.showMessageDialog(null, panel, "Datos Cliente", JOptionPane.INFORMATION_MESSAGE);
		}
		else JOptionPane.showMessageDialog(null, Messages.BUSCAR_CLIENTE_SIN_EXITO);
	}
}
