package ejercicio1;

import java.security.MessageDigest;

import util.ArchivoUtil;
import util.StringUtil;

public class TestDigest {
	private static final String ALGORITMO = "SHA1";
	private String archivoOrigen = "files/quijote.txt";
	private MessageDigest messageDigest;
	
	public TestDigest() throws Exception {
		this.messageDigest = MessageDigest.getInstance(ALGORITMO);
	}

	public static void main(String[] args) throws Exception {
		new TestDigest().iniciar();
	}

	private void iniciar() throws Exception {
		byte[] digestText = hash();

		System.out.println("Hash = " + StringUtil.getHex(digestText));
		System.out.println("Longitud hash = " + StringUtil.getHex(digestText).length());
	}

	private byte[] hash() throws Exception {
		byte[] plainText = ArchivoUtil.leerArchivo(archivoOrigen);
		return messageDigest.digest(plainText);
	}

}