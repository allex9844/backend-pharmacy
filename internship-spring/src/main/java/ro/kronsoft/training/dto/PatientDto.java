package ro.kronsoft.training.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import ro.kronsoft.training.validation.PatientValidation;

@PatientValidation
public class PatientDto extends BaseDto {
	private static final long serialVersionUID = -8844598191205679567L;

	@NotNull
	@Size(min = 0, max = 64)
	private String firstName;

	@NotNull
	@Size(min = 0, max = 64)
	private String lastName;

	@NotNull
	@Size(min = 0, max = 32)
	@Pattern(regexp = "[0-9]+")
	private String phoneNumber;
	
	private String birthday;

	@NotNull
	@Positive
	private Long doctorId;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public Long getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Long doctorId) {
		this.doctorId = doctorId;
	}

	@Override
	public String toString() {
		return "PatientDto [firstName=" + firstName + ", lastName=" + lastName + ", phoneNumber=" + phoneNumber
				+ ", doctorId=" + doctorId + ", id=" + id + "]";
	}

}
