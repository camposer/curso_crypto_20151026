package ejercicio4.gui;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.List;
import java.util.Scanner;

import ejercicio4.model.Persona;
import ejercicio4.rmi.PersonaRmi;

/**
 * SSL 1w
 * OPTIONS: -Djavax.net.ssl.trustStore=keystores/truststore-cliente.ts -Djavax.net.ssl.trustStorePassword=123456
 *
 * SSL 2w
 * OPTIONS: 
 * -Djavax.net.ssl.keyStore=keystores/keystore-cliente.jks -Djavax.net.ssl.keyStorePassword=123456
 * -Djavax.net.ssl.trustStore=keystores/truststore-cliente.ts -Djavax.net.ssl.trustStorePassword=123456
 */
public class PersonaGui {
	private Scanner scanner;
	private PersonaRmi personaRmi;
	
	public PersonaGui() throws Exception {
		scanner = new Scanner(System.in);
		personaRmi = (PersonaRmi)LocateRegistry
				.getRegistry()
				.lookup("PersonaRmi");
	}
	
	public void iniciar() throws RemoteException {
		
		while (true) {
			System.out.println();
			System.out.println("Gestión de personas");
			System.out.println("1. Agregar");
			System.out.println("2. Obtener todos");
			System.out.println("3. Salir");
			System.out.print("? ");
			
			String opcion = scanner.nextLine();
			
			if (opcion.equals("1"))
				agregar();
			else if (opcion.equals("2"))
				listar();
			else if (opcion.equals("3"))
				break;
		}
		

	}
	
	private void agregar() throws RemoteException {
		Persona p  = new Persona();
		
		System.out.print("Nombre? ");
		p.setNombre(scanner.nextLine());
		System.out.print("Apellido? ");
		p.setApellido(scanner.nextLine());
		System.out.print("Edad? ");
		p.setEdad(Integer.parseInt(scanner.nextLine()));
		
		personaRmi.agregarPersona(p);
	}

	private void listar() throws RemoteException {
		List<Persona> personas = personaRmi.obtenerPersonas();
		
		if (personas != null) for (Persona p : personas)
			System.out.println(p);
	}

	public static void main(String[] args) throws Exception {
		new PersonaGui().iniciar();
	}
}
