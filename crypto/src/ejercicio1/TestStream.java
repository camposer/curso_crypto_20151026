package ejercicio1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.security.Key;
import java.util.Date;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;

public class TestStream {
	private static final String ALGORITMO = "DESede";
	
	private String archivoOrigen = "files/quijote.txt"; 
	private String archivoCifrado = "files/quijote.cifrado.txt";
	private String archivoDescifrado = "files/quijote.descifrado.txt";
	private Cipher cipher;
	private Key key;

	public TestStream() throws Exception {
		this.cipher = Cipher.getInstance(ALGORITMO);
		this.key = getKey();
	}

	private Key getKey() throws Exception {
		return KeyGenerator.getInstance(ALGORITMO).generateKey();
	}
	
	public static void main(String[] args) throws Exception {
		new TestStream().iniciar();
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

		try (
				FileInputStream fis = new FileInputStream(archivoOrigen);
				FileOutputStream fos = new FileOutputStream(archivoCifrado);
				CipherOutputStream cos = new CipherOutputStream(fos, cipher);
			) {
		
			byte b = (byte)fis.read();
			while (b != -1) {
				cos.write(b);
				b = (byte)fis.read();
			}
		}
	}

	private void descifrar() throws Exception {
		cipher.init(Cipher.DECRYPT_MODE, key);

		try (
				FileOutputStream fos = new FileOutputStream(archivoDescifrado);
				PrintWriter pw = new PrintWriter(new OutputStreamWriter(fos));
				FileInputStream fis = new FileInputStream(archivoCifrado);
				CipherInputStream cis = new CipherInputStream(fis, cipher);
				BufferedReader br = new BufferedReader(new InputStreamReader(cis));
			) {
		
			String linea = br.readLine();
			while (linea != null) {
				pw.println(linea);
				linea = br.readLine();
			}
		}
	}

}
