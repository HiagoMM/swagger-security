package security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import security.domains.entities.Usuario;

@Repository
public interface UserRep extends JpaRepository<Usuario, Long>{

}
