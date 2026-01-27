package presentacion.Factoria;

import java.util.List;


import negocio.Empleado.SAEmpleadoImp;
import negocio.Empleado.TransferEmpleado;
import presentacion.Controlador.Eventos;
import presentacion.GUICliente.*;
import presentacion.GUIFacturas.VistaAñadirProducto;
import presentacion.GUIFacturas.VistaBuscarFactura;
import presentacion.GUIFacturas.VistaBuscarFacturasPorCliente;
import presentacion.GUIFacturas.VistaCerrarVenta;
import presentacion.GUIFacturas.VistaMostrarFacturas;
import presentacion.GUIMarca.VistaAñadirMarca;
import presentacion.GUIMarca.VistaBuscarMarca;
import presentacion.GUIMarca.VistaEliminarMarca;
import presentacion.GUIMarca.VistaModificarAtributosMarca;
import presentacion.GUIMarca.VistaModificarMarca;
import presentacion.GUIMarca.VistaMostrarMarcas;
import presentacion.GUIProducto.VistaBuscarProducto;
import presentacion.GUIProducto.VistaEliminarProducto;
import presentacion.GUIProducto.VistaModificarProducto;
import presentacion.GUIProducto.VistaModificarProducto1;
import presentacion.GUIProducto.VistaMostrarProductos;
import presentacion.GUIProducto.VistaMostrarProductosporMarca;
import presentacion.GUIProducto.VistaNuevoProducto;
import presentacion.GUIProveedor.VistaAñadirProveedor;
import presentacion.GUIProveedor.VistaBuscarProveedor;
import presentacion.GUIProveedor.VistaEliminarProveedor;
import presentacion.GUIProveedor.VistaModificarProveedor;
import presentacion.GUIProveedor.*;
import presentacion_.IGUI;

public class FactoriaPresentacion extends FactoriaAbstractaPresentacion{
 
	@Override
	public IGUI createVista(int idEvento) {
			 switch (idEvento) {
			 //-------PRODUCTO-------//   
			 case Eventos.NUEVO_PRODUCTO: { return new VistaNuevoProducto();}
			 case Eventos.BUSCAR_PRODUCTO: { return new VistaBuscarProducto();}
			 case Eventos.ELIMINAR_PRODUCTO: { return new VistaEliminarProducto();}
			 case Eventos.MOSTRAR_PRODUCTOS:{ return new VistaMostrarProductos();}
			 case Eventos.MODIFICAR_PRODUCTO:{ return new VistaModificarProducto1();}
			 case Eventos.MODIFICAR_PRODUCTO_FINAL:{ return new VistaModificarProducto();}
			 case Eventos.MOSTRAR_PRODUCTOS_POR_MARCA:{ return new VistaMostrarProductosporMarca();}
			 //-------PROVEEDOR-------//   
			 case Eventos.NUEVO_PROVEEDOR:{ return new VistaAñadirProveedor();}
			 case Eventos.BUSCAR_PROVEEDOR:{ return new VistaBuscarProveedor();}
			 case Eventos.MODIFICAR_PROVEEDOR:{ return new VistaModificarProveedor(); }
			 case Eventos.MODIFICAR_PROVEEDOR1: {return new VistaModificarProveedor1();}
			 case Eventos.MOSTRAR_PROVEEDOR:{ return new VistaMostrarProveedor();}
			 case Eventos.ELIMINAR_PROVEEDOR:{ return new VistaEliminarProveedor();}
			 case Eventos.VINCULACIONES:{ return new VistaVinculaciones();}
			 //-------EMPLEADO-------//   
			 case Eventos.ALTA_EMPLEADO: { return new presentacion.GUIEmpleado.VistaAlta();}
		     case Eventos.BAJA_EMPLEADO: { return new presentacion.GUIEmpleado.BajaEmpleado();}
		     case Eventos.BUSCAR_EMPLEADO: { return new presentacion.GUIEmpleado.VistaBuscarEmpleado();}
		     case Eventos.MOSTRAR_EMPLEADO: { return new presentacion.GUIEmpleado.VistaMostrar();}
		     case Eventos.MODIFICAR_EMPLEADO: { return (IGUI) new presentacion.GUIEmpleado.VistaModificar();}

		     //case Eventos.VENTANA_PRINCIPAL_EMPLEADOS: { return (IGUI) new presentacion.GUIEmpleado.VistaPrincipal();}
		     //-------CLIENTE-------//
		     case Eventos.ALTA_CLIENTE: { return new VistaAltaCliente(); }
		     case Eventos.BAJA_CLIENTE: { return new VistaEliminarCliente(); }
		     case Eventos.BUSCAR_CLIENTE: { return new VistaBuscarCliente(); }
		     case Eventos.LISTAR_CLIENTE: { return new VistaListarCliente(); }
		     case Eventos.MODIFICAR_CLIENTE_INITIAL: { return new VistaModificarClienteInitial(); }
		     case Eventos.MODIFICAR_CLIENTE_FINAL: { return new VistaModificarClienteFinal(); }
		     //-------FACTURA-------//
		     //case Eventos.ABRIR_VENTA: no hace falta pq no tiene vista
		     case Eventos.CERRAR_VENTA: {return new VistaCerrarVenta();}
		     case Eventos.BUSCAR_FACTURA: {return new VistaBuscarFactura();}
		     case Eventos.MOSTRAR_FACTURAS: {return new VistaMostrarFacturas();}
		     case Eventos.AÑADIR_PRODUCTO: {return new VistaAñadirProducto();}
		     case Eventos.MOSTRAR_FACTURAS_POR_CLIENTE:{return new VistaBuscarFacturasPorCliente();}
		   //-------MARCA-------//
		     case Eventos.AÑADIR_MARCA: { return new VistaAñadirMarca();}
			 case Eventos.BUSCAR_MARCA: { return new VistaBuscarMarca();}
			 case Eventos.ELIMINAR_MARCA: { return new VistaEliminarMarca();}
			 case Eventos.MOSTRAR_MARCAS:{ return new VistaMostrarMarcas();}
			 case Eventos.MODIFICAR_MARCA:{ return new VistaModificarMarca();}
			 case Eventos.MODIFICAR_ATRIBUTOS_MARCA:{ return new VistaModificarAtributosMarca();}
		     default:{
				 return null;
			 }
		}
	}
}