package presentacion.GUIFacturas;

import javax.swing.table.AbstractTableModel;
import java.util.List;
import negocio.Facturas.TLineaFactura;

public class LineaFacturaTableModel extends AbstractTableModel {

    private final String[] columnas = { "ID Línea", "ID Factura", "ID Producto", "Cantidad", "Precio" };
    private List<TLineaFactura> lineas;

    public LineaFacturaTableModel(List<TLineaFactura> lineas) {
        this.lineas = lineas;
    }

    @Override
    public int getRowCount() {
        return lineas == null ? 0 : lineas.size();
    }

    @Override
    public int getColumnCount() {
        return columnas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        TLineaFactura l = lineas.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> l.getIdLinea();
            case 1 -> l.getIdFactura();
            case 2 -> l.getIdProducto();
            case 3 -> l.getCantidad();
            case 4 -> l.getPrecio();
            default -> null;
        };
    }

    @Override
    public String getColumnName(int column) {
        return columnas[column];
    }
}
