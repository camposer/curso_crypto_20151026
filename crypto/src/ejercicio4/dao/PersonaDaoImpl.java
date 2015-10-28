package ejercicio4.dao;

import java.util.ArrayList;
import java.util.List;

import ejercicio4.model.Persona;

class PersonaDaoImpl implements PersonaDao {
	private List<Persona> personas = new ArrayList<>();
	private long contador = 1L;

	@Override
	public void agregar(Persona p) {
		p.setId(contador++);
		personas.add(p);
	}

	@Override
	public List<Persona> obtenerTodos() {
		return personas;
	}

}
