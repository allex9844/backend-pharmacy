package ro.kronsoft.training.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ro.kronsoft.training.entities.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
	
	Patient getById(Long id);
	
	List<Patient> findByLastName(String lastName);
	
	List<Patient> findByDoctorId(Long doctorId);
	
}
