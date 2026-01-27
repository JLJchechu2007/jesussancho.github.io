package simulator.view;

import java.awt.event.ActionEvent;
import java.io.InputStream;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import simulator.control.Controller;

public class FileChooserCarretera implements ActionListener {
    JFrame f;
    Controller c;

    public FileChooserCarretera(JFrame frame, Controller c) {
        f = frame;
        this.c = c;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser filec = new JFileChooser();
        filec.setCurrentDirectory(new File("F:\\universidad\\tp2\\workspace\\proyecto\\resources\\examples"));
        int result = filec.showOpenDialog(f);
        
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = filec.getSelectedFile();
            try (InputStream in = new FileInputStream(selectedFile)) {
                c.reset();
                c.loadEvents(in);
                JOptionPane.showMessageDialog(f, "Archivo seleccionado: " + selectedFile.getAbsolutePath());
            } catch (IOException exception) {
                exception.printStackTrace();
                JOptionPane.showMessageDialog(f, "Error al cargar el archivo", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(f, "No se seleccionó ningún archivo");
        }
    }
}
