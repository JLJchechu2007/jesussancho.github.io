package presentacion.GUIEmpleado;

import javax.swing.JFrame;

import negocio.Empleado.TransferEmpleado;
import negocio.Empleado.TransferVendedor;
import presentacion.Controlador.Controlador;
import presentacion.Controlador.ControladorImp;
import presentacion.Controlador.Eventos;
import presentacion_.IGUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class VistaBuscarEmpleado extends JFrame implements IGUI{
	    private JTextField dniField;
	    private JButton aceptarButton;
	    private JButton cancelarButton;

	    public VistaBuscarEmpleado() {
	        setTitle("Buscar Empleado"); // Título de la ventana
	        setSize(300, 180);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setLocationRelativeTo(null);
	        initComponents();
	    }

	    private void initComponents() {
	        JLabel tituloLabel = new JLabel("Buscar Empleado", JLabel.CENTER);
	        tituloLabel.setFont(new Font("Arial", Font.BOLD, 16));

	        JLabel dniLabel = new JLabel("DNI del empleado:");
	        dniField = new JTextField(15);

	        aceptarButton = new JButton("Aceptar");
	        cancelarButton = new JButton("Cancelar");

	        JPanel dniPanel = new JPanel();
	        dniPanel.add(dniLabel);
	        dniPanel.add(dniField);

	        JPanel buttonPanel = new JPanel();
	        buttonPanel.add(aceptarButton);
	        buttonPanel.add(cancelarButton);

	        JPanel mainPanel = new JPanel();
	        mainPanel.setLayout(new BorderLayout(10, 10));
	        mainPanel.add(tituloLabel, BorderLayout.NORTH);
	        mainPanel.add(dniPanel, BorderLayout.CENTER);
	        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

	        add(mainPanel);

	        // Eventos
	        aceptarButton.addActionListener(e->{
	        	this.setVisible(false);
	        	String dni=this.dniField.getText();
	        	TransferEmpleado t= new TransferVendedor();
	        	t.setDNI(dni);
	        	Controlador.getInstancia().accion(Eventos.BUSCAR_EMPLEADO, t);
	        });

	        this.cancelarButton.addActionListener(e->this.dispose());
	        setVisible(true);
	    }

		@Override
		public void actualizar(int evento, Object datos) {
			if(evento==Eventos.RES_BUSCAR_EMPLEADO_OK) {
				TransferEmpleado t=(TransferEmpleado) datos;
				JOptionPane.showMessageDialog(null, "Empleado encontrado:\n" +
                        "Nombre: " + t.getNombre() + "\n" +
                        "Apellido: " + t.getApellido() + "\n" +
                        "DNI: " + t.getDNI() + "\n" +
                        "Sueldo: " + t.getSueldo() + "\n" +
                        "Tipo: " + t.getPuesto() + "\n" +
                        "Activo: " + (t.estaActivo() ? "Sí" : "No"));
			}
			else if(evento==Eventos.RES_BUSCAR_EMPLEADO_KO) {
                JOptionPane.showMessageDialog(this, "Empleado no encontrado");
			}
		}
	
}
