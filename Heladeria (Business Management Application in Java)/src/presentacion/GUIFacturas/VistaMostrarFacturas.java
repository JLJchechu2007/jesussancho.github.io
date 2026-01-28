package presentacion.GUIFacturas;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import negocio.Facturas.TFacturas;
import presentacion.Controlador.Controlador;
import presentacion.Controlador.Eventos;
import presentacion_.IGUI;

public class VistaMostrarFacturas extends JFrame implements IGUI {

    private Controlador ctrl;

    //-------CONSTRUCTOR-------//

    public VistaMostrarFacturas() {
        this.setTitle("MOSTRAR FACTURAS");
        this.setSize(350, 150);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.ctrl = Controlador.getInstancia();

        this.initGUI();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    private void initGUI() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel label = new JLabel("¿Desea mostrar la lista de facturas?");
        mainPanel.add(label, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        JButton cancelar = new JButton("No");
        cancelar.setPreferredSize(new Dimension(100, 40));
        cancelar.addActionListener(e -> this.dispose());

        JButton aceptar = new JButton("Sí");
        aceptar.setPreferredSize(new Dimension(100, 40));
        aceptar.addActionListener(e -> {
            try {
                ctrl.accion(Eventos.MOSTRAR_FACTURAS, null);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error al intentar mostrar las facturas.");
            }
            this.dispose();
        });

        buttonPanel.add(cancelar);
        buttonPanel.add(aceptar);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        this.add(mainPanel);
    }

    //-------ACTUALIZAR GUI-------//

    @SuppressWarnings("unchecked")
    @Override
    public void actualizar(int evento, Object datos) {
        this.dispose();
        if (evento == Eventos.RES_MOSTRAR_FACTURAS_OK) {
            this.mostrarFacturas((List<TFacturas>) datos);
        } else {
            JOptionPane.showMessageDialog(null, "No se han podido mostrar las facturas.");
        }
    }

    //-------PRIVATE-------//

    private void mostrarFacturas(List<TFacturas> lista) {
        JFrame ventanaListado = new JFrame("LISTA DE FACTURAS");
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
