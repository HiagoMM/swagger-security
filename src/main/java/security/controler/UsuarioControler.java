package security.controler;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import security.domains.entities.Usuario;
import security.exceptions.AlreadyExistsException;
import security.exceptions.NotExistsException;
import security.services.UsuarioService;



@RestController
@RequestMapping("/user")
@Api(value = "Api Rest Usuario")
@CrossOrigin(origins="*")
public class UsuarioControler {

	@Autowired
	UsuarioService service;

	@ApiOperation(value = "Cria usuario")
	@PostMapping("/")
	public ResponseEntity<Usuario> postUser(@Valid @RequestBody Usuario user) {
		try {
			user = service.postUser(user);
			return new ResponseEntity<Usuario>(user, HttpStatus.CREATED);
		} catch (AlreadyExistsException e) {
			return new ResponseEntity<Usuario>(HttpStatus.CONFLICT);
		}
	}

	
	@ApiOperation(value = "Atualiza usuario")
	@PutMapping("/{id}")
	public ResponseEntity<Usuario> putUser(@Valid @RequestBody Usuario user,@PathVariable Long id) {
		try {
			user = service.putUser(user,id);
			return new ResponseEntity<Usuario>(user,HttpStatus.OK);
		} catch (NotExistsException e) {
			return new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	@ApiOperation(value = "Devolve Lista de Usuarios")
	@GetMapping("/")
	public ResponseEntity<List<Usuario>> getAllUser(){
		return new ResponseEntity<List<Usuario>>(service.readAllUsers(),HttpStatus.OK);
	}
	
	@ApiOperation(value = "busca usuario por id")
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> getUser(@PathVariable Long id){
		try {
			Usuario user =service.readUser(id);
			return new ResponseEntity<Usuario>(user,HttpStatus.OK);
		} catch (NotExistsException e) {
			return new ResponseEntity<Usuario>(HttpStatus.CONFLICT);
		}
	}
	
	@ApiOperation(value = "deleta usuario por id")
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable Long id){
		try {
			service.deleteUser(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (NotExistsException e) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
			
		}
	}
}
