package presentacion.GUIFacturas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import negocio.Facturas.TFacturas;
import presentacion.Controlador.ControladorImp;
import presentacion.Controlador.Eventos;
import presentacion_.IGUI;

public class VistaCerrarVenta extends JFrame implements IGUI {

    private JTextField idVendedorField;
    private JTextField idClienteField;
    private JTextField dirField;

    public VistaCerrarVenta() {
        super("Cerrar Factura");
        initGUI();
    }

    private void initGUI() {
        this.setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        formPanel.add(new JLabel("ID Cliente:"));
        idClienteField = new JTextField();
        formPanel.add(idClienteField);

        formPanel.add(new JLabel("ID Vendedor:"));
        idVendedorField = new JTextField();
        formPanel.add(idVendedorField);
        
        formPanel.add(new JLabel("Dirección Factura:"));
        dirField = new JTextField();
        formPanel.add(dirField);
        
        JButton cerrarButton = new JButton("Cerrar Venta");
        cerrarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int idCliente = Integer.parseInt(idClienteField.getText());
                    String idVendedor = idVendedorField.getText();
                    String direccion = dirField.getText();

                    TFacturas factura = new TFacturas();
                    factura.setIdCliente(idCliente);
                    factura.setIdVendedor(idVendedor);
                    factura.setDireccion(direccion);

                    // Llama al controlador
                    ControladorImp.getInstancia().accion(Eventos.CERRAR_VENTA, factura);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "ID Cliente inválido");
                }
            }
        });



        this.add(formPanel, BorderLayout.CENTER);
        this.add(cerrarButton, BorderLayout.SOUTH);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
    }

    private void limpiarCampos() {
        idClienteField.setText("");
        idVendedorField.setText("");
        dirField.setText("");
    }

    @Override
    public void actualizar(int evento, Object datos) {
        switch (evento) {
            case Eventos.RES_CERRAR_VENTA_OK:
                int idFactura = (int) datos;
                JOptionPane.showMessageDialog(this,
                        "Venta cerrada correctamente. ID de factura: " + idFactura,
                        "Éxito",
                        JOptionPane.INFORMATION_MESSAGE);
                this.dispose();
                break;

            case Eventos.RES_CERRAR_VENTA_KO:
                JOptionPane.showMessageDialog(this,
                        "Error al cerrar la venta. Verifica los datos.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                break;

            default:
                JOptionPane.showMessageDialog(this,
                        "Evento desconocido recibido: " + evento,
                        "Advertencia",
                        JOptionPane.WARNING_MESSAGE);
                break;
        }
    }
}
