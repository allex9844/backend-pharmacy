package ro.kronsoft.training.transformer;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ro.kronsoft.training.dto.DoctorDto;
import ro.kronsoft.training.entities.Doctor;
import ro.kronsoft.training.repository.DoctorRepository;

@Component
public class DoctorTransformer extends AbstractTransformer<Doctor, DoctorDto> {
	@Autowired
	private DoctorRepository doctorRepository;

	public DoctorDto toDto(Doctor doctor) {
		DoctorDto dto = new DoctorDto();
		BeanUtils.copyProperties(doctor, dto);
		return dto;
	}

	public Doctor toEntity(DoctorDto dto) {
		Doctor doctor = findOrCreateNew(dto.getId());
		BeanUtils.copyProperties(dto, doctor);
		return doctor;
	}

	@Override
	protected Doctor findOrCreateNew(Long id) {
		return id == null ? new Doctor() : doctorRepository.findById(id).orElseGet(() -> new Doctor());
	}

}
