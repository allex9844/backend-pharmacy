package ro.kronsoft.training.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ro.kronsoft.training.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional<User> findByUsername(String username);
	
	boolean existsByUsername(String username);
	
	@Query(value = "SELECT EXISTS(SELECT * FROM users WHERE id != :id AND username = :username)", 
			nativeQuery = true)
	boolean existsByUsernameNotId(@Param("username") String username, @Param("id") Long id);
	
	User getById(Long id);

}
