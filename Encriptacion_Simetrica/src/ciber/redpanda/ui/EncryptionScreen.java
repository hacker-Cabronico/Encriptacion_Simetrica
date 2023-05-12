package ciber.redpanda.ui;

import javax.crypto.SecretKey;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ciber.redpanda.*;

public class EncryptionScreen extends JFrame {
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

	private JTextArea plainTextArea;
    private JTextArea encryptedTextArea;

    public EncryptionScreen() {
    	_cifrado= new Cifrado();
        setTitle("Symmetric Encryption - Cifrado");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridBagLayout());

        JLabel plainTextLabel = new JLabel("Plain Text:");
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5);
        add(plainTextLabel, gbc);

        plainTextArea = new JTextArea(5, 20);
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5);
        add(new JScrollPane(plainTextArea), gbc);

        JLabel encryptedTextLabel = new JLabel("Encrypted Text:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(5, 5, 5, 5);
        add(encryptedTextLabel, gbc);

        encryptedTextArea = new JTextArea(5, 20);
        encryptedTextArea.setEditable(false);
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.insets = new Insets(5, 5, 5, 5);
        add(new JScrollPane(encryptedTextArea), gbc);

        JButton encryptButton = new JButton("Encrypt");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets(5, 5, 5, 5);
        add(encryptButton, gbc);

        encryptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String plainText = plainTextArea.getText();
                try {
                    SecretKey secretKey = _cifrado.get_Secret();
                    String encryptedText = _cifrado.encriptar(plainText, secretKey);
                    encryptedTextArea.setText(encryptedText);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        pack();
        setLocationRelativeTo(null);
    }
}
