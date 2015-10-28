package ejercicio4.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import javax.rmi.ssl.SslRMIClientSocketFactory;
import javax.rmi.ssl.SslRMIServerSocketFactory;

import ejercicio4.model.Persona;
import ejercicio4.service.PersonaService;
import ejercicio4.service.PersonaServiceFactory;

class PersonaRmiSsl1w
		extends UnicastRemoteObject
		implements PersonaRmi {
	
	private static final long serialVersionUID = -1717912069904899834L;

	private PersonaService personaService;
	
	public PersonaRmiSsl1w() throws RemoteException {
		super(0, new SslRMIClientSocketFactory(), new SslRMIServerSocketFactory());
		personaService = PersonaServiceFactory.createPersonaService();
	}

	@Override
	public void agregarPersona(Persona p) throws RemoteException {
		personaService.agregarPersona(p);
	}

	@Override
	public List<Persona> obtenerPersonas() throws RemoteException {
		return personaService.obtenerPersonas();
	}

}
