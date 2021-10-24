package ro.kronsoft.training.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ro.kronsoft.training.entities.Doctor;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
	
	Doctor getById(Long id);
	
	boolean existsById(Long id);
	
	boolean existsByEmail(String email);
	
	@Query(nativeQuery = true, value = "SELECT COUNT(*) FROM doctor WHERE email = :email AND id != :id")
	long countByEmailNotId(@Param("email") String email, @Param("id") Long id);
	
}
