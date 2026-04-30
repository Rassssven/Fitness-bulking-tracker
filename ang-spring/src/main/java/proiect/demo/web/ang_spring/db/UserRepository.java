package proiect.demo.web.ang_spring.db;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import proiect.demo.web.ang_spring.Entities.User;

public interface UserRepository extends JpaRepository<User, Long>{

	boolean existsByEmail(String email);
	
	Optional<User> findByEmail(String email);
	
}
