package ejercicio4.server;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import ejercicio4.rmi.PersonaRmiFactory;

/**
 * SSL 1w
 * OPTIONS: -Djavax.net.ssl.keyStore=keystores/keystore-servidor.jks -Djavax.net.ssl.keyStorePassword=123456
 *
 * SSL 2w
 * OPTIONS: 
 * -Djavax.net.ssl.keyStore=keystores/keystore-servidor.jks -Djavax.net.ssl.keyStorePassword=123456
 * -Djavax.net.ssl.trustStore=keystores/truststore-servidor.ts -Djavax.net.ssl.trustStorePassword=123456
 */
public class PersonaServer {
	private static final int PUERTO = 1099;

	public static void main(String[] args) throws Exception {
		Registry registry = 
				LocateRegistry.createRegistry(PUERTO);
		System.out.println("Iniciado PersonaServer...");
		
		registry.rebind("PersonaRmi", 
				PersonaRmiFactory.createPersonaRmi());
	}
}
