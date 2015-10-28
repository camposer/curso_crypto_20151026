package ejercicio4.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import ejercicio4.model.Persona;
import ejercicio4.service.PersonaService;
import ejercicio4.service.PersonaServiceFactory;

class PersonaRmiImpl
		extends UnicastRemoteObject
		implements PersonaRmi {
	
	private static final long serialVersionUID = -1717912069904899834L;

	private PersonaService personaService;
	
	public PersonaRmiImpl() throws RemoteException {
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
