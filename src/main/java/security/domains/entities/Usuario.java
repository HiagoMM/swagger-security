package security.domains.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	
	
	@NotNull(message="{string.nulo}")
	private String nome;
	@Email(message="{email.validation}")
	@NotNull(message="{string.nulo}")
	private String email;
	@NotNull(message="{string.nulo}")
	private String senha;

}
