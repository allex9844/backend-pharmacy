package ro.kronsoft.training.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ro.kronsoft.training.dto.PatientDto;
import ro.kronsoft.training.service.PatientService;
import ro.kronsoft.training.transformer.PatientTransformer;

@RestController
@RequestMapping("/patient")
public class PatientController {
	@Autowired
	private PatientService patientService;
	@Autowired
	private PatientTransformer patientTransformer;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<PatientDto> getPatients() {
		return patientTransformer.toDtoList(patientService.findPatients());
	}
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public PatientDto getPatientById(@PathVariable Long id) {
		return patientTransformer.toDto(patientService.getPatientById(id));
	}
	
	@PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public PatientDto createPatient(@Validated @RequestBody PatientDto patient) {
		return patientTransformer.toDto(patientService.savePatient(patientTransformer.toEntity(patient)));
	}
	
	@PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updatePatient(@Validated @RequestBody PatientDto patient) {
		patientService.savePatient(patientTransformer.toEntity(patient));
	}

	@Secured({"ROLE_ADMIN"})
	@DeleteMapping(value = "/{id}/delete")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletePatient(@PathVariable Long id) {
		patientService.deletePatient(id);
	}
	
	@GetMapping(value = "/count", produces = MediaType.APPLICATION_JSON_VALUE)
	public Long countPatients() {
		return patientService.countPatients();
	}
	
	@GetMapping(value = "/byDoctor", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<PatientDto> getPatientsByDoctor(@RequestParam Long doctorId) {
		return patientTransformer.toDtoList(patientService.getPatientsByDoctor(doctorId));
	}

}
