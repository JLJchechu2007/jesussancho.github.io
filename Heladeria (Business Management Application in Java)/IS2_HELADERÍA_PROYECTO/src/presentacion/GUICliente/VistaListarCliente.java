package presentacion.GUICliente;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import messages.Messages;
import negocio.Cliente.TransferCliente;
import presentacion.Controlador.Controlador;
import presentacion.Controlador.ControladorImp;
import presentacion.Controlador.Eventos;
import presentacion_.IGUI;

@SuppressWarnings("serial")
public class VistaListarCliente extends JFrame implements IGUI {
	private Controlador ctrl;
	
	
    //-------CONSTRUCTORES-------//   
	
	public VistaListarCliente() {
		this.setTitle("BUSCAR CLIENTES");
		this.setSize(300, 150);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.ctrl = ControladorImp.getInstancia();
		
		this.InitGui();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}

	private void InitGui() {
		JPanel mainPanel = new JPanel(new BorderLayout());
		mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		
		JLabel label = new JLabel("Quieres mostrar una la lista de los Clientes?");
	    mainPanel.add(label, BorderLayout.NORTH);
		
	    
	    JPanel buttonPanel = new JPanel();
		JButton save = new JButton("SI");
		save.setPreferredSize(new Dimension(100, 40));
		save.addActionListener(e -> {
			try {
				ctrl.accion(Eventos.LISTAR_CLIENTE, null);
			} catch(Exception ex) { JOptionPane.showMessageDialog(null, Messages.BUSCAR_CLIENTE_SIN_EXITO); }
			this.dispose();
		});
		JButton cancel = new JButton("No");
		cancel.setPreferredSize(new Dimension(100, 40));
		cancel.addActionListener(e -> { this.dispose(); });
		
	    buttonPanel.add(cancel);
	    buttonPanel.add(save);
	    
	    mainPanel.add(buttonPanel, BorderLayout.SOUTH);
	    
	    
	    this.add(mainPanel);
	}
	
	//-------ACTUALIZAR GUI-------//   

	@SuppressWarnings("unchecked")
	@Override
	public void actualizar(int evento, Object datos) {
		this.dispose();
		if (evento == Eventos.EXITO_LISTAR_CLIENTE) {
			this.GUIListingClientes((List<TransferCliente>)datos);
		} else JOptionPane.showMessageDialog(null, Messages.LISTAR_CLIENTE_SIN_EXITO);
		
	}
	
	
	//-------PRIVATE-------//   
	
	private void GUIListingClientes(List<TransferCliente> clientes) {
		JFrame listClients = new JFrame("LISTAR CLIENTES");
		listClients.setSize(450, 200);
		listClients.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		
		JPanel mainPanel = new JPanel(new BorderLayout());
		JPanel listPanel = new JPanel();
		listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
	    listPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
	
		for (TransferCliente tC : clientes) {
			listPanel.add(new JLabel("Id: " + tC.getId() + ", DNI: " + tC.getDNI() + ", Nombre: " + tC.getNombre() + 
					", Apellido: " + tC.getApellido() + ", Age: " + tC.getAge()));
		}		
		
		JScrollPane scrollPane = new JScrollPane(listPanel);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		JPanel buttonPanel = new JPanel();
		JButton end = new JButton("Ok");
		end.setPreferredSize(new Dimension(100, 30));
		end.addActionListener(e -> { listClients.dispose(); });
		buttonPanel.add(end);
		
		mainPanel.add(scrollPane, BorderLayout.CENTER);
		mainPanel.add(buttonPanel, BorderLayout.SOUTH);
		
		
		listClients.add(mainPanel);
		listClients.setVisible(true);
		listClients.setLocationRelativeTo(null);
	}
}
