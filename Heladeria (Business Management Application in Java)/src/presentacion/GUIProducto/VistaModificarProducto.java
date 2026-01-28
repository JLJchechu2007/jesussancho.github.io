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

public class VistaModificarProducto extends JFrame implements IGUI {
private JLabel lidProd;
private JTextField tidProd;
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

public VistaModificarProducto(){
	 setTitle("MODIFICAR PRODUCTO");
	 crearComponentes();
	 configurarComponentes();
	 crearOyentes();
	 this.pack();
	 this.setLocationRelativeTo(null);
}
public void setDatos(Object datos) {
	TProducto p = (TProducto)datos; 
	tidProd.setText(String.valueOf(p.getId()));
	ttipoProd.setText(p.getTipoProducto());
	tcantidad.setText(String.valueOf(p.getCantidad()));
	tprecio.setText(String.valueOf(p.getPrecioProducto()));
	tmarca.setText(String.valueOf(p.getIdMarca()));
	this.setVisible(true);
}
private void crearComponentes() {
	lidProd= new JLabel("Id del producto:");
	tidProd= new JTextField(20);
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
	JPanel idp= new JPanel();
	BoxLayout hor= new BoxLayout(idp,BoxLayout.X_AXIS);
	idp.setLayout(hor);
	idp.add(lidProd);
	idp.add(tidProd);
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
	panel.add(idp);
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
		setVisible(false);
		TProducto producto= new TProducto();
		int idP = Integer.parseInt(tidProd.getText());
		try {
		String tipoP = ttipoProd.getText();
		producto.setTipoProducto(tipoP);
		}
		catch(NullPointerException ex) {}
		try {
		int cantidad = Integer.parseInt(tcantidad.getText());
		producto.setCantidad(cantidad);
		}
		catch(NullPointerException ex) {}
		try {
		float precio = Float.parseFloat(tprecio.getText());
		producto.setPrecioProducto(precio);
		}
		catch(NullPointerException ex){}
		try {
		int idMarca = Integer.parseInt(tmarca.getText());
		producto.setIdMarca(idMarca);
		}
		catch(NullPointerException ex){}
		producto.setId(idP);
		Controlador.getInstancia().accion(Eventos.MODIFICAR_PRODUCTO_FINAL,producto);
	});
	cancelar.addActionListener(e->{this.dispose();});
	}
	public void actualizar(int evento, Object datos){
		if (evento == Eventos.RES_MODIFICAR_PRODUCTO_FINAL_OK) {
		this.dispose();
		JOptionPane.showMessageDialog(this, "Se ha modificado el producto con id "+(Integer)datos);
		}
		else if(evento == Eventos.RES_MODIFICAR_PRODUCTO_FINAL_KO) {
		this.dispose();
		JOptionPane.showMessageDialog(this, "No se ha podido modificar el producto indicado");
		}
	}
	
}

