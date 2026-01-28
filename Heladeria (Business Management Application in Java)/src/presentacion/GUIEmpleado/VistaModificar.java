package presentacion.GUIEmpleado;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import negocio.Empleado.Puesto;
import negocio.Empleado.TransferDirectivo;
import negocio.Empleado.TransferEmpleado;
import negocio.Empleado.TransferVendedor;
import presentacion.Controlador.Controlador;
import presentacion.Controlador.ControladorImp;
import presentacion.Controlador.Eventos;
import presentacion_.IGUI;

public class VistaModificar extends JFrame implements IGUI {
	
	private JLabel texto;
	private JButton aceptar;
	private JButton cancelar;
	private JTextField dni;

	private JPanel mainPanel, datosPanel, puestoPanel, detallesPanel, botonesAbajo;
    private JTextField nombreField, apellidoField, sueldoField;
    private JLabel dniLabel;
    private JRadioButton vendedorRadio, directivoRadio;
    private JTextArea cargoArea, ventasArea;
    private ButtonGroup roleGroup;
    private JPanel directivoPanel, vendedorPanel;

    public VistaModificar() {
    	JPanel panel = new JPanel();
    	// Crear componentes
    	texto = new JLabel("Introduzca el DNI del empleado a modificar");
    	texto.setFont(new Font("Arial", Font.BOLD, 14));

    	dni = new JTextField();
    	dni.setFont(new Font("Arial", Font.PLAIN, 16));
    	dni.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40)); // Altura y expansión horizontal

    	aceptar = new JButton("Aceptar");
    	aceptar.addActionListener(e -> {
    		this.dispose();
    	    TransferEmpleado t = new TransferVendedor();
    	    t.setDNI(dni.getText());
    	    Controlador.getInstancia().accion(Eventos.MODIFICAR_EMPLEADO, t);
    	});

    	cancelar = new JButton("Cancelar");
    	cancelar.addActionListener(e -> this.dispose());

    	// Layout vertical principal
    	BoxLayout vertical = new BoxLayout(panel, BoxLayout.Y_AXIS);
    	panel.setLayout(vertical);
    	panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Margen

    	// Subpanel ptex con etiqueta encima del campo de texto
    	JPanel ptex = new JPanel();
    	ptex.setLayout(new BoxLayout(ptex, BoxLayout.Y_AXIS));
    	ptex.setAlignmentX(Component.LEFT_ALIGNMENT);

    	ptex.add(texto);
    	ptex.add(Box.createRigidArea(new Dimension(0, 10))); // Espacio entre etiqueta y campo
    	ptex.add(dni);

    	// Botones en línea
    	JPanel panelbot = new JPanel();
    	panelbot.setLayout(new BoxLayout(panelbot, BoxLayout.X_AXIS));
    	panelbot.add(Box.createHorizontalGlue()); // empujar botones a la derecha
    	panelbot.add(cancelar);
    	panelbot.add(Box.createRigidArea(new Dimension(10, 0)));
    	panelbot.add(aceptar);

    	// Añadir todo al panel principal
    	panel.add(ptex);
    	panel.add(Box.createRigidArea(new Dimension(0, 20))); // espacio entre campo y botones
    	panel.add(panelbot);

    	// Finalizar ventana
    	this.setContentPane(panel);
    	this.pack();
    	this.setLocationRelativeTo(null);
    	this.setVisible(true);
        
    }

    private void procesarModificar(TransferEmpleado emp) {
    	TransferEmpleado t = null;

        // Asignar los valores de los campos al TransferEmpleado
        String dni = dniLabel.getText();

        // Solo asignar valores si los campos no están vacíos
        String nombre = (nombreField.getText().isEmpty() ? emp.getNombre(): nombreField.getText());
        String apellido = (apellidoField.getText().isEmpty() ? emp.getApellido() : apellidoField.getText());

        int sueldo = -1;
        if (sueldoField.getText().isEmpty()) {
            sueldo = emp.getSueldo();  // Si sueldo está vacío, se establece como 0
        } else {
            try {
            	sueldo = Integer.parseInt(sueldoField.getText());  // Si el sueldo es válido, lo asigna
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "El sueldo debe ser un número", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        String tipo;
        // Asignar tipo de empleado según la selección
        if (vendedorRadio.isSelected() && emp.getPuesto().equals(Puesto.VENDEDOR)) {
            int numVentas = (ventasArea.getText().isEmpty() ? emp.getVentas() : Integer.parseInt(ventasArea.getText()));
            t = new TransferVendedor(emp.getId(), dni, nombre, apellido, emp.estaActivo(), numVentas, sueldo);
        } 
        else if(vendedorRadio.isSelected()&& emp.getPuesto().equals(Puesto.DIRECTIVO)) {
        	if(this.ventasArea.getText().trim().isEmpty()) {
        		JOptionPane.showMessageDialog(this, "Si cambias el tipo de empleado deberas meter los campos necesarios");
        		cargoArea.requestFocus();
        	    return;
        	}
        	else {
        		int ventas=-1;
        		try {
                	ventas = Integer.parseInt(ventasArea.getText());  // Si el sueldo es válido, lo asigna
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "El numero de ventas debe ser un número", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
        		t=new TransferVendedor(emp.getId(), dni, nombre, apellido, emp.estaActivo(), ventas, sueldo);
        	}
        }
        else if (directivoRadio.isSelected() && emp.getPuesto().equals(Puesto.DIRECTIVO)) {
            String cargo = (cargoArea.getText().isEmpty() ? emp.getCargo() : cargoArea.getText());
            t = new TransferDirectivo(emp.getId(), dni, nombre, apellido, emp.estaActivo(), cargo, sueldo);

        }
        else if(directivoRadio.isSelected() && emp.getPuesto().equals(Puesto.VENDEDOR)) {
        	if(this.cargoArea.getText().trim().isEmpty()) {
        		JOptionPane.showMessageDialog(this, "Si cambias el tipo de empleado deberas meter los campos necesarios");
        		cargoArea.requestFocus();
        	    return;
        	}
        	else t = new TransferDirectivo(emp.getId(), dni, nombre, apellido, emp.estaActivo(), this.cargoArea.getText(), sueldo);
        }
        else {
            tipo = null;
            t = new TransferDirectivo(emp.getId(), dni, nombre, apellido, emp.estaActivo(), null, sueldo); // Directivo con cargo null para identificarlo de los demas
        }

        // Llamar al controlador para modificar el empleado
        Controlador.getInstancia().accion(Eventos.MODIFICAR_EMPLEADO_FINAL, t);
        this.dispose();
    }

	@Override
	public void actualizar(int evento, Object datos) {
		if(evento==Eventos.RES_MODIFICAR_EMPLEADO_OK) {
			TransferEmpleado t = (TransferEmpleado) datos;
			
			texto.setVisible(false);
			aceptar.setVisible(false);
			dni.setVisible(false);
			cancelar.setVisible(false);
			setSize(500, 500);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setLocationRelativeTo(null);

	        mainPanel = new JPanel(new BorderLayout(10, 10));
	        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

	        // === Panel de campos básicos ===
	        datosPanel = new JPanel(new GridLayout(4, 2, 5, 5));

	        datosPanel.add(new JLabel("Nombre: "));
	        nombreField = new JTextField(t.getNombre());
	        datosPanel.add(nombreField);

	        datosPanel.add(new JLabel("Apellido: "));
	        apellidoField = new JTextField(t.getApellido());
	        datosPanel.add(apellidoField);

	        datosPanel.add(new JLabel("DNI (no modificable): "));
	        dniLabel = new JLabel(t.getDNI());
	        datosPanel.add(dniLabel);

	        datosPanel.add(new JLabel("Sueldo: "));
	        sueldoField = new JTextField(""+t.getSueldo());
	        datosPanel.add(sueldoField);

	        // === Panel de selección de tipo de empleado ===
	        puestoPanel = new JPanel(new GridLayout(1, 2, 5, 5));
	        puestoPanel.setBorder(BorderFactory.createTitledBorder("Tipo de empleado. Si cambias el tipo tendras que introducir los atributos especificos para que la modificacion tenga exito"));

	        vendedorRadio = new JRadioButton("Vendedor");
	        directivoRadio = new JRadioButton("Directivo");

	        roleGroup = new ButtonGroup();
	        roleGroup.add(vendedorRadio);
	        roleGroup.add(directivoRadio);
	        
	        puestoPanel.add(vendedorRadio);
	        puestoPanel.add(directivoRadio);

	        // === Panel de detalles dinámico (se llena al pulsar un radio) ===
	        detallesPanel = new JPanel(new CardLayout());

	        // Panel para directivo
	        directivoPanel = new JPanel(new BorderLayout());
	        directivoPanel.add(new JLabel("Cargo:"), BorderLayout.NORTH);
	        cargoArea = new JTextArea(3, 20);
	        cargoArea.setText(t.getCargo());
	        directivoPanel.add(new JScrollPane(cargoArea), BorderLayout.CENTER);

	        // Panel para vendedor
	        vendedorPanel = new JPanel(new BorderLayout());
	        vendedorPanel.add(new JLabel("Número de ventas:"), BorderLayout.NORTH);
	        ventasArea = new JTextArea(3, 20);
	        ventasArea.setText(""+t.getVentas());
	        vendedorPanel.add(new JScrollPane(ventasArea), BorderLayout.CENTER);
	        

	        // === Listeners para mostrar panel correcto al seleccionar ===
	        vendedorRadio.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                detallesPanel.removeAll();
	                detallesPanel.add(vendedorPanel);
	                detallesPanel.revalidate();
	                detallesPanel.repaint();
	            }
	        });

	        directivoRadio.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                detallesPanel.removeAll();
	                detallesPanel.add(directivoPanel);
	                detallesPanel.revalidate();
	                detallesPanel.repaint();
	            }
	        });
	        if(t.getPuesto().equals(Puesto.VENDEDOR)) {
	        	vendedorRadio.setSelected(true);
	        	vendedorRadio.doClick();
	        }
	        else if(t.getPuesto().equals(Puesto.DIRECTIVO)) {
	        	directivoRadio.setSelected(true);
	        	directivoRadio.doClick();

	        }


	        // === Botones inferiores ===
	        JButton enviarButton = new JButton("Modificar");
	        enviarButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                procesarModificar(t);
	                
	            }
	        });

	        JButton cancelarButton = new JButton("Cancelar");
	        cancelarButton.addActionListener(e -> dispose());

	        botonesAbajo = new JPanel();
	        botonesAbajo.add(enviarButton);
	        botonesAbajo.add(cancelarButton);

	        // === Estructura intermedia para mantener orden ===
	        JPanel centerPanel = new JPanel(new BorderLayout(10, 10));
	        centerPanel.add(puestoPanel, BorderLayout.NORTH);
	        centerPanel.add(detallesPanel, BorderLayout.CENTER);

	        mainPanel.add(datosPanel, BorderLayout.NORTH);
	        mainPanel.add(centerPanel, BorderLayout.CENTER);
	        mainPanel.add(botonesAbajo, BorderLayout.SOUTH);

	        this.add(mainPanel);
		}
		else if(evento==Eventos.RES_MODIFICAR_EMPLEADO_KO) {
			JOptionPane.showMessageDialog(this, "No se ha podido encontrar el empleado con dni: "+ (String) datos);
		}
		else if (evento == Eventos.RES_MODIFICAR_EMPLEADO_FINAL_OK) {
			JOptionPane.showMessageDialog(this, "Empleado modificado correctamente. ID: " + (int) datos);			
		}
		else if (evento == Eventos.RES_MODIFICAR_EMPLEADO_FINAL_KO) {
			JOptionPane.showMessageDialog(this, "No se ha podido modificar el empleado");
		}
	}
}
