package presentacion.GUIProducto;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import negocio.Producto.*;
import presentacion.Controlador.Controlador;
import presentacion.Controlador.Eventos;
import presentacion_.IGUI;

public class VistaBuscarProducto extends JFrame implements IGUI {
private JLabel lidProd;
private JTextField tidProd;
private JButton aceptar;
private JButton cancelar;

public VistaBuscarProducto(){
	setTitle("BUSCAR PRODUCTO");
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
		setVisible(false);
		int idP = Integer.parseInt(tidProd.getText());
		TProducto producto= new TProducto();
		producto.setId(idP);
		Controlador.getInstancia().accion(Eventos.BUSCAR_PRODUCTO,producto);
	});
	cancelar.addActionListener(e->{this.dispose();});
}
public void actualizar(int evento, Object datos){
	 if (evento == Eventos.RES_BUSCAR_PRODUCTO_OK) {
		 this.dispose();
		 TProducto res= (TProducto)datos;
		 JOptionPane.showMessageDialog(this, 
				    "Tipo de producto: " + res.getTipoProducto() + "\n" +
				    "Cantidad: " + res.getCantidad() + "\n" +
				    "Precio del producto: " + res.getPrecioProducto() + "\n" +
				    "Marca del producto: " + res.getIdMarca());
		 }
		else if(evento == Eventos.RES_BUSCAR_PRODUCTO_KO) {
		this.dispose();
		 JOptionPane.showMessageDialog(this, "No se ha podido encontrar el producto indicado");
		 }
}

}