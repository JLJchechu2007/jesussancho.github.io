package simulator.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class Exit implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		int confirm = JOptionPane.showConfirmDialog(
                null,
                "Are you sure you want to exit?",
                "Exit Simulation",
                JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
}


