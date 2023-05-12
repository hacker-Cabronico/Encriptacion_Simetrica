package ciber.redpanda.ui;

import javax.crypto.SecretKey;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import ciber.redpanda.*;

public class DecryptionScreen extends JFrame {
    private JTextArea encryptedTextArea;
    private JTextArea decryptedTextArea;
    private Cifrado _cifrado;
    
	/**
	 * @return the _cifrado
	 */
	public Cifrado get_cifrado() {
		return _cifrado;
	}

	/**
	 * @param _cifrado the _cifrado to set
	 */
	public void set_cifrado(Cifrado _cifrado) {
		this._cifrado = _cifrado;
	}
	
	
    public DecryptionScreen() {
        setTitle("Symmetric Encryption - Descifrado");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridBagLayout());

        JLabel encryptedTextLabel = new JLabel("Encrypted Text:");
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5);
        add(encryptedTextLabel, gbc);

        encryptedTextArea = new JTextArea(5, 20);
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5);
        add(new JScrollPane(encryptedTextArea), gbc);

        JLabel decryptedTextLabel = new JLabel("Decrypted Text:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(5, 5, 5, 5);
        add(decryptedTextLabel, gbc);

        decryptedTextArea = new JTextArea(5, 20);
        decryptedTextArea.setEditable(false);
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.insets = new Insets(5, 5, 5, 5);
        add(new JScrollPane(decryptedTextArea), gbc);

        JButton decryptButton = new JButton("Decrypt");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets(5, 5, 5, 5);
        add(decryptButton, gbc);

        decryptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String encryptedText = encryptedTextArea.getText();
                try {
                    SecretKey secretKey = _cifrado.get_Secret();
                    String decryptedText = _cifrado.desencriptar(encryptedText, secretKey);
                    decryptedTextArea.setText(decryptedText);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        pack();
        setLocationRelativeTo(null);
    }
}