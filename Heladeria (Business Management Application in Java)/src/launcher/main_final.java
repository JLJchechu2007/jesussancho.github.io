package launcher;

import java.awt.GridLayout;


import javax.swing.*;
import presentacion.GUIFacturas.*;
import presentacion_.MainWindow;

public class main_final extends JFrame {

    public main_final() {
        super("Gestión de Facturas");
    }

   
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainWindow().setVisible(true));
    }
}

