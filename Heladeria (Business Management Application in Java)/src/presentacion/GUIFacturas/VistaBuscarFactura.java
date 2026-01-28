package presentacion.GUIFacturas;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import presentacion.Controlador.ControladorImp;
import presentacion.Controlador.Eventos;
import presentacion_.IGUI;

@SuppressWarnings("serial")
public class VistaBuscarFactura extends JFrame implements IGUI {

    private JTextField idFacturaField;
    private ControladorImp ctrl;

    //-------CONSTRUCTORES-------//

    public VistaBuscarFactura() {
        this.setTitle("BUSCAR FACTURA");
        this.setSize(300, 150);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.ctrl = (ControladorImp) ControladorImp.getInstancia();

        this.initGui();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    private void initGui() {
        JPanel jp = new JPanel(new GridLayout(2, 2, 10, 10));
        jp.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        jp.add(new JLabel("Id Factura:"));
        this.idFacturaField = new JTextField();
        jp.add(this.idFacturaField);

        JButton buscar = new JButton("Buscar");
        buscar.addActionListener(e -> {
            try {
                int idFactura = Integer.parseInt(idFacturaField.getText());
                ctrl.accion(Eventos.BUSCAR_FACTURA, idFactura);
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

    @Override
    public void actualizar(int evento, Object datos) {
        this.dispose();
        if (evento == Eventos.RES_BUSCAR_FACTURA_OK) {
            mostrarFactura(datos);
        } else {
            JOptionPane.showMessageDialog(null, "Factura no encontrada", "Resultado", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void mostrarFactura(Object factura) {
        // Aquí puedes personalizar cómo mostrar la información
        JOptionPane.showMessageDialog(null, factura.toString(), "Factura encontrada", JOptionPane.INFORMATION_MESSAGE);
    }
}