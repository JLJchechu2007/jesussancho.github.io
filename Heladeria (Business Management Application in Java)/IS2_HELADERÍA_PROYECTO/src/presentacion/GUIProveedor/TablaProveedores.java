package presentacion.GUIProveedor;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import negocio.Proveedor.TProveedor;

public class TablaProveedores extends AbstractTableModel{
	ArrayList<TProveedor> c; 
	String[] headers = {"idProveedores", "nombre", "telefono", "correo"}; 
	public TablaProveedores(Object datos) {
		c= (ArrayList<TProveedor>) datos; 
		
	}

	@Override
	public int getRowCount() {
		return c.size();
	}

	@Override
	public int getColumnCount() {
		return 4;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if(columnIndex == 0) {
			return c.get(rowIndex).getId(); 
		}else if(columnIndex ==1){
			return c.get(rowIndex).getNombre(); 
		}else if(columnIndex ==2) {
			return c.get(rowIndex).getTelefono(); 
		}else {
			return c.get(rowIndex).getCorreo(); 
		}
	}
	public String getColumnName(int col) {
		return headers[col];
	}


}
