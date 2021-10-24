package ro.kronsoft.training.service;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ro.kronsoft.training.entities.DocType;
import ro.kronsoft.training.entities.Doctor;
import ro.kronsoft.training.entities.Patient;
import ro.kronsoft.training.repository.DoctorRepository;
import ro.kronsoft.training.repository.PatientRepository;

@Service
@Transactional
public class DoctorService {
	@Autowired
	private DoctorRepository doctorRepository;
	@Autowired
	private PatientRepository patientRepository;

	public List<Doctor> findDoctors() {
		return doctorRepository.findAll();
	}
	
	public Doctor getDoctorById(Long id) {
		return doctorRepository.getById(id);
	}

	public Doctor saveDoctor(Doctor doctor) {
		return doctorRepository.save(doctor);
	}

	public void deleteDoctor(Long id) {
		doctorRepository.deleteById(id);
	}

	public void deleteDoctors() {
		doctorRepository.deleteAll();
	}

	@PostConstruct
	private void createStartupData() {
		try {
			if (doctorRepository.count() == 0 && patientRepository.count() == 0) {
				Doctor doc1 = doctorRepository.save(new Doctor("Aurelia", "Marin", "marinau@yahoo.com",
						"Str. Cl. Bucuresti", 32, DocType.PHYSICIAN));
				Doctor doc2 = doctorRepository.save(
						new Doctor("Ionel", "Marcu", "ionel.doctor@yahoo.com", "Str. Nucului", 182, DocType.THERAPIST));
				Doctor doc3 = doctorRepository.save(
						new Doctor("Damian", "Olaru", "damian-olaru@gmail.com", "Str. Florii", 11, DocType.PHYSICIAN));
				Doctor doc4 = doctorRepository
						.save(new Doctor("Maria", "Pavel", "pmaria@gmail.com", "Bl. Saturn", 58, DocType.SURGEON));
				patientRepository.save(new Patient("Alexandru", "Pop", "0754865895", doc4));
				patientRepository.save(new Patient("Mihai", "Pop", "0785642536", doc4));
				patientRepository.save(new Patient("Dorel", "Antonoaie", "0786472355", doc3));
				patientRepository.save(new Patient("Mihai", "Maxim", "0796387451", doc2));
				patientRepository.save(new Patient("Oana", "Pop", "0714752369", doc2));
				patientRepository.save(new Patient("Mihaela", "Roman", "0769835412", doc1));
				patientRepository.save(new Patient("Marcel", "Constantin", "0723986572", doc1));
				patientRepository.save(new Patient("Iulia", "Popescu", "0723456897", doc1));
			}
		} catch (Exception e) {
			System.out.println("Fail silently as this is just dummy data insertion method");
		}
	}

}
