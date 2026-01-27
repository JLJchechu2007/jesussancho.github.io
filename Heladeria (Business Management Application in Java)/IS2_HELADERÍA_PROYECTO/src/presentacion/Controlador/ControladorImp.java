package presentacion.Controlador;

import negocio.Cliente.SACliente;
import negocio.Cliente.TransferCliente;
import negocio.Empleado.ComparadoresEmpleado;
import negocio.Empleado.TransferEmpleado;
import negocio.Factoria.FactoriaAbstractaNegocio;
import negocio.Facturas.SAFacturas;
import negocio.Facturas.SAFacturasImp;
import negocio.Facturas.TFacturas;
import negocio.Facturas.TLineaFactura;
import negocio.Marca.SAMarca;
import negocio.Marca.TMarca;
import negocio.Producto.SAProducto;
import negocio.Producto.TProducto;
import negocio.Proveedor.SAProveedor;
import negocio.Proveedor.TMarcaProveedor;
import negocio.Proveedor.TProveedor;
import presentacion.Factoria.FactoriaAbstractaPresentacion;
import presentacion.GUICliente.VistaModificarClienteFinal;
import presentacion.GUIMarca.VistaModificarAtributosMarca;
import presentacion.GUIProducto.VistaModificarProducto;
import presentacion.GUIProveedor.VistaModificarProveedor;

import javax.swing.JOptionPane;

import java.util.Collection;
import java.util.List;

public class ControladorImp extends Controlador {
    
    @Override
    public void accion(int evento, Object datos) {
        switch (evento) {
        
        	case Eventos.MAIN_WINDOW:
        		//IGUI vistaPrincipal=FactoriaPresentacion.getInstance().nuevaVista(evento, datos);
                //if(vistaPrincipal!=null) vistaPrincipal.mostrar();
                break;
                
            //-----------------------------------------------------------------------//    
        	case Eventos.ABRIR_VENTA: {
        	    SAFacturas saFacturas = FactoriaAbstractaNegocio.getInstance().crearSAFactura();
        	    int res = saFacturas.abrirVenta();

        	    if (res == 0) {
        	        JOptionPane.showMessageDialog(null, "Venta abierta correctamente", "Información", JOptionPane.INFORMATION_MESSAGE);
        	    } else {
        	        JOptionPane.showMessageDialog(null, "Error al abrir la venta", "Error", JOptionPane.ERROR_MESSAGE);
        	    }
        	    break;
        	}


                
        	case Eventos.AÑADIR_PRODUCTO: {
        	    TLineaFactura linea = (TLineaFactura) datos;
        	    SAFacturas saFacturas = FactoriaAbstractaNegocio.getInstance().crearSAFactura();

        	    boolean exito = saFacturas.añadirProducto(linea);

        	    if (exito)
        	        FactoriaAbstractaPresentacion.getInstance()
        	            .createVista(Eventos.AÑADIR_PRODUCTO)
        	            .actualizar(Eventos.RES_AÑADIR_PRODUCTO_OK, null);
        	    else {
        	        String error = ((SAFacturasImp) saFacturas).getMensajeError();
        	        JOptionPane.showMessageDialog(null, error, "Error al añadir producto", JOptionPane.ERROR_MESSAGE);
        	        FactoriaAbstractaPresentacion.getInstance()
        	            .createVista(Eventos.AÑADIR_PRODUCTO)
        	            .actualizar(Eventos.RES_AÑADIR_PRODUCTO_KO, null);
        	    }
        	    break;
        	}

        	case Eventos.CERRAR_VENTA: {
        	    TFacturas factura = (TFacturas) datos;
        	    SAFacturas saFactura = FactoriaAbstractaNegocio.getInstance().crearSAFactura();
        	    int idFactura = saFactura.cerrarVenta(factura);

        	    if (idFactura > 0) {
        	        JOptionPane.showMessageDialog(null,
        	            "Venta cerrada correctamente. ID de la factura: " + idFactura,
        	            "Éxito",
        	            JOptionPane.INFORMATION_MESSAGE);
        	    } else {
        	        String error = saFactura.getMensajeError();
        	        JOptionPane.showMessageDialog(null, error, "Error al cerrar venta", JOptionPane.ERROR_MESSAGE);
        	    }
        	    break;
        	}


            case Eventos.BUSCAR_FACTURA: {
                int idFactura = (Integer) datos;  // Cambiar a Integer ya que estamos pasando solo el ID
                SAFacturas saFactura = FactoriaAbstractaNegocio.getInstance().crearSAFactura();
                TFacturas resultado = saFactura.buscarFactura(idFactura);  // Buscar la factura con el ID

                if (resultado != null) {
                    FactoriaAbstractaPresentacion.getInstance().createVista(evento).actualizar(Eventos.RES_BUSCAR_FACTURA_OK, resultado);
                } else {
                    FactoriaAbstractaPresentacion.getInstance().createVista(evento).actualizar(Eventos.RES_BUSCAR_FACTURA_KO, null);
                }
                break;
            }



            case Eventos.MOSTRAR_FACTURAS: {
                SAFacturas saFacturas = FactoriaAbstractaNegocio.getInstance().crearSAFactura();
                List<TFacturas> todas = saFacturas.mostrarfacturas();
                if(todas.size() > 0) FactoriaAbstractaPresentacion.getInstance().createVista(Eventos.MOSTRAR_FACTURAS).actualizar(Eventos.RES_MOSTRAR_FACTURAS_OK, todas);
                else FactoriaAbstractaPresentacion.getInstance().createVista(Eventos.MOSTRAR_FACTURAS).actualizar(Eventos.RES_MOSTRAR_FACTURAS_KO, null);
                break;
            }
            
            case Eventos.MOSTRAR_FACTURAS_POR_CLIENTE: {
            	int idCliente = (Integer) datos;
            	SAFacturas saFacturas = FactoriaAbstractaNegocio.getInstance().crearSAFactura();
                List<TFacturas> facturas = saFacturas.buscarFacturasPorCliente(idCliente);
                if(facturas.size() > 0) FactoriaAbstractaPresentacion.getInstance().createVista(Eventos.MOSTRAR_FACTURAS_POR_CLIENTE).actualizar(Eventos.RES_MOSTRAR_FACTURAS_POR_CLIENTE_OK, facturas);
                else FactoriaAbstractaPresentacion.getInstance().createVista(Eventos.MOSTRAR_FACTURAS_POR_CLIENTE).actualizar(Eventos.RES_MOSTRAR_FACTURAS_POR_CLIENTE_KO, null);
                break;
            }
            //-----------------------------------------------------------------------//   
            case Eventos.ALTA_EMPLEADO:
                TransferEmpleado nuevoEmpleado = (TransferEmpleado) datos;
                int idGenerado = FactoriaAbstractaNegocio.getInstance().crearSAEmpleado().altaEmpleado(nuevoEmpleado);
                if (idGenerado != -1) {
                    FactoriaAbstractaPresentacion.getInstance().createVista(evento).actualizar(Eventos.RES_ALTA_EMPLEADO_OK, idGenerado);
                } else {
                	FactoriaAbstractaPresentacion.getInstance().createVista(evento).actualizar(Eventos.RES_ALTA_EMPLEADO_KO, idGenerado);
                }
                break;
            	
            case Eventos.BAJA_EMPLEADO:
            	TransferEmpleado t = (TransferEmpleado) datos;
                int idBaja = FactoriaAbstractaNegocio.getInstance().crearSAEmpleado().bajaEmpleado(t.getDNI());
                if (idBaja != -1) {
                    FactoriaAbstractaPresentacion.getInstance().createVista(evento).actualizar(Eventos.RES_BAJA_EMPLEADO_OK, idBaja);
                } else {
                    FactoriaAbstractaPresentacion.getInstance().createVista(evento).actualizar(Eventos.RES_BAJA_EMPLEADO_KO, idBaja);
                }
                break;
                
            case Eventos.MODIFICAR_EMPLEADO:
            	TransferEmpleado empleadoModificado = (TransferEmpleado) datos;
            	String dni=empleadoModificado.getDNI();
            	TransferEmpleado empleadoAntiguo=FactoriaAbstractaNegocio.getInstance().crearSAEmpleado().buscarEmpleado(dni);
            	if(empleadoAntiguo!=null) {
            		FactoriaAbstractaPresentacion.getInstance().createVista(evento).actualizar(Eventos.RES_MODIFICAR_EMPLEADO_OK, empleadoAntiguo);
            	}
            	else {
            		FactoriaAbstractaPresentacion.getInstance().createVista(evento).actualizar(Eventos.RES_MODIFICAR_EMPLEADO_KO, dni);
            	}
            	break;
                
                
            case Eventos.MODIFICAR_EMPLEADO_FINAL:
            	TransferEmpleado empleadoModificado1 = (TransferEmpleado) datos;
            	int idModificado = FactoriaAbstractaNegocio.getInstance().crearSAEmpleado().modificar(empleadoModificado1);
                if (idModificado != -1) {
                    FactoriaAbstractaPresentacion.getInstance().createVista(Eventos.MODIFICAR_EMPLEADO).actualizar(Eventos.RES_MODIFICAR_EMPLEADO_FINAL_OK, idModificado);
                } else {
                	FactoriaAbstractaPresentacion.getInstance().createVista(Eventos.MODIFICAR_EMPLEADO).actualizar(Eventos.RES_MODIFICAR_EMPLEADO_FINAL_KO, idModificado);                }
                break;
    	
            case Eventos.MOSTRAR_EMPLEADO:
            	// Llamamos al modelo para obtener datos
                List<TransferEmpleado> empleados = FactoriaAbstractaNegocio.getInstance().crearSAEmpleado().mostrarEmpleados();
                List<TransferEmpleado> vendedores = FactoriaAbstractaNegocio.getInstance().crearSAEmpleado().mostrarVendedores();
                List<TransferEmpleado> directivos = FactoriaAbstractaNegocio.getInstance().crearSAEmpleado().mostrarDirectivos();
                List<TransferEmpleado> sueldo=FactoriaAbstractaNegocio.getInstance().crearSAEmpleado().ordenarEmpleados(ComparadoresEmpleado.POR_SUELDO.get());
                List<TransferEmpleado> nombre=FactoriaAbstractaNegocio.getInstance().crearSAEmpleado().ordenarEmpleados(ComparadoresEmpleado.POR_APELLIDO_NOMBRE.get());
                List<TransferEmpleado> dni1=FactoriaAbstractaNegocio.getInstance().crearSAEmpleado().ordenarEmpleados(ComparadoresEmpleado.POR_DNI.get());


                // Preparamos el paquete de datos para la Vista
                Object[] datosVista = {empleados, vendedores, directivos,sueldo,nombre,dni1};

                if(datosVista!=null) {
                	FactoriaAbstractaPresentacion.getInstance().createVista(evento).actualizar(Eventos.RES_MOSTRAR_EMPLEADOS_OK, datosVista);
                }
                else {
                	FactoriaAbstractaPresentacion.getInstance().createVista(evento).actualizar(Eventos.RES_MOSTRAR_EMPLEADOS_KO, datosVista);
                }
            	break;
            
            case Eventos.BUSCAR_EMPLEADO:
            	TransferEmpleado dniBuscar = (TransferEmpleado) datos;
                TransferEmpleado empleadoEncontrado = FactoriaAbstractaNegocio.getInstance().crearSAEmpleado().buscarEmpleado(dniBuscar.getDNI());
                if (empleadoEncontrado != null) {
                    FactoriaAbstractaPresentacion.getInstance().createVista(evento).actualizar(Eventos.RES_BUSCAR_EMPLEADO_OK, empleadoEncontrado);
                } else {
                    FactoriaAbstractaPresentacion.getInstance().createVista(evento).actualizar(Eventos.RES_BUSCAR_EMPLEADO_KO, empleadoEncontrado);
                }
                break;           
            
            case Eventos.NUEVO_PRODUCTO:{
            	TProducto producto= (TProducto) datos;
            	SAProducto saProd= FactoriaAbstractaNegocio.getInstance().crearSAProducto();
                int resultado= saProd.nuevoProducto(producto);
                if(resultado!=-1)
                FactoriaAbstractaPresentacion.getInstance().createVista(evento).actualizar(Eventos.RES_NUEVO_PRODUCTO_OK,resultado); 
                else FactoriaAbstractaPresentacion.getInstance().createVista(evento).actualizar(Eventos.RES_NUEVO_PRODUCTO_KO,resultado); 
                break;
            }
            	
            case Eventos.MODIFICAR_PRODUCTO:{
            	TProducto producto= (TProducto) datos;
            	SAProducto saProd= FactoriaAbstractaNegocio.getInstance().crearSAProducto();
                TProducto resultado= saProd.read(producto.getId());
                if(resultado!=null) {
                	VistaModificarProducto vmp= (VistaModificarProducto)FactoriaAbstractaPresentacion.getInstance().createVista(Eventos.MODIFICAR_PRODUCTO_FINAL);
                	vmp.setDatos(resultado);
                }
                else FactoriaAbstractaPresentacion.getInstance().createVista(evento).actualizar(Eventos.RES_MODIFICAR_PRODUCTO_KO,datos); 
                break;
            }
            case Eventos.MODIFICAR_PRODUCTO_FINAL:{
            	TProducto producto= (TProducto) datos;
            	SAProducto saProd= FactoriaAbstractaNegocio.getInstance().crearSAProducto();
                int resultado= saProd.update(producto);
                if(resultado!=-1)
                FactoriaAbstractaPresentacion.getInstance().createVista(evento).actualizar(Eventos.RES_MODIFICAR_PRODUCTO_FINAL_OK,resultado); 
                else FactoriaAbstractaPresentacion.getInstance().createVista(evento).actualizar(Eventos.RES_MODIFICAR_PRODUCTO_FINAL_KO,resultado);
                break;
            }
            	
            case Eventos.BUSCAR_PRODUCTO:{
            	TProducto producto= (TProducto) datos;
            	SAProducto saProd= FactoriaAbstractaNegocio.getInstance().crearSAProducto();
                TProducto resultado= saProd.read(producto.getId());
                if(resultado!=null)
                FactoriaAbstractaPresentacion.getInstance().createVista(evento).actualizar(Eventos.RES_BUSCAR_PRODUCTO_OK,resultado); 
                else FactoriaAbstractaPresentacion.getInstance().createVista(evento).actualizar(Eventos.RES_BUSCAR_PRODUCTO_KO,resultado); 
                break;
            }
            	
            case Eventos.MOSTRAR_PRODUCTOS:{
            	 SAProducto saProd= FactoriaAbstractaNegocio.getInstance().crearSAProducto();
            	 Collection<TProducto>resultado= saProd.readall();
                 if(resultado!=null)
                 FactoriaAbstractaPresentacion.getInstance().createVista(evento).actualizar(Eventos.RES_MOSTRAR_PRODUCTOS_OK,resultado); 
                 else FactoriaAbstractaPresentacion.getInstance().createVista(evento).actualizar(Eventos.RES_MOSTRAR_PRODUCTOS_KO,resultado); 
            	 break;
            }
            case Eventos.MOSTRAR_PRODUCTOS_POR_MARCA:{
            	TProducto producto= (TProducto) datos;
           	    SAProducto saProd= FactoriaAbstractaNegocio.getInstance().crearSAProducto();
           	    Collection<TProducto>resultado= saProd.readallbymarca(producto.getIdMarca());
                if(resultado!=null)
                FactoriaAbstractaPresentacion.getInstance().createVista(evento).actualizar(Eventos.RES_MOSTRAR_PRODUCTOS_POR_MARCA_OK,resultado); 
                else FactoriaAbstractaPresentacion.getInstance().createVista(evento).actualizar(Eventos.RES_MOSTRAR_PRODUCTOS_POR_MARCA_KO,resultado); 
           	    break;
           }
            	
            case Eventos.ELIMINAR_PRODUCTO:{
            	TProducto producto= (TProducto) datos;
            	SAProducto saProd= FactoriaAbstractaNegocio.getInstance().crearSAProducto();
                int resultado= saProd.delete(producto.getId());
                if(resultado!=-1)
                FactoriaAbstractaPresentacion.getInstance().createVista(evento).actualizar(Eventos.RES_ELIMINAR_PRODUCTO_OK,resultado); 
                else FactoriaAbstractaPresentacion.getInstance().createVista(evento).actualizar(Eventos.RES_ELIMINAR_PRODUCTO_KO,resultado); 
            	break;
            }
            //-----------------------------------------------------------------------//
            case Eventos.ALTA_CLIENTE: {
                TransferCliente tCliente = (TransferCliente)datos;
                SACliente saCliente = FactoriaAbstractaNegocio.getInstance().crearSACliente();

                int clienteId = saCliente.altaCliente(tCliente);
                if (clienteId > 0) FactoriaAbstractaPresentacion.getInstance().createVista(Eventos.ALTA_CLIENTE).actualizar(Eventos.EXITO_ALTA_CLIENTE, clienteId);
                else FactoriaAbstractaPresentacion.getInstance().createVista(Eventos.ALTA_CLIENTE).actualizar(Eventos.SIN_EXITO_ALTA_CLIENTE, null);
                break;
	        }
	        case Eventos.BAJA_CLIENTE: {
	                TransferCliente tCliente = (TransferCliente)datos;
	                SACliente saCliente = FactoriaAbstractaNegocio.getInstance().crearSACliente();
	                if (saCliente.bajaCliente(tCliente)) FactoriaAbstractaPresentacion.getInstance().createVista(Eventos.BAJA_CLIENTE).actualizar(Eventos.EXITO_BAJA_CLIENTE, tCliente);
	                else FactoriaAbstractaPresentacion.getInstance().createVista(Eventos.BAJA_CLIENTE).actualizar(Eventos.SIN_EXITO_BAJA_CLIENTE, null);
	                break;
	        }
	        case Eventos.BUSCAR_CLIENTE: {
                TransferCliente tCliente = (TransferCliente)datos;
                SACliente saCliente = FactoriaAbstractaNegocio.getInstance().crearSACliente();
                TransferCliente tC = saCliente.buscarCliente(tCliente);

                if (tC != null) {
                FactoriaAbstractaPresentacion.getInstance().createVista(Eventos.BUSCAR_CLIENTE).actualizar(Eventos.EXITO_BUSCAR_CLIENTE, tC);;
                }
                else FactoriaAbstractaPresentacion.getInstance().createVista(Eventos.BUSCAR_CLIENTE).actualizar(Eventos.SIN_EXITO_BUSCAR_CLIENTE, null);
                break;
        }

	        case Eventos.LISTAR_CLIENTE: {
                SACliente saCliente = FactoriaAbstractaNegocio.getInstance().crearSACliente();
                List<TransferCliente> clientes = saCliente.listarClientes();
                if(clientes.size() > 0) FactoriaAbstractaPresentacion.getInstance().createVista(Eventos.LISTAR_CLIENTE).actualizar(Eventos.EXITO_LISTAR_CLIENTE, clientes);
                else FactoriaAbstractaPresentacion.getInstance().createVista(Eventos.LISTAR_CLIENTE).actualizar(Eventos.SIN_EXITO_LISTAR_CLIENTE, null);
                break;
	        }
	        case Eventos.MODIFICAR_CLIENTE_INITIAL: {
	        	TransferCliente tCliente = (TransferCliente)datos;
	        	SACliente saCliente = FactoriaAbstractaNegocio.getInstance().crearSACliente();
	        	
	        	TransferCliente result = saCliente.buscarCliente(tCliente);
	        	if (result != null) {
	        		VistaModificarClienteFinal vis = (VistaModificarClienteFinal) FactoriaAbstractaPresentacion.getInstance().createVista(Eventos.MODIFICAR_CLIENTE_FINAL);
	        		vis.setText(result);
	        	}
	        	else FactoriaAbstractaPresentacion.getInstance().createVista(evento).actualizar(Eventos.SIN_EXITO_MODIFICAR_CLIENTE, null);
	        	break;
	        }
	        case Eventos.MODIFICAR_CLIENTE_FINAL: {
	        	TransferCliente tCliente = (TransferCliente)datos;
	        	SACliente saCliente = FactoriaAbstractaNegocio.getInstance().crearSACliente();
	        	
	        	if(saCliente.modificarClienteFinal(tCliente)) FactoriaAbstractaPresentacion.getInstance().createVista(evento).actualizar(Eventos.EXITO_MODIFICAR_CLIENTE, tCliente);
	        	else FactoriaAbstractaPresentacion.getInstance().createVista(evento).actualizar(Eventos.SIN_EXITO_MODIFICAR_CLIENTE, null);
	        	break;
	        }
			//-----------------------------------------------------------------------//
			case Eventos.NUEVO_PROVEEDOR:{
				TProveedor tProveedor= (TProveedor) datos; 
				SAProveedor saPro =FactoriaAbstractaNegocio.getInstance().crearSAProveedor();
				int res = saPro.create(tProveedor); 
				if(res != -1) FactoriaAbstractaPresentacion.getInstance().createVista(evento).actualizar(Eventos.RES_NUEVO_PROVEEDOR_OK,res);
				else FactoriaAbstractaPresentacion.getInstance().createVista(evento).actualizar(Eventos.RES_NUEVO_PROVEEDOR_KO,res); 
				break; 
			}
			case Eventos.BUSCAR_PROVEEDOR:{
				TProveedor tProveedor= (TProveedor) datos; 
				SAProveedor saPro =FactoriaAbstractaNegocio.getInstance().crearSAProveedor();
				TProveedor res = saPro.read(tProveedor.getId()); 
				if(res != null) FactoriaAbstractaPresentacion.getInstance().createVista(evento).actualizar(Eventos.RES_BUSCAR_PROVEEDOR_OK,res);
				else FactoriaAbstractaPresentacion.getInstance().createVista(evento).actualizar(Eventos.RES_BUSCAR_PROVEEDOR_KO,res); 
				break; 
			}
			case Eventos.ELIMINAR_PROVEEDOR:{
				TProveedor tProveedor= (TProveedor) datos; 
				SAProveedor saPro =FactoriaAbstractaNegocio.getInstance().crearSAProveedor();
				int res = saPro.delete(tProveedor.getId()); 
				if(res != -1) FactoriaAbstractaPresentacion.getInstance().createVista(evento).actualizar(Eventos.RES_ELIMINAR_PROVEEDOR_OK,res);
				else FactoriaAbstractaPresentacion.getInstance().createVista(evento).actualizar(Eventos.RES_ELIMINAR_PROVEEDOR_KO,res); 
				break; 
			}
			case Eventos.MODIFICAR_PROVEEDOR1:{
				TProveedor tProveedor= (TProveedor) datos; 
				SAProveedor saPro =FactoriaAbstractaNegocio.getInstance().crearSAProveedor();
				TProveedor res = saPro.read(tProveedor.getId()); 
				if(res!= null) {
					VistaModificarProveedor vmp = (VistaModificarProveedor) FactoriaAbstractaPresentacion.getInstance().createVista(Eventos.MODIFICAR_PROVEEDOR); 
					vmp.setDatos(res); 
				}
				else FactoriaAbstractaPresentacion.getInstance().createVista(evento).actualizar(Eventos.RES_MODIFICAR_PROVEEDOR1_KO, datos);
				break; 
			}
			case Eventos.MODIFICAR_PROVEEDOR:{
				TProveedor tProveedor= (TProveedor) datos; 
				SAProveedor saPro =FactoriaAbstractaNegocio.getInstance().crearSAProveedor();
				int res = saPro.update(tProveedor); 
				if(res != -1) FactoriaAbstractaPresentacion.getInstance().createVista(evento).actualizar(Eventos.RES_MODIFICAR_PROVEEDOR_OK,res);
				else FactoriaAbstractaPresentacion.getInstance().createVista(evento).actualizar(Eventos.RES_MODIFICAR_PROVEEDOR_KO,res); 
				break; 
			}
			case Eventos.MOSTRAR_PROVEEDOR:{
				SAProveedor saPro =FactoriaAbstractaNegocio.getInstance().crearSAProveedor();
				Collection<TProveedor> res= saPro.readAll();  
				if(res != null) FactoriaAbstractaPresentacion.getInstance().createVista(evento).actualizar(Eventos.RES_MOSTRAR_PROVEEDOR_OK,res);
				else FactoriaAbstractaPresentacion.getInstance().createVista(evento).actualizar(Eventos.RES_MOSTRAR_PROVEEDOR_KO,res); 
				break; 
			}
			case Eventos.VINCULACIONES:{
				TMarcaProveedor tMarcaProveedor= (TMarcaProveedor) datos; 
				SAProveedor saPro =FactoriaAbstractaNegocio.getInstance().crearSAProveedor();
				boolean res = saPro.vinculaciones(tMarcaProveedor); 
				if(res) FactoriaAbstractaPresentacion.getInstance().createVista(evento).actualizar(Eventos.RES_VINCULACIONES_PROVEEDOR_OK,datos);
				else FactoriaAbstractaPresentacion.getInstance().createVista(evento).actualizar(Eventos.RES_VINCULACIONES_PROVEEDOR_KO,datos);
				break; 
			}
			//-----------------------------------------------------------------------//
			
			case Eventos.AÑADIR_MARCA:{
				TMarca tmarca= (TMarca) datos;
				SAMarca samarca= FactoriaAbstractaNegocio.getInstance().crearSAMarca();
				int res= samarca.create(tmarca);
				if(res==-1) FactoriaAbstractaPresentacion.getInstance().createVista(evento).actualizar(Eventos.RES_AÑADIR_MARCA_KO,res); 
				else FactoriaAbstractaPresentacion.getInstance().createVista(evento).actualizar(Eventos.RES_AÑADIR_MARCA_OK,res);
				break;
			}
			
			case Eventos.ELIMINAR_MARCA:{
				TMarca tmarca= (TMarca) datos;
				SAMarca samarca= FactoriaAbstractaNegocio.getInstance().crearSAMarca();
				int res= samarca.delete(tmarca.getId());
				if(res==-1) FactoriaAbstractaPresentacion.getInstance().createVista(evento).actualizar(Eventos.RES_ELIMINAR_MARCA_KO,res); 
				else FactoriaAbstractaPresentacion.getInstance().createVista(evento).actualizar(Eventos.RES_ELIMINAR_MARCA_OK,res); 
				break;
			}
			
			case Eventos.BUSCAR_MARCA:{
				TMarca tmarca= (TMarca) datos;
				SAMarca samarca= FactoriaAbstractaNegocio.getInstance().crearSAMarca();
				TMarca res= samarca.read(tmarca.getId());
				if(res==null) FactoriaAbstractaPresentacion.getInstance().createVista(evento).actualizar(Eventos.RES_BUSCAR_MARCA_KO,res); 
				else FactoriaAbstractaPresentacion.getInstance().createVista(evento).actualizar(Eventos.RES_BUSCAR_MARCA_OK,res);
				break;
			}
			
			case Eventos.MODIFICAR_MARCA:{
				TMarca tmarca= (TMarca) datos;
				SAMarca samarca= FactoriaAbstractaNegocio.getInstance().crearSAMarca();
				TMarca res= samarca.read(tmarca.getId());
				if(res==null) FactoriaAbstractaPresentacion.getInstance().createVista(evento).actualizar(Eventos.RES_MODIFICAR_MARCA_KO,datos); 
				else {
					VistaModificarAtributosMarca vista= (VistaModificarAtributosMarca)FactoriaAbstractaPresentacion.getInstance().createVista(Eventos.MODIFICAR_ATRIBUTOS_MARCA);
                	vista.setDatos(res);
				}
				break;
			}
			
			case Eventos.MODIFICAR_ATRIBUTOS_MARCA:{
				TMarca tmarca= (TMarca) datos;
				SAMarca samarca= FactoriaAbstractaNegocio.getInstance().crearSAMarca();
				int res= samarca.update(tmarca);
				if(res==-1) FactoriaAbstractaPresentacion.getInstance().createVista(evento).actualizar(Eventos.RES_MODIFICAR_ATRIBUTOS_MARCA_KO,res); 
				else FactoriaAbstractaPresentacion.getInstance().createVista(evento).actualizar(Eventos.RES_MODIFICAR_ATRIBUTOS_MARCA_OK,res); 
				break;
			}
			
			case Eventos.MOSTRAR_MARCAS:{
				SAMarca samarca= FactoriaAbstractaNegocio.getInstance().crearSAMarca();
				Collection<TMarca> res= samarca.readAll();
				if(res==null) FactoriaAbstractaPresentacion.getInstance().createVista(evento).actualizar(Eventos.RES_MOSTRAR_MARCAS_KO,res); 
				else FactoriaAbstractaPresentacion.getInstance().createVista(evento).actualizar(Eventos.RES_MOSTRAR_MARCAS_OK,res); 
				break;
			}
			
            default:
               JOptionPane.showMessageDialog(null, "Evento no reconocido");
                break;
        }
    }
}
