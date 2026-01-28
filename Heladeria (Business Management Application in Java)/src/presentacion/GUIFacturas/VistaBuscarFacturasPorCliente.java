package presentacion.GUIFacturas;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import negocio.Facturas.TFacturas;
import presentacion.Controlador.Controlador;
import presentacion.Controlador.Eventos;
import presentacion_.IGUI;

public class VistaBuscarFacturasPorCliente  extends JFrame implements IGUI {
    private Controlador ctrl;
    private JTextField idClienteField;

    //-------CONSTRUCTOR-------//

    public VistaBuscarFacturasPorCliente() {
        this.setTitle("BUSCAR FACTURA POr CLIENTE");
        this.setSize(350, 150);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.ctrl = Controlador.getInstancia();

        this.initGUI();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    private void initGUI() {
    	JPanel jp = new JPanel(new GridLayout(2, 2, 10, 10));
        jp.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        jp.add(new JLabel("Id Cliente:"));
        this.idClienteField = new JTextField();
        jp.add(this.idClienteField);

        JButton buscar = new JButton("Buscar");
        buscar.addActionListener(e -> {
            try {
                int idCliente = Integer.parseInt(idClienteField.getText());
                ctrl.accion(Eventos.MOSTRAR_FACTURAS_POR_CLIENTE, idCliente);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Error: formato numérico incorrecto", "Error", JOptionPane.ERROR_MESSAGE);
            }
            this.dispose();
        });

        JButton cancelar = new JButton("Cancelar");
        cancelar.addActionListener(e -> { this.dispose(); });

        jp.add(cancelar);
        jp.add(buscar);

        this.add(jp);
    }

    //-------ACTUALIZAR GUI-------//

    @SuppressWarnings("unchecked")
    @Override
    public void actualizar(int evento, Object datos) {
        this.dispose();
        if (evento == Eventos.RES_MOSTRAR_FACTURAS_POR_CLIENTE_OK) {
            this.mostrarFacturasPorCliente((List<TFacturas>) datos);
        } else {
            JOptionPane.showMessageDialog(null, "No se han podido mostrar las facturas.");
        }
    }

    //-------PRIVATE-------//

    private void mostrarFacturasPorCliente(List<TFacturas> lista) {
        JFrame ventanaListado = new JFrame("LISTA DE FACTURAS DEL CLIENTE");
        ventanaListado.setSize(1200, 350);
        ventanaListado.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel listaPanel = new JPanel();
        listaPanel.setLayout(new javax.swing.BoxLayout(listaPanel, javax.swing.BoxLayout.Y_AXIS));
        listaPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        JTable tablafacturas= new JTable(new FacturaTableModel(lista));
		JScrollPane scrollPane = new JScrollPane(tablafacturas);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        JPanel buttonPanel = new JPanel();
        JButton ok = new JButton("OK");
        ok.setPreferredSize(new Dimension(100, 30));
        ok.addActionListener(e -> ventanaListado.dispose());
        buttonPanel.add(ok);

        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        ventanaListado.add(mainPanel);
        ventanaListado.setVisible(true);
        ventanaListado.setLocationRelativeTo(null);
    }
}
