package presentacion.GUICliente;

import java.awt.BorderLayout;
import java.awt.Font;
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
public class VistaModificarClienteFinal extends JFrame implements IGUI {
	private JLabel FieldId;
	private JTextField FieldDNI;
	private JTextField FieldNombre;
	private JTextField FieldApellido;
	private JTextField FieldAge;
	private Controlador ctrl;  
	

	public VistaModificarClienteFinal() {
		this.setTitle("MODIFICAR CLIENTES");
		this.setSize(350, 450);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.ctrl = ControladorImp.getInstancia();
		
		this.InitGui();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}
	
	@SuppressWarnings("unused")
	private void InitGui() {
		JPanel mainPanel = new JPanel(new BorderLayout(4, 4));
		
		JLabel info = new JLabel("Cambia los datos para hacer cambios al Cliente");
		info.setFont(new Font("Arial", Font.BOLD, 13));
		info.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));				
		mainPanel.add(info, BorderLayout.NORTH);
		
		JPanel jp = new JPanel(new GridLayout(6, 2, 10, 10));
		jp.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));
		
		jp.add(new JLabel("Id: "));
		this.FieldId = new JLabel("");
		jp.add(this.FieldId);
		
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
					TransferCliente tc = new TransferCliente();
					
					tc.setId(Integer.parseInt(FieldId.getText()));
					tc.setDNI(FieldDNI.getText());
					
					String nombre = FieldNombre.getText();
					if(!nombre.isEmpty() && this.isAlpha(nombre)) tc.setNombre(nombre); 
					else throw new Exception();
					
					String apellido = FieldApellido.getText();
					if(!apellido.isEmpty() && this.isAlpha(apellido)) tc.setApellido(apellido);	
					else throw new Exception();
					
					if(!FieldAge.getText().isEmpty() && Integer.parseInt(FieldAge.getText()) > 17) tc.setAge(Integer.parseInt(FieldAge.getText()));
					else throw new Exception();
					
					ctrl.accion(Eventos.MODIFICAR_CLIENTE_FINAL, tc);
				} catch (Exception ex) { JOptionPane.showMessageDialog(null, Messages.CREAR_CLIENTE_SIN_EXITO); }
				this.dispose();
		});
		JButton cancel = new JButton("Cancelar");		
		cancel.addActionListener(e -> { dispose(); });
		
		jp.add(cancel);
		jp.add(save);
		
		mainPanel.add(jp, BorderLayout.CENTER);
		this.add(mainPanel);
	}
	
	
	//-------SETTERS-------//   
	
	public void setText(Object datos) {
		TransferCliente tCliente = (TransferCliente) datos;
		this.FieldId.setText(String.valueOf(tCliente.getId()));
		this.FieldDNI.setText(tCliente.getDNI());
		this.FieldNombre.setText(tCliente.getNombre());
		this.FieldApellido.setText(tCliente.getApellido());
		this.FieldAge.setText(String.valueOf(tCliente.getAge()));
	}
	
	
    //-------PRIVATE-------//   
	
	private boolean isAlpha(String name) {
	    return name.matches("[a-zA-Z]+");
	}

	
	//-------ACTUALIZAR GUI-------//   
	
	@Override
	public void actualizar(int evento, Object datos) {
		this.dispose();
		if (evento == Eventos.EXITO_MODIFICAR_CLIENTE) {
			TransferCliente tCliente = (TransferCliente)datos;
			JOptionPane.showMessageDialog(null, Messages.MODIFICAR_CLIENTE_CON_EXITO + tCliente.getId());
		} else JOptionPane.showMessageDialog(null, Messages.CREAR_CLIENTE_SIN_EXITO);
		
	}
}
