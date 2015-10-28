package ejercicio4.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import ejercicio4.model.Persona;

public interface PersonaRmi extends Remote {
	void agregarPersona(Persona p) throws RemoteException;
	List<Persona> obtenerPersonas() throws RemoteException;
}
