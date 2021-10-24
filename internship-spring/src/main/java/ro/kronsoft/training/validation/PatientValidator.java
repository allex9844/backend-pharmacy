package ro.kronsoft.training.validation;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ro.kronsoft.training.dto.PatientDto;
import ro.kronsoft.training.repository.DoctorRepository;

@Component
public class PatientValidator implements ConstraintValidator<PatientValidation, PatientDto> {
	@Autowired
	private DoctorRepository doctorRepository;
	
	@Override
	public boolean isValid(PatientDto value, ConstraintValidatorContext context) {
		boolean isValid = true;
		Long doctorId = value.getDoctorId();
		if (!doctorRepository.existsById(doctorId)) {
			context.buildConstraintViolationWithTemplate(
					String.format("The doctor with id=%s does not exist.", doctorId)).addPropertyNode("doctorId")
					.addConstraintViolation();
			isValid = false;
		}

		String birthday = value.getBirthday();
		if (birthday != null) {
			try {
				LocalDate.parse(birthday);
			} catch (DateTimeParseException e) {
				context.buildConstraintViolationWithTemplate(
						String.format("The pacient birthday=%s is invalid.", birthday)).addPropertyNode("birthday")
						.addConstraintViolation();
				isValid = false;
			}
		}
		return isValid;
	}

}
