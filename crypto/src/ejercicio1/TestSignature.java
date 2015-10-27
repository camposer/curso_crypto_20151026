package ejercicio1;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.util.Base64;

import util.ArchivoUtil;

public class TestSignature {
	private static final String ALGORITMO_CIFRADO = "RSA"; 
	private static final String ALGORITMO_HASH = "SHA1"; 
	
	private String archivoOrigen = "files/quijote.txt";
	private String archivoFirma = "files/quijote.txt.sign";
	private Signature signature;
	private PublicKey publicKey;
	private PrivateKey privateKey;

	public TestSignature() throws Exception {
		this.signature = Signature.getInstance(ALGORITMO_HASH + "with" + 
				ALGORITMO_CIFRADO);
		generateKeys();
	}

	private void generateKeys() throws Exception {
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(ALGORITMO_CIFRADO);
		KeyPair keyPair = keyPairGenerator.generateKeyPair();
		this.publicKey = keyPair.getPublic();
		this.privateKey = keyPair.getPrivate();
	}
	
	public static void main(String[] args) throws Exception {
		new TestSignature().iniciar();
	}

	private void iniciar() throws Exception {
		firmar();
		
		if (verficar())
			System.out.println("El documento es válido");
		else
			System.out.println("El documento es inválido");
	}

	private void firmar() throws Exception {
		signature.initSign(privateKey);
		
		byte[] sourceText = ArchivoUtil.leerArchivo(archivoOrigen);
		signature.update(sourceText);
		
		byte[] signText = signature.sign();
		ArchivoUtil.escribirArchivo(signText, archivoFirma);
	}

	private boolean verficar() throws Exception {
		signature.initVerify(publicKey);

		byte[] sourceText = ArchivoUtil.leerArchivo(archivoOrigen);
		signature.update(sourceText);
		
		byte[] signatureText = ArchivoUtil.leerArchivo(archivoFirma);
		
		return signature.verify(signatureText);
	}


}
