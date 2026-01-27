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

public class VistaNuevoProducto extends JFrame implements IGUI {
private JLabel ltipoProd;
private JTextField ttipoProd;
private JLabel lprecio;
private JTextField tprecio;
private JLabel lcantidad;
private JTextField tcantidad;
private JLabel lmarca;
private JTextField tmarca;
private JButton aceptar;
private JButton cancelar;

public VistaNuevoProducto(){
	setTitle("AÑADIR PRODUCTO NUEVO");
	 crearComponentes();
	 configurarComponentes();
	 crearOyentes();
	 this.pack();
	 this.setLocationRelativeTo(null);
	 this.setVisible(true);
}
private void crearComponentes() {
	ltipoProd= new JLabel("Tipo de producto:");
	ttipoProd= new JTextField(20);
	lprecio= new JLabel("Precio por unidad:");
	tprecio= new JTextField(20);
	lcantidad= new JLabel("Cantidad:");
	tcantidad= new JTextField(20);
	lmarca= new JLabel("Marca:");
	tmarca= new JTextField(20);
    aceptar=new JButton("Aceptar");
    cancelar= new JButton("Cancelar");
}
private void configurarComponentes() {
	JPanel panel= new JPanel();
	BoxLayout vertical= new BoxLayout(panel,BoxLayout.Y_AXIS);
	panel.setLayout(vertical);
	JPanel tp= new JPanel();
	BoxLayout h1= new BoxLayout(tp,BoxLayout.X_AXIS);
	tp.setLayout(h1);
	tp.add(ltipoProd);
	tp.add(ttipoProd);
	JPanel ca= new JPanel();
	BoxLayout h2= new BoxLayout(ca,BoxLayout.X_AXIS);
	ca.setLayout(h2);
	ca.add(lcantidad);
	ca.add(tcantidad);
	JPanel pr= new JPanel();
	BoxLayout h3= new BoxLayout(pr,BoxLayout.X_AXIS);
	pr.setLayout(h3);
	pr.add(lprecio);
	pr.add(tprecio);
	JPanel ma= new JPanel();
	BoxLayout h4= new BoxLayout(ma,BoxLayout.X_AXIS);
	ma.setLayout(h4);
	ma.add(lmarca);
	ma.add(tmarca);
	JPanel panelbot= new JPanel();
	BoxLayout horizontal = new BoxLayout(panelbot,BoxLayout.X_AXIS);
	panelbot.setLayout(horizontal);
	panelbot.add(cancelar);
	panelbot.add(aceptar);
	panel.add(tp);
	panel.add(ca);
	panel.add(pr);
	panel.add(ma);
	panel.add(panelbot);
	this.setContentPane(panel);
}
private void crearOyentes(){
	aceptar.addActionListener(e->{
		this.dispose();
		this.setVisible(false);
		String tipoP = ttipoProd.getText();
		int cantidad = Integer.parseInt(tcantidad.getText());
		float precio = Float.parseFloat(tprecio.getText());
		int idMarca = Integer.parseInt(tcantidad.getText());
		TProducto producto= new TProducto();
		producto.setTipoProducto(tipoP);
		producto.setCantidad(cantidad);
		producto.setPrecioProducto(precio);
		producto.setIdMarca(idMarca);
		Controlador.getInstancia().accion(Eventos.NUEVO_PRODUCTO,producto);
	});
	cancelar.addActionListener(e->{this.dispose();});
}
public void actualizar(int evento, Object datos){
	   if (evento == Eventos.RES_NUEVO_PRODUCTO_OK) {
		   this.dispose();
		 JOptionPane.showMessageDialog(this, "Se ha añadido el producto con id "+(Integer)datos);
		 }
		else if(evento == Eventos.RES_NUEVO_PRODUCTO_KO) {
			this.dispose();
		 JOptionPane.showMessageDialog(this, "No se ha podido añadir el producto indicado");
		 }
}
}
