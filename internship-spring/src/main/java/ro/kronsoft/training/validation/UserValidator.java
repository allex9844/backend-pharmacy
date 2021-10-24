package ro.kronsoft.training.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ro.kronsoft.training.dto.UserDto;
import ro.kronsoft.training.repository.UserRepository;

@Component
public class UserValidator implements ConstraintValidator<UserValidation, UserDto> {
	@Autowired
	private UserRepository userRepository;
		
	@Override
	public boolean isValid(UserDto value, ConstraintValidatorContext context) {
		Long id = value.getId();
		String username = value.getUsername();
		boolean isValid = !(id == null ? userRepository.existsByUsername(username)
				: userRepository.existsByUsernameNotId(username, id));
		if (!isValid) {
			context.buildConstraintViolationWithTemplate(
					String.format("The username=%s is not unique.", username))
					.addPropertyNode("username").addConstraintViolation();
		}
		return isValid;
	}

}
