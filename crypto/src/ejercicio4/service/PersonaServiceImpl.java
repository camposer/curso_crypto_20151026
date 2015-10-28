package ejercicio4.service;

import java.util.List;

import ejercicio4.dao.PersonaDao;
import ejercicio4.dao.PersonaDaoFactory;
import ejercicio4.model.Persona;

class PersonaServiceImpl implements PersonaService {
	private PersonaDao personaDao;
	
	public PersonaServiceImpl() {
		personaDao = PersonaDaoFactory.createPersonaDao();
	}

	@Override
	public void agregarPersona(Persona p) {
		personaDao.agregar(p);
	}

	@Override
	public List<Persona> obtenerPersonas() {
		return personaDao.obtenerTodos();
	}

}
