package ciber.redpanda.ui;

import javax.swing.*;

import ciber.redpanda.Cifrado;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class Simetrica extends JFrame {
	private static Cifrado _Cifrador;
	
    public static void main(String[] args) throws Exception {
    	_Cifrador = new Cifrado();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGUI();
            }
        });
        _Cifrador.set_Secret(_Cifrador.generarClave());
    }	
    
    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Symmetric Encryption");

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        JButton encryptButton = new JButton("Cifrar");
        encryptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EncryptionScreen encryptionScreen = new EncryptionScreen();
                encryptionScreen.setVisible(true);
                encryptionScreen.set_cifrado(_Cifrador); 
            }
        });

        JButton decryptButton = new JButton("Descifrar");
        decryptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DecryptionScreen decryptionScreen = new DecryptionScreen();
                decryptionScreen.setVisible(true);
                decryptionScreen.set_cifrado(_Cifrador);
            }
        });

        panel.add(encryptButton);
        panel.add(decryptButton);

        frame.getContentPane().add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}