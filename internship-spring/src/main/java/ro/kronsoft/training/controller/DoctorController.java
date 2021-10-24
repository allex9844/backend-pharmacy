package ro.kronsoft.training.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ro.kronsoft.training.dto.DoctorDto;
import ro.kronsoft.training.service.DoctorService;
import ro.kronsoft.training.transformer.DoctorTransformer;

@RestController
@RequestMapping("/doctor")
public class DoctorController {
	@Autowired
	private DoctorService doctorService;
	@Autowired
	private DoctorTransformer doctorTransformer;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<DoctorDto> getDoctors() {
		return doctorTransformer.toDtoList(doctorService.findDoctors());
	}
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public DoctorDto getDoctorById(@PathVariable Long id) {
		return doctorTransformer.toDto(doctorService.getDoctorById(id));
	}

	@PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public DoctorDto createDoctor(@Validated @RequestBody DoctorDto doctor) {
		return doctorTransformer.toDto(doctorService.saveDoctor(doctorTransformer.toEntity(doctor)));
	}

	@PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateDoctor(@Valid @RequestBody DoctorDto doctor) {
		doctorService.saveDoctor(doctorTransformer.toEntity(doctor));
	}

	@DeleteMapping(value = "/{id}/delete")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteDoctor(@PathVariable Long id) {
		doctorService.deleteDoctor(id);
	}

	@DeleteMapping(value = "/deleteAll")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteDoctors() {
		doctorService.deleteDoctors();
	}
	
}
