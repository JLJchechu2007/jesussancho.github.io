package presentacion_;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import presentacion.Controlador.ControladorImp;
import presentacion.Controlador.Eventos;
import presentacion.Factoria.FactoriaAbstractaPresentacion;

@SuppressWarnings("serial")
public class FacturasIntermedio extends JFrame {
private static final String IMG = "resources/Icons/";
	
	public FacturasIntermedio(MainWindow mainWindow) {
		this.setTitle("Heladeria - Facturas");
		this.setSize(500, 150);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		this.initGui();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		
		this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                if (mainWindow != null) {
                    mainWindow.setVisible(true); // Show main window again
                }
            }
        });
	}
	
	private void initGui() {
		JPanel jP = new JPanel(new GridLayout(1, 5, 0, 0));
		
		JButton altaButton = new JButton();
		this.AddButton(jP, altaButton, e -> { 
		    ControladorImp.getInstancia().accion(Eventos.ABRIR_VENTA, null);
		}, IMG + "icon_abrir_venta.png");

		JButton bajaButton = new JButton();
		this.AddButton(jP, bajaButton, e -> { FactoriaAbstractaPresentacion.getInstance().createVista(Eventos.AÑADIR_PRODUCTO); }, IMG + "icon_añadir_producto.png");
		
		JButton buscarButton = new JButton();
		this.AddButton(jP, buscarButton, e -> { FactoriaAbstractaPresentacion.getInstance().createVista(Eventos.CERRAR_VENTA); }, IMG + "icon_cerrar_venta.png");	
		
		JButton listarButton = new JButton();
		this.AddButton(jP, listarButton, e -> { FactoriaAbstractaPresentacion.getInstance().createVista(Eventos.BUSCAR_FACTURA); }, IMG + "icon_buscar_factura.png");
		
		JButton modificarButton = new JButton();
		this.AddButton(jP, modificarButton, e -> { FactoriaAbstractaPresentacion.getInstance().createVista(Eventos.MOSTRAR_FACTURAS); }, IMG + "icon_mostrar_facturas.png");
		
		JButton modificarPorClienteButton = new JButton();
		this.AddButton(jP, modificarPorClienteButton, e -> { FactoriaAbstractaPresentacion.getInstance().createVista(Eventos.MOSTRAR_FACTURAS_POR_CLIENTE); }, IMG + "icon_mostrar_facturas_por_cliente.png");
		
		this.add(jP, BorderLayout.CENTER);

	}

	/*private void AddButton(JPanel jp, JButton jb, ActionListener l, String path) {
	jb.addActionListener(l);
	ImageIcon icon = new ImageIcon(path);
	Image img = icon.getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH);
	jb.setIcon(new ImageIcon(img));
	jb.setSize(75, 75);
	jb.setBackground(Color.WHITE);
	jb.setOpaque(true);
	jb.setBorderPainted(false);

	// AÑADIDO PARA POSICIÓN DEL TEXTO
	jb.setHorizontalTextPosition(SwingConstants.CENTER);
	jb.setVerticalTextPosition(SwingConstants.BOTTOM);

	jp.add(jb);
}*/

	private void AddButton(JPanel jp, JButton jb, ActionListener l, String path) {
		jb.addActionListener(l);

		ImageIcon icon = new ImageIcon(path);
		Image img = icon.getImage().getScaledInstance(60, 100, Image.SCALE_SMOOTH);
		jb.setIcon(new ImageIcon(img));


		jb.setBackground(Color.WHITE);
		jb.setOpaque(true);
		jb.setBorderPainted(false);

		jb.setContentAreaFilled(false);

		jp.add(jb);
	}


}
