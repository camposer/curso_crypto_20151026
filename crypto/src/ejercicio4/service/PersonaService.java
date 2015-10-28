package ejercicio4.service;

import java.util.List;

import ejercicio4.model.Persona;

public interface PersonaService {
	void agregarPersona(Persona p);
	List<Persona> obtenerPersonas();
}
