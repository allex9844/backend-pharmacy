package ro.kronsoft.training.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.kronsoft.training.entities.Patient;
import ro.kronsoft.training.repository.PatientRepository;

@Service
@Transactional
public class PatientService {
	@Autowired
	private PatientRepository patientRepository;
	
	public Patient getPatientById(Long id) {
		return patientRepository.getById(id);
	}
	
	public List<Patient> findPatients() {
		return patientRepository.findAll();
	}
	
	public Patient savePatient(Patient patient) {
		return patientRepository.save(patient);
	}
	
	public void deletePatient(Long id) {
		patientRepository.deleteById(id);
	}
	
	public void deletePatients() {
		patientRepository.deleteAll();
	}

	public Long countPatients() {
		return patientRepository.count();
	}

	public List<Patient> getPatientsByDoctor(Long doctorId) {
		return patientRepository.findByDoctorId(doctorId);
	}

}
