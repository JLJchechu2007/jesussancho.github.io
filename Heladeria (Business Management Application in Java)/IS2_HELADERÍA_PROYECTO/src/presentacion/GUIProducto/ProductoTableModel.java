package presentacion.GUIProducto;

import java.util.ArrayList;
import java.util.Collection;

import javax.swing.table.AbstractTableModel;

import negocio.Producto.TProducto;

public class ProductoTableModel extends AbstractTableModel {

	private ArrayList<TProducto> _prods;
	private String[] _colNames = { "IdProducto", "TipoDeProducto","CantidadProducto","PrecioProducto","IdMarca"};

	
	public ProductoTableModel(ArrayList<TProducto> datos) {
		_prods= datos;
	}
	
	@Override
	public int getRowCount() {
		return _prods.size();
	}

	@Override
	public int getColumnCount() {
		return _colNames.length;
	}
	
	@Override
	public String getColumnName(int col) {
		return _colNames[col];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Object s = null;
		switch (columnIndex) {
		case 0:
			s = _prods.get(rowIndex).getId();
			break;
		case 1:
			s = _prods.get(rowIndex).getTipoProducto();
			break;
		case 2:
			s = _prods.get(rowIndex).getCantidad();
			break;
		case 3:
			s= _prods.get(rowIndex).getPrecioProducto();
			break;
		case 4:
			s=_prods.get(rowIndex).getIdMarca();
			break;
		}
		return s;
	}

}
