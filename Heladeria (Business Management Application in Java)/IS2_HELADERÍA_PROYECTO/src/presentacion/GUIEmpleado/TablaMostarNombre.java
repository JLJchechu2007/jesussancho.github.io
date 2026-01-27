package presentacion.GUIEmpleado;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import negocio.Empleado.TransferEmpleado;
import presentacion.Controlador.Controlador;

public class TablaMostarNombre extends AbstractTableModel{
	
	private List<String> columnNames;
	private List<TransferEmpleado> empleados;
	
	public TablaMostarNombre(List<TransferEmpleado> empleados) {
		columnNames = new ArrayList<String> ();
		this.empleados = empleados;
		columnNames.add("Apellidos");
		columnNames.add("Nombre");
		columnNames.add("DNI");
		columnNames.add("Sueldo");
		columnNames.add("Puesto");
	}

	@Override
	public int getRowCount() {
		return empleados.size();
	}

	@Override
	public int getColumnCount() {
		return 5;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		ArrayList<TransferEmpleado> empleadoslista = new ArrayList<TransferEmpleado> (empleados);
		TransferEmpleado empleado = empleadoslista.get(rowIndex);
		switch(columnIndex) {
		case 0: return empleado.getApellido();
		case 1: return empleado.getNombre();
		case 2: return empleado.getDNI();
		case 3: return empleado.getSueldo();
		case 4: return empleado.getPuesto();
		default: return null;
		}
	}
	
	//En los metodos de observer poner fireTableStructureChanged()
}
