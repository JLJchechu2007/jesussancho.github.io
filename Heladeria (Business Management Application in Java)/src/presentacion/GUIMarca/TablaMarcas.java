package presentacion.GUIMarca;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import negocio.Marca.TMarca;

public class TablaMarcas extends AbstractTableModel{
	
	private List<TMarca> listamarcas;
	private String[] nombrescol= {"idMarca", "nombreMarca", "nombrejefeMarca", "lugarsedeMarca"};
	
	public TablaMarcas(List<TMarca> _listamarcas) {
		listamarcas=_listamarcas;
	}

	@Override
	public int getRowCount() {
		return listamarcas.size();
	}

	@Override
	public int getColumnCount() {
		return nombrescol.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Object o=null;
		switch(columnIndex) {
		case 0: 
			o=listamarcas.get(rowIndex).getId();
			break;
		case 1:
			o=listamarcas.get(rowIndex).getNombre();
			break;
		case 2: 
			o=listamarcas.get(rowIndex).getNombreJefe();
			break;
		case 3:
			o= listamarcas.get(rowIndex).getLugarSede();
			break;
		}
		return o;
	}
	
	@Override
	public String getColumnName(int col) {
		return nombrescol[col];
	}

}
