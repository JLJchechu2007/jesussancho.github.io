package negocio.Empleado;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import integracion.Empleado.DAOEmpleado;
import integracion.Empleado.DAOEmpleadoImp;
import integracion.Factoria.FactoriaAbstractaIntegracion;

public class SAEmpleadoImp implements SAEmpleado{
	
	private DAOEmpleado dao;
	
	public SAEmpleadoImp(){
		dao = FactoriaAbstractaIntegracion.getInstance().crearDAOEmpleado();
;
	}

	@Override
	public int altaEmpleado(TransferEmpleado t) {
		return dao.insertar(t);
	}

	@Override
	public int bajaEmpleado(String DNI) {
		return dao.eliminar(DNI);
	}

	@Override
	public List<TransferEmpleado> mostrarEmpleados() {
		return dao.listar();
	}

	@Override
	public List<TransferEmpleado> mostrarVendedores() {
		return dao.listarVendedores();
	}

	@Override
	public List<TransferEmpleado> mostrarDirectivos() {
		return dao.listarDirectivos();
	}

	@Override
	public TransferEmpleado buscarEmpleado(String DNI) {
		return dao.buscar(DNI);
	}

	@Override
	public List<TransferEmpleado> ordenarEmpleados(Comparator<TransferEmpleado> comparador) {
		List<TransferEmpleado> empleados=dao.listar();
		empleados.sort(comparador);
		return empleados;
	}

	@Override
	public int modificar(TransferEmpleado t) {
		/*TransferEmpleado nuevo;
		if(t.estaActivo()) {
			if(t.getPuesto().equals(Puesto.VENDEDOR)) {
				String dni=t.getDNI();
				TransferEmpleado antiguo=dao.buscar(dni);
				if(!t.getPuesto().equals(antiguo.getPuesto()) && t.getVentas()==-1) return -1;
				String nombre=(t.getNombre() == null) ? antiguo.getNombre() : t.getNombre();
				String apellidos=(t.getApellido()==null)? antiguo.getApellido():t.getApellido();
				Integer ventas = t.getVentas();
				int ventas2 = (ventas != -1) ? ventas : antiguo.getVentas();
				int sueldo = (t.getSueldo()==-1)? antiguo.getSueldo() :t.getSueldo();
					
				nuevo = new TransferVendedor(antiguo.getId(),dni,nombre,apellidos,true,ventas2,sueldo);
				
			}
			else {
				String dni=t.getDNI();
				TransferEmpleado antiguo=dao.buscar(dni);
				if(!t.getPuesto().equals(antiguo.getPuesto()) && t.getCargo()==null) return -1;
				String nombre=(t.getNombre() == null) ? antiguo.getNombre() : t.getNombre();
				String apellidos=(t.getApellido()==null)? antiguo.getApellido():t.getApellido();
				String cargo=(t.getCargo()==null)? antiguo.getCargo():t.getCargo();
				int sueldo = (t.getSueldo()==-1)? antiguo.getSueldo() :t.getSueldo();
					
				nuevo = new TransferDirectivo(antiguo.getId(),dni,nombre,apellidos,true,cargo,sueldo);
				
			}
		}
		else {
			TransferEmpleado antiguo=dao.buscar(t.getDNI());
			if(antiguo.getPuesto().equals(Puesto.VENDEDOR)) {
				String dni=t.getDNI();
				String nombre=(t.getNombre() == null) ? antiguo.getNombre() : t.getNombre();
				String apellidos=(t.getApellido()==null)? antiguo.getApellido():t.getApellido();
				Integer ventas = t.getVentas();
				int ventas2 = (ventas != -1) ? ventas : antiguo.getVentas();
				int sueldo = (t.getSueldo()==-1)? antiguo.getSueldo() :t.getSueldo();
					
				nuevo = new TransferVendedor(antiguo.getId(),dni,nombre,apellidos,true,ventas2,sueldo);
				
			}
			else {
				String dni=t.getDNI();
				String nombre=(t.getNombre() == null) ? antiguo.getNombre() : t.getNombre();
				String apellidos=(t.getApellido()==null)? antiguo.getApellido():t.getApellido();
				String cargo=(t.getCargo()==null)? antiguo.getCargo():t.getCargo();
				int sueldo = (t.getSueldo()==-1)? antiguo.getSueldo() :t.getSueldo();
					
				nuevo = new TransferDirectivo(antiguo.getId(),dni,nombre,apellidos,true,cargo,sueldo);
				
			}
		}*/
		if(t.getPuesto().equals(Puesto.DIRECTIVO)&&t.getCargo()==null) {
			TransferEmpleado antiguo=dao.buscar(t.getDNI());
			if(antiguo.getPuesto().equals(Puesto.VENDEDOR)) t=new TransferVendedor(t.getId(),t.getDNI(),t.getNombre(),t.getApellido(),t.estaActivo(),antiguo.getVentas(),t.getSueldo());
			else if(antiguo.getPuesto().equals(Puesto.DIRECTIVO)) t.setCargo(antiguo.getCargo());
		}
		return dao.modificar(t);
	}

}
