package security.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import security.domains.entities.Usuario;
import security.exceptions.AlreadyExistsException;
import security.exceptions.NotExistsException;
import security.repository.UserRep;

@Service
public class UsuarioService {

	@Autowired
	private UserRep repository;
	
	
	public Usuario postUser(Usuario user) throws AlreadyExistsException {
		return repository.save(user);
		
	}
	
	
	public Usuario putUser(Usuario user,Long id) throws NotExistsException {
	
		if (repository.existsById(id)) {
			user.setId(id);
			return repository.save(user);
		}
		throw new NotExistsException();
	}
	
	
	public Usuario readUser(Long id) throws NotExistsException{
		if (repository.existsById(id)) {
			return repository.findById(id).get();
		}throw new NotExistsException();
	}
	
	
	public List<Usuario> readAllUsers(){
		return repository.findAll();
	}
	
	
	
	
	
	
	public void deleteUser(Long id) throws NotExistsException{
		if (!repository.existsById(id)) {
			throw new NotExistsException();
		}
		repository.deleteById(id);

	}
	
	
}
