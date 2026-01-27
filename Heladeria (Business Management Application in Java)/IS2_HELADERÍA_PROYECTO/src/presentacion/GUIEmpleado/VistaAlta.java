package presentacion.GUIEmpleado;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import negocio.Empleado.TransferDirectivo;
import negocio.Empleado.TransferEmpleado;
import negocio.Empleado.TransferVendedor;
import presentacion.Controlador.Eventos;
import presentacion_.IGUI;

public class VistaAlta extends JFrame implements IGUI {

    private JPanel mainPanel, datosPanel, puestoPanel, detallesPanel, botonesAbajo;
    private JTextField nombreField, apellidoField, dniField, sueldoField;
    private JRadioButton vendedorRadio, directivoRadio;
    private JTextArea cargoArea, ventasArea;
    private ButtonGroup roleGroup;
    private JPanel directivoPanel, vendedorPanel;

    public VistaAlta() {
        setTitle("Dar de alta un empleado");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // === Panel de campos básicos ===
        datosPanel = new JPanel(new GridLayout(4, 2, 5, 5));

        datosPanel.add(new JLabel("Nombre: "));
        nombreField = new JTextField();
        datosPanel.add(nombreField);

        datosPanel.add(new JLabel("Apellido: "));
        apellidoField = new JTextField();
        datosPanel.add(apellidoField);

        datosPanel.add(new JLabel("DNI: "));
        dniField = new JTextField();
        datosPanel.add(dniField);

        datosPanel.add(new JLabel("Sueldo: "));
        sueldoField = new JTextField();
        datosPanel.add(sueldoField);

        // === Panel de selección de tipo de empleado ===
        puestoPanel = new JPanel(new GridLayout(1, 2, 5, 5));
        puestoPanel.setBorder(BorderFactory.createTitledBorder("Tipo de empleado"));

        vendedorRadio = new JRadioButton("Vendedor");
        directivoRadio = new JRadioButton("Directivo");

        roleGroup = new ButtonGroup();
        roleGroup.add(vendedorRadio);
        roleGroup.add(directivoRadio);

        puestoPanel.add(vendedorRadio);
        puestoPanel.add(directivoRadio);

        // === Panel de detalles dinámico (se llena al pulsar un radio) ===
        detallesPanel = new JPanel(new CardLayout());

        // Panel para directivo
        directivoPanel = new JPanel(new BorderLayout());
        directivoPanel.add(new JLabel("Cargo:"), BorderLayout.NORTH);
        cargoArea = new JTextArea(3, 20);
        directivoPanel.add(new JScrollPane(cargoArea), BorderLayout.CENTER);

        // Panel para vendedor
        vendedorPanel = new JPanel(new BorderLayout());
        vendedorPanel.add(new JLabel("Número de ventas:"), BorderLayout.NORTH);
        ventasArea = new JTextArea(3, 20);
        vendedorPanel.add(new JScrollPane(ventasArea), BorderLayout.CENTER);

        // === Listeners para mostrar panel correcto al seleccionar ===
        vendedorRadio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                detallesPanel.removeAll();
                detallesPanel.add(vendedorPanel);
                detallesPanel.revalidate();
                detallesPanel.repaint();
            }
        });

        directivoRadio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                detallesPanel.removeAll();
                detallesPanel.add(directivoPanel);
                detallesPanel.revalidate();
                detallesPanel.repaint();
            }
        });

        // === Botones inferiores ===
        JButton enviarButton = new JButton("Dar de alta");
        enviarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                procesarAlta();
                dispose();
            }
        });

        JButton cancelarButton = new JButton("Cancelar");
        cancelarButton.addActionListener(e -> dispose());

        botonesAbajo = new JPanel();
        botonesAbajo.add(enviarButton);
        botonesAbajo.add(cancelarButton);

        // === Estructura intermedia para mantener orden ===
        JPanel centerPanel = new JPanel(new BorderLayout(10, 10));
        centerPanel.add(puestoPanel, BorderLayout.NORTH);
        centerPanel.add(detallesPanel, BorderLayout.CENTER);

        mainPanel.add(datosPanel, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(botonesAbajo, BorderLayout.SOUTH);

        this.add(mainPanel);
        setVisible(true);
    }

    private void procesarAlta() {
        if (nombreField.getText().isEmpty() || apellidoField.getText().isEmpty()
                || dniField.getText().isEmpty() || sueldoField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor complete todos los campos básicos", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!vendedorRadio.isSelected() && !directivoRadio.isSelected()) {
            JOptionPane.showMessageDialog(this, "Seleccione un tipo de empleado", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            String nombre = nombreField.getText().trim();
            String apellido = apellidoField.getText().trim();
            String dni = dniField.getText().trim();
            int sueldo = Integer.parseInt(sueldoField.getText().trim());

            TransferEmpleado empleado = null;

            if (vendedorRadio.isSelected()) {
                if (ventasArea.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Ingrese el número de ventas", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                int ventas = Integer.parseInt(ventasArea.getText().trim());
                empleado = new TransferVendedor(-1, dni, nombre, apellido, true, ventas, sueldo);

            } else if (directivoRadio.isSelected()) {
                if (cargoArea.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Ingrese el cargo del directivo", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                String cargo = cargoArea.getText().trim();
                empleado = new TransferDirectivo(-1, dni, nombre, apellido, true, cargo, sueldo);
            }

            if (empleado != null) {
                presentacion.Controlador.ControladorImp.getInstancia().accion(presentacion.Controlador.Eventos.ALTA_EMPLEADO, empleado);
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Error en formato numérico. Verifique sueldo y ventas.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void actualizar(int evento, Object datos) {
        if (evento == Eventos.RES_ALTA_EMPLEADO_OK) {
            JOptionPane.showMessageDialog(this, "Empleado dado de alta correctamente. ID: " + datos);
        } else if (evento == Eventos.RES_ALTA_EMPLEADO_KO) {
            JOptionPane.showMessageDialog(this, "Error al dar de alta el empleado");
        }
    }
}