package integracion.Facturas;

import java.util.List;

import negocio.Facturas.TFacturas;
import negocio.Producto.TProducto;

public interface DAOFacturas { //no hay método de eliminar factura porque no se admite devolución
    int insertar(TFacturas factura);
    TFacturas buscar(int id);
    List<TFacturas> buscarPorCliente(int idCliente);
    List<TFacturas> listar();
	public int actualizar(TFacturas factura);
}

