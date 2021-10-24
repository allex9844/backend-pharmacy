package ro.kronsoft.training.transformer;

import java.time.LocalDate;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ro.kronsoft.training.dto.PatientDto;
import ro.kronsoft.training.entities.Patient;
import ro.kronsoft.training.repository.DoctorRepository;
import ro.kronsoft.training.repository.PatientRepository;

@Component
public class PatientTransformer extends AbstractTransformer<Patient, PatientDto> {
	@Autowired
	private PatientRepository patientRepository;
	@Autowired
	private DoctorRepository doctorRepository;
	
	@Override
	public PatientDto toDto(Patient patient) {
		PatientDto dto = new PatientDto();
		BeanUtils.copyProperties(patient, dto);
		if (patient.getBirthday() != null) {
			dto.setBirthday(patient.getBirthday().toString());
		}
		dto.setDoctorId(patient.getDoctor().getId());
		return dto;
	}
	
	@Override
	public Patient toEntity(PatientDto dto) {
		Patient patient = findOrCreateNew(dto.getId());
		BeanUtils.copyProperties(dto, patient);
		if (dto.getBirthday() != null) {
			patient.setBirthday(LocalDate.parse(dto.getBirthday()));
		}
		patient.setDoctor(doctorRepository.getById(dto.getDoctorId()));
		return patient;
	}
	
	@Override
	protected Patient findOrCreateNew(Long id) {
		return id == null ? new Patient() : patientRepository.findById(id).orElseGet(() -> new Patient());
	}

}
