package ejercicio4.dao;

import java.util.List;

import ejercicio4.model.Persona;

public interface PersonaDao {
	void agregar(Persona p);
	List<Persona> obtenerTodos();
}
