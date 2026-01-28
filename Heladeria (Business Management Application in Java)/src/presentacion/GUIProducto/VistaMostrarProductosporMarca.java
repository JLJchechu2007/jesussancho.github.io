package presentacion.GUIProducto;

import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import negocio.Producto.TProducto;
import presentacion.Controlador.Controlador;
import presentacion.Controlador.Eventos;
import presentacion_.IGUI;

public class VistaMostrarProductosporMarca extends JFrame implements IGUI {
	JLabel lidm;
	JTextField tidm;
	JButton aceptar;
	JButton cancelar;
	
	public VistaMostrarProductosporMarca () {
		setTitle("MOSTRAR PRODUCTOS POR MARCA");
		JPanel panel= new JPanel();
		lidm= new JLabel("Id de Marca");
		tidm= new JTextField(20);
		aceptar=new JButton("Aceptar");
		cancelar=new JButton("Cancelar");
		BoxLayout vertical= new BoxLayout(panel,BoxLayout.Y_AXIS);
		panel.setLayout(vertical);
		JPanel ptex= new JPanel();
		BoxLayout h = new BoxLayout(ptex,BoxLayout.X_AXIS);
		ptex.setLayout(h);
		ptex.add(lidm);
		ptex.add(tidm);
		JPanel panelbot= new JPanel();
		BoxLayout horizontal = new BoxLayout(panelbot,BoxLayout.X_AXIS);
		panelbot.setLayout(horizontal);
		panelbot.add(cancelar);
		panelbot.add(aceptar);
		panel.add(ptex);
		panel.add(panelbot);
		crearOyentes();
		this.setContentPane(panel);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	private void crearOyentes() {
		aceptar.addActionListener(e->{
			this.dispose();
			int idM = Integer.parseInt(tidm.getText());
			TProducto producto= new TProducto();
			producto.setIdMarca(idM);
			Controlador.getInstancia().accion(Eventos.MOSTRAR_PRODUCTOS_POR_MARCA, producto);
		});
		cancelar.addActionListener(e->{this.dispose();});
	}
	public void actualizar(int evento,Object datos) {
		if (evento == Eventos.RES_MOSTRAR_PRODUCTOS_POR_MARCA_OK) {
			 this.dispose();
			 ArrayList<TProducto>res= (ArrayList<TProducto>)datos;
			 JTable tablaproductos= new JTable(new ProductoTableModel(res));
			 JScrollPane scroll = new JScrollPane(tablaproductos);
			 JOptionPane.showMessageDialog(null, scroll, "Productos listados:", JOptionPane.INFORMATION_MESSAGE);
	    }		 
		else if(evento == Eventos.RES_MOSTRAR_PRODUCTOS_POR_MARCA_KO) {
		JOptionPane.showMessageDialog(this, "No hay productos asociados a la marca");
		}
	}
}
