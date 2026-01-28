package presentacion.GUIFacturas;

import javax.swing.table.AbstractTableModel;
import java.util.List;
import negocio.Facturas.TFacturas;

public class FacturaTableModel extends AbstractTableModel {

    private final String[] columnas = { "idFactura", "idCliente", "idVendedor", "fecha", "importe", "activo", "dirección" };
    private List<TFacturas> facturas;

    public FacturaTableModel(List<TFacturas> facturas) {
        this.facturas = facturas;
    }

    @Override
    public int getRowCount() {
        return facturas == null ? 0 : facturas.size();
    }

    @Override
    public int getColumnCount() {
        return columnas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        TFacturas f = facturas.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> f.getIdFactura();        // IDFactura
            case 1 -> f.getIdCliente();        // Cliente
            case 2 -> f.getIdVendedor();       // Vendedor
            case 3 -> f.getFecha();            // Fecha
            case 4 -> f.getImporte();          // Importe
            case 5 -> f.isActivo() ? "Sí" : "No"; // Activo
            case 6 -> f.getDireccion();       // Dirección
            default -> null;
        };
    }

    @Override
    public String getColumnName(int column) {
        return columnas[column];
    }
}
