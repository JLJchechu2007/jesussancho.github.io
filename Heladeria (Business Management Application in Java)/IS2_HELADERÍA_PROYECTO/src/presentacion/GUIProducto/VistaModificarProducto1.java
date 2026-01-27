package presentacion.GUIProducto;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import negocio.Producto.TProducto;
import presentacion.Controlador.Controlador;
import presentacion.Controlador.Eventos;
import presentacion_.IGUI;

public class VistaModificarProducto1 extends JFrame implements IGUI {
	
	private JLabel lidProd;
	private JTextField tidProd;
	private JButton aceptar;
	private JButton cancelar;

	public VistaModificarProducto1(){
		setTitle("MODIFICAR PRODUCTO");
		crearComponentes();
	    configurarComponentes();
	    crearOyentes();
	    this.pack();
		this.setLocationRelativeTo(null);
	    this.setVisible(true);
	}
	private void crearComponentes() {
		lidProd= new JLabel("Id del producto:");
		tidProd= new JTextField(20);
	    aceptar=new JButton("Aceptar");
	    cancelar= new JButton("Cancelar");
	}
	private void configurarComponentes() {
		JPanel panel= new JPanel();
		BoxLayout vertical= new BoxLayout(panel,BoxLayout.Y_AXIS);
		panel.setLayout(vertical);
		JPanel idp= new JPanel();
		BoxLayout hor= new BoxLayout(idp,BoxLayout.X_AXIS);
		idp.setLayout(hor);
		idp.add(lidProd);
		idp.add(tidProd);
		JPanel panelbot= new JPanel();
		BoxLayout horizontal = new BoxLayout(panelbot,BoxLayout.X_AXIS);
		panelbot.setLayout(horizontal);
		panelbot.add(cancelar);
		panelbot.add(aceptar);
		panel.add(idp);
		panel.add(panelbot);
		this.setContentPane(panel);
	}
	private void crearOyentes(){
		aceptar.addActionListener(e->{
			this.dispose();
			setVisible(false);
			int idP = Integer.parseInt(tidProd.getText());
			TProducto producto= new TProducto();
			producto.setId(idP);
			Controlador.getInstancia().accion(Eventos.MODIFICAR_PRODUCTO,producto);
		});
		cancelar.addActionListener(e->{this.dispose();});

}
	@Override
	public void actualizar(int evento, Object datos) {
		 if(evento == Eventos.RES_MODIFICAR_PRODUCTO_KO) {
			 this.dispose();
			 JOptionPane.showMessageDialog(this, "No se ha podido encontrar el producto indicado");
		 }
	}
	}
