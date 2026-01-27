package presentacion_;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import presentacion.Controlador.ControladorImp;
import presentacion.Controlador.Eventos;
import presentacion.GUIMarca.VistaMarca;

public class MainWindow extends JFrame implements IGUI {
    private JButton Empleados, Clientes, Envios, Facturas, Marca, Producto, Proveedor;

    public MainWindow() {
        super("Heladería");

        // Panel con fondo personalizado
        JPanel panelConFondo = new JPanel() {
            Image fondo = new ImageIcon("resources/Icons/fondo3.png").getImage();

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this); // Escala al tamaño del panel
            }
        };
        panelConFondo.setLayout(null); // Posicionamiento absoluto

        int yInicio = 220;  // más arriba que antes
        int alturaBoton = 40;
        int anchoBoton = 280;
        int espacio = 10;

        // Nueva referencia vertical centrada en el cartel de "Heladería"
        int centroY = 280; // Ajusta si el cartel está más arriba/abajo

        // Estilo de los botones
        Font fuente = new Font("Segoe Print", Font.BOLD, 14);
        Color colorFondo = new Color(255, 255, 255, 230);
        Color colorTexto = new Color(219, 112, 147);

        // Botones encima del título
        Clientes = crearBoton("CLIENTES", centroY - 4 * (alturaBoton + espacio), anchoBoton, alturaBoton, fuente, colorFondo, colorTexto);
        Clientes.addActionListener(e -> {new ClientesFrame(); });
        panelConFondo.add(Clientes);

        Empleados = crearBoton("EMPLEADOS", centroY - 3 * (alturaBoton + espacio), anchoBoton, alturaBoton, fuente, colorFondo, colorTexto);
        Empleados.addActionListener(new EmpleadosMain());
        panelConFondo.add(Empleados);

        // Botones debajo del título
        Envios = crearBoton("ENVÍOS", centroY - 2 * (alturaBoton + espacio), anchoBoton, alturaBoton, fuente, colorFondo, colorTexto);
        Envios.addActionListener(e -> {});
        panelConFondo.add(Envios);

        Facturas = crearBoton("FACTURAS", centroY + 2 * (alturaBoton + espacio), anchoBoton, alturaBoton, fuente, colorFondo, colorTexto);
        Facturas.addActionListener(e -> {
            this.setVisible(true);
            new FacturasIntermedio(this);
        });
        panelConFondo.add(Facturas);

        Producto = crearBoton("PRODUCTOS", centroY + 3 * (alturaBoton + espacio), anchoBoton, alturaBoton, fuente, colorFondo, colorTexto);
        Producto.addActionListener(new ProductosDialog());
        panelConFondo.add(Producto);

        Marca = crearBoton("MARCA", centroY + 4 * (alturaBoton + espacio), anchoBoton, alturaBoton, fuente, colorFondo, colorTexto);
        Marca.addActionListener(new VistaMarca());
        panelConFondo.add(Marca);

        Proveedor = crearBoton("PROVEEDORES", centroY + 5 * (alturaBoton + espacio), anchoBoton, alturaBoton, fuente, colorFondo, colorTexto);
        Proveedor.addActionListener(e -> new ProveedorDialog().setVisible(true));
        panelConFondo.add(Proveedor);


        this.setContentPane(panelConFondo);
        this.setSize(500, 650); // ⬅ más bajo que antes
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
    }

    private JButton crearBoton(String texto, int y, int ancho, int alto, Font fuente, Color fondo, Color textoColor) {
        JButton boton = new JButton(texto);
        boton.setBounds((500 - ancho) / 2, y, ancho, alto);
        boton.setFont(fuente);
        boton.setBackground(fondo);
        boton.setForeground(textoColor);
        boton.setFocusPainted(false);
        boton.setBorder(BorderFactory.createLineBorder(new Color(219, 112, 147), 2, true));
        return boton;
    }

    @Override
    public void actualizar(int evento, Object datos) {
        // No implementado
    }
}