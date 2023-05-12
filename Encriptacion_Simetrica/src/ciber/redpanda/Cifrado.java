package ciber.redpanda;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;

public class Cifrado {
	private SecretKey _Secret;  
	
    public SecretKey generarClave() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128);
        return keyGenerator.generateKey();
    }

    public String encriptar(String texto, SecretKey clave) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, clave);
        byte[] encryptedBytes = cipher.doFinal(texto.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public String desencriptar(String textoEncriptado, SecretKey clave) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, clave);
        byte[] encryptedBytes = Base64.getDecoder().decode(textoEncriptado);
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
        return new String(decryptedBytes);
    }

	/**
	 * @return the _Secret
	 */
	public SecretKey get_Secret() {
		return _Secret;
	}

	/**
	 * @param secretKey the _Secret to set
	 */
	public void set_Secret(SecretKey secretKey) {
		this._Secret = secretKey;
	}
}
