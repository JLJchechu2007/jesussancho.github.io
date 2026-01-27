package integracion.Facturas;

import java.util.List;

import negocio.Facturas.TLineaFactura;

public interface DAOLineaFactura {
	int insertar(TLineaFactura linea);
	TLineaFactura buscar(int id);
	List<TLineaFactura> buscarPorFactura(int id);
    List<TLineaFactura> listar();
}
