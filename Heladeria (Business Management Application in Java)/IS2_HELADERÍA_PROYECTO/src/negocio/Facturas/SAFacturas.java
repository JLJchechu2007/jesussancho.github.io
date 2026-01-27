package negocio.Facturas;

import java.util.List;

public interface SAFacturas {

	public int abrirVenta();
	public boolean añadirProducto(TLineaFactura linea);
    public int cerrarVenta(TFacturas f);
    public List<TFacturas> mostrarfacturas();
    public TFacturas buscarFactura(int id);
    public List<TFacturas> buscarFacturasPorCliente(int idCliente);
	public String getMensajeError();
}
