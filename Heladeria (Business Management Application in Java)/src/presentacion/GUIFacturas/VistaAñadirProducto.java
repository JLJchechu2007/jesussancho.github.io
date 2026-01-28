package presentacion.GUIFacturas;

import java.awt.GridLayout;
import javax.swing.*;
import negocio.Facturas.TLineaFactura;
import negocio.Producto.TProducto;
import integracion.Producto.DAOProducto;
import integracion.Producto.DAOProductoImp;
import presentacion.Controlador.*;
import presentacion_.IGUI;

@SuppressWarnings("serial")
public class VistaAñadirProducto extends JFrame implements IGUI {

    private JTextField fieldIdProducto;
    private JTextField fieldCantidad;
    private Controlador ctrl;

    public VistaAñadirProducto() {
        this.setTitle("AÑADIR PRODUCTO A FACTURA");
        this.setSize(300, 250);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.ctrl = Controlador.getInstancia();
        this.initGUI();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void initGUI() {
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        panel.add(new JLabel("ID Producto:"));
        fieldIdProducto = new JTextField();
        panel.add(fieldIdProducto);

        panel.add(new JLabel("Cantidad:"));
        fieldCantidad = new JTextField();
        panel.add(fieldCantidad);

        JButton cancelar = new JButton("Cancelar");
        cancelar.addActionListener(e -> this.dispose());

        JButton aceptar = new JButton("Aceptar");
        aceptar.addActionListener(e -> {
            try {
                int idProducto = Integer.parseInt(fieldIdProducto.getText().trim());
                int cantidad = Integer.parseInt(fieldCantidad.getText().trim());

                if (cantidad <= 0) throw new NumberFormatException();

                // Obtener el precio desde la BD
                DAOProducto daoProducto = new DAOProductoImp(); // o desde la FactoriaAbstractaIntegracion
                TProducto p = daoProducto.read(idProducto);

                if (p == null) {
                    JOptionPane.showMessageDialog(this, "Producto no encontrado.");
                    return;
                }

                float precio = p.getPrecioProducto();

                // idFactura se completa en cerrarVenta
                TLineaFactura linea = new TLineaFactura(0, 0, idProducto, cantidad, precio);
                ctrl.accion(Eventos.AÑADIR_PRODUCTO, linea);

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Por favor, introduzca valores numéricos válidos (cantidad > 0).");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al acceder al producto.");
            }

            this.dispose();
        });

        panel.add(cancelar);
        panel.add(aceptar);
        this.add(panel);
    }

    @Override
    public void actualizar(int evento, Object datos) {
        this.dispose();
        switch (evento) {
            case Eventos.RES_AÑADIR_PRODUCTO_OK:
                JOptionPane.showMessageDialog(null, "Producto añadido correctamente a la factura.");
                break;
            case Eventos.RES_AÑADIR_PRODUCTO_KO:
                JOptionPane.showMessageDialog(null, "Error al añadir producto a la factura.");
                break;
        }
    }
}
