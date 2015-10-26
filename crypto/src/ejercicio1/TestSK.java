package ejercicio1;

import java.security.Key;
import java.util.Date;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;

import util.ArchivoUtil;

public class TestSK {
	private static final String ALGORITMO = "AES";
	
	private String archivoOrigen = "files/quijote.txt"; 
	private String archivoCifrado = "files/quijote.cifrado.txt";
	private String archivoDescifrado = "files/quijote.descifrado.txt";
	private Cipher cipher;
	private Key key;

	public TestSK() throws Exception {
		this.cipher = Cipher.getInstance(ALGORITMO);
		this.key = getKey();
	}

	private Key getKey() throws Exception {
		return KeyGenerator.getInstance(ALGORITMO).generateKey();
	}
	
	public static void main(String[] args) throws Exception {
		new TestSK().iniciar();
	}

	private void iniciar() throws Exception {
		long inicio = new Date().getTime();
		
		cifrar();
		System.out.println("Cifrado " + archivoOrigen + " en " + archivoCifrado);

		descifrar();
		System.out.println("Descifrado " + archivoCifrado + " en " + archivoDescifrado);
		
		long fin = new Date().getTime();
		System.out.println("Duración total = " + (fin - inicio) + " mseg");
	}

	private void cifrar() throws Exception {
		cipher.init(Cipher.ENCRYPT_MODE, key);
		byte[] cipherText = 
				cipher.doFinal(ArchivoUtil.leerArchivo(archivoOrigen));
		ArchivoUtil.escribirArchivo(cipherText, archivoCifrado);
	}

	private void descifrar() throws Exception {
		cipher.init(Cipher.DECRYPT_MODE, key);
		byte[] plainText = 
				cipher.doFinal(ArchivoUtil.leerArchivo(archivoCifrado));
		ArchivoUtil.escribirArchivo(plainText, archivoDescifrado);
	}

}
