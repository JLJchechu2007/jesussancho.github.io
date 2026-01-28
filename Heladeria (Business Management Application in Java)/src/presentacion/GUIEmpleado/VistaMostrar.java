package presentacion.GUIEmpleado;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.AbstractTableModel;

import negocio.Empleado.TransferEmpleado;
import presentacion.Controlador.Controlador;
import presentacion.Controlador.ControladorImp;
import presentacion.Controlador.Eventos;
import presentacion_.IGUI;

public class VistaMostrar extends JFrame implements IGUI {
	
	//Atributos principio
	JLabel texto;
	JButton aceptar;
	JButton cancelar;

    private JTable tabla;
    private JComboBox<String> tipoTabla;
    private AbstractTableModel directivos;
    private AbstractTableModel vendedores;
    private AbstractTableModel DNI;
    private AbstractTableModel nombre;
    private AbstractTableModel sueldo;
    private AbstractTableModel todo;

    public VistaMostrar() {
    	setTitle("MOSTRAR EMPLEADOS");
		JPanel panel= new JPanel();
		texto= new JLabel("¿Desea mostrar la lista de empleados?");
		aceptar=new JButton("Aceptar");
		aceptar.addActionListener(e->Controlador.getInstancia().accion(Eventos.MOSTRAR_EMPLEADO, null));
		cancelar=new JButton("Cancelar");
		cancelar.addActionListener(e->this.dispose());
		BoxLayout vertical= new BoxLayout(panel,BoxLayout.Y_AXIS);
		panel.setLayout(vertical);
		JPanel ptex= new JPanel();
		BoxLayout h = new BoxLayout(ptex,BoxLayout.X_AXIS);
		ptex.setLayout(h);
		ptex.add(texto);
		JPanel panelbot= new JPanel();
		BoxLayout horizontal = new BoxLayout(panelbot,BoxLayout.X_AXIS);
		panelbot.setLayout(horizontal);
		panelbot.add(cancelar);
		panelbot.add(aceptar);
		panel.add(ptex);
		panel.add(panelbot);
		this.setContentPane(panel);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
    }
    
    @Override
	public void actualizar(int evento, Object datos) {
		if(evento==Eventos.RES_MOSTRAR_EMPLEADOS_OK) {
			texto.setVisible(false);
			aceptar.setVisible(false);
			Object[] listas=(Object[]) datos;
			List<TransferEmpleado> empleados = (List<TransferEmpleado>) listas[0];
			List<TransferEmpleado> vendedores = (List<TransferEmpleado>) listas[1];
			List<TransferEmpleado> directivos = (List<TransferEmpleado>) listas[2];
			List<TransferEmpleado> sueldo = (List<TransferEmpleado>) listas[3];
			List<TransferEmpleado> nombre = (List<TransferEmpleado>) listas[4];
			List<TransferEmpleado> dni = (List<TransferEmpleado>) listas[5];
			this.directivos = new TablaMostarDirectivos(directivos);
	        this.vendedores = new TablaMostarVendedores(vendedores);
	        this.DNI = new TablaMostarDNI(empleados);
	        this.nombre = new TablaMostarNombre(nombre);
	        this.sueldo = new TablaMostarSueldo(sueldo);
	        this.todo = new TablaMostar1(dni);

	        // Tabla
	        tabla = new JTable(todo);
	        JScrollPane pane=new JScrollPane(tabla);
	        this.add(pane, BorderLayout.CENTER);

	        // Panel inferior con JComboBox y botón
	        JPanel panelInferior = new JPanel(new BorderLayout(5,5));

	        String[] opciones = {
	            "Ordenar por nombre", "Ordenar por DNI", "Ordenar por sueldo",
	            "Solo directivos", "Solo vendedores", "Tabla principal"
	        };
	        tipoTabla = new JComboBox<>(opciones);
	        tipoTabla.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                cambiarModelo();
	            }
	        });
	        panelInferior.add(tipoTabla, BorderLayout.CENTER);
	        this.add(panelInferior, BorderLayout.SOUTH);
	        
	        // Ajustar tamaño de la ventana a la tabla
	        this.pack();
	        this.setLocationRelativeTo(null);
	        this.setVisible(true);
		}
		else if(evento==Eventos.RES_MOSTRAR_EMPLEADOS_KO) {
			JOptionPane.showMessageDialog(this,"No se han podido mostrar los empeados");
		}
	}

    private void cambiarModelo() {
        String seleccion = (String) tipoTabla.getSelectedItem();
        switch (seleccion) {
            case "Ordenar por nombre":
                tabla.setModel(nombre);
                break;
            case "Ordenar por DNI":
                tabla.setModel(DNI);
                break;
            case "Ordenar por sueldo":
                tabla.setModel(sueldo);
                break;
            case "Solo directivos":
                tabla.setModel(directivos);
                break;
            case "Solo vendedores":
                tabla.setModel(vendedores);
                break;
            default:
                tabla.setModel(todo);
                break;
        }
     // Asegurarse de que la ventana se ajuste a la nueva tabla
        this.revalidate();
        this.repaint();
        this.pack();  // Asegura que la ventana se ajuste al tamaño de la tabla
    }
}