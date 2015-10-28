package ejercicio4.rmi;

import java.rmi.RemoteException;

public abstract class PersonaRmiFactory {

	public static PersonaRmi createPersonaRmi() throws RemoteException {
		//return new PersonaRmiImpl();
		//return new PersonaRmiSsl1w();
		return new PersonaRmiSsl2w();
	}

}
