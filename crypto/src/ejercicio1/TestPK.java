package ejercicio1;

import java.io.ByteArrayOutputStream;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.util.Date;

import javax.crypto.Cipher;

import util.ArchivoUtil;

public class TestPK {
	private static final String ALGORITMO = "RSA";
    public static final int MAX_BLOQUE_CIFRADO = 117;
    public static final int MAX_BLOQUE_DESCIFRADO = 128;

	private String archivoOrigen = "files/quijote.txt";
	private String archivoCifrado = "files/quijote.cifrado.txt";
	private String archivoDescifrado = "files/quijote.descifrado.txt";
	private Cipher cipher;
	private Key publicKey;
	private Key privateKey;

	public TestPK() throws Exception {
		this.cipher = Cipher.getInstance(ALGORITMO);
		generateKeys();
	}

	private void generateKeys() throws Exception {
		KeyPairGenerator keyPairGenerator = KeyPairGenerator
				.getInstance(ALGORITMO);
		KeyPair keyPair = keyPairGenerator.generateKeyPair();
		this.publicKey = keyPair.getPublic();
		this.privateKey = keyPair.getPrivate();
	}

	public static void main(String[] args) throws Exception {
		new TestPK().iniciar();
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
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);

        byte[] plainText = ArchivoUtil.leerArchivo(archivoOrigen);
        int bloques = (int) Math.ceil((double) plainText.length
                        / MAX_BLOQUE_CIFRADO);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int maxBloqueCifradoLen = MAX_BLOQUE_CIFRADO;

        for (int i = 0; i < bloques; i++) {
                if (i + 1 == bloques)
                        maxBloqueCifradoLen = plainText.length
                                        - (i * MAX_BLOQUE_CIFRADO);

                byte[] bloqueCifrado = cipher.doFinal(plainText, i
                                * MAX_BLOQUE_CIFRADO, maxBloqueCifradoLen);

                baos.write(bloqueCifrado);
        }

        byte[] cipherText = baos.toByteArray();
        ArchivoUtil.escribirArchivo(cipherText, archivoCifrado);
	}

	private void descifrar() throws Exception {
        cipher.init(Cipher.DECRYPT_MODE, publicKey);

        byte[] cipherText = ArchivoUtil.leerArchivo(archivoCifrado);
        int bloques = (int) Math.ceil((double) cipherText.length
                        / MAX_BLOQUE_DESCIFRADO);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int maxBloqueDescifradoLen = MAX_BLOQUE_DESCIFRADO;
        for (int i = 0; i < bloques; i++) {
                if (i + 1 == bloques)
                        maxBloqueDescifradoLen = cipherText.length
                                        - (i * MAX_BLOQUE_DESCIFRADO);

                byte[] bloque = cipher.doFinal(cipherText, i * MAX_BLOQUE_DESCIFRADO,
                                maxBloqueDescifradoLen);

                baos.write(bloque);
        }

        byte[] text = baos.toByteArray();
        ArchivoUtil.escribirArchivo(text, archivoDescifrado);
	}
}
