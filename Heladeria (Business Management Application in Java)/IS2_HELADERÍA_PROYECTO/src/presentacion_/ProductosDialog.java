package presentacion_;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import presentacion.Controlador.Eventos;
import presentacion.Factoria.FactoriaAbstractaPresentacion;

public class ProductosDialog extends JDialog implements ActionListener {
	private JButton NuevoProd;
	private JButton BuscarProd;
	private JButton ModificarProd;
	private JButton MostrarProd;
	private JButton EliminarProd;
	private JButton ProdporM;
	
	public ProductosDialog() {
		setTitle("Gestion de Productos");
		crearComponentes();
		configurarComponentes();
		crearOyentes();
	}
	private void crearComponentes(){
		NuevoProd= new JButton();
		botones("resources/Icons/Nuevo_producto.png",NuevoProd);
		BuscarProd=new JButton();
		botones("resources/Icons/Buscar_Producto.png",BuscarProd);
		ModificarProd=new JButton();
		botones("resources/Icons/Modificar_Producto.png",ModificarProd);
		MostrarProd=new JButton();
		botones("resources/Icons/Mostrar_Productos.png",MostrarProd);
		EliminarProd= new JButton();
		botones("resources/Icons/Eliminar_Producto.png",EliminarProd);
		ProdporM= new JButton();
		botones("resources/Icons/Mostrar_pormarcas.png",ProdporM);
	}
	private void configurarComponentes(){
		JPanel panel= new JPanel();
		BoxLayout horizontal= new BoxLayout(panel,BoxLayout.X_AXIS);
		panel.setLayout(horizontal);
		panel.add(NuevoProd);
		panel.add(BuscarProd);
		panel.add(ModificarProd);
		panel.add(MostrarProd);
		panel.add(EliminarProd);
		panel.add(ProdporM);
		this.setContentPane(panel);
	}
	private void crearOyentes(){
		NuevoProd.addActionListener(e->{
			this.setVisible(false);
			FactoriaAbstractaPresentacion.getInstance().createVista(Eventos.NUEVO_PRODUCTO);});
		BuscarProd.addActionListener(e->{
			this.setVisible(false);
			FactoriaAbstractaPresentacion.getInstance().createVista(Eventos.BUSCAR_PRODUCTO);});
		ModificarProd.addActionListener(e->{
			this.setVisible(false);
			FactoriaAbstractaPresentacion.getInstance().createVista(Eventos.MODIFICAR_PRODUCTO);});
		MostrarProd.addActionListener(e->{
			this.setVisible(false);
			FactoriaAbstractaPresentacion.getInstance().createVista(Eventos.MOSTRAR_PRODUCTOS);});
		EliminarProd.addActionListener(e->{ 
			this.setVisible(false);
			FactoriaAbstractaPresentacion.getInstance().createVista(Eventos.ELIMINAR_PRODUCTO);});
		ProdporM.addActionListener(e->{ 
			this.setVisible(false);
			FactoriaAbstractaPresentacion.getInstance().createVista(Eventos.MOSTRAR_PRODUCTOS_POR_MARCA);});
	}
	private void botones(String s, JButton b){
		ImageIcon icon = new ImageIcon(s);
		Image img = icon.getImage().getScaledInstance(70, 90, Image.SCALE_SMOOTH);
		b.setIcon(new ImageIcon(img));
		b.setBackground(Color.WHITE);
		b.setOpaque(true);
		b.setBorderPainted(false);
		b.setContentAreaFilled(false);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		this.setModal(true);
		this.pack();
        this.setLocationRelativeTo(null);
	    this.setVisible(true);
	}

}
