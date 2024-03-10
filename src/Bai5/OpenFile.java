package Bai5;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class OpenFile extends JFrame implements ActionListener{
	
	private JTextArea Area;
    private JButton Button;

    public OpenFile() {
        setTitle("File Chooser Example");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // TextArea 
        Area = new JTextArea();
        Area.setEditable(false);

        // Button 
        Button = new JButton("Open...");
        Button.addActionListener(this);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(Area, BorderLayout.CENTER);
        panel.add(Button, BorderLayout.SOUTH);

        add(panel);
    }
    
 // button click event
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == Button) {
            JFileChooser fileChooser = new JFileChooser();
            int returnValue = fileChooser.showOpenDialog(this);

            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                try {
                    BufferedReader reader = new BufferedReader(new FileReader(selectedFile));
                    StringBuilder content = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        content.append(line).append("\n");
                    }
                    reader.close();
                    Area.setText(content.toString());
                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Error reading file", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			OpenFile example = new OpenFile();
            example.setVisible(true);
        });

	}

}
