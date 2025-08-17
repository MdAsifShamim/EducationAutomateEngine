package com.ed.std.engine.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class StudentPersonalInfoDto {
	
	@NotEmpty(message = "First Name can not be null or empty")
	@Size(min = 2, max=100,message = "The length of the student first name should be between 2 and 100")
	private String firstName;
	
	@NotEmpty(message = "Last Name can not be null or empty")
	@Size(min = 2, max=100,message = "The length of the student last name should be between 2 and 100")
	private String lastName;

	@NotEmpty(message = "Email can not be null or empty")
	@Email(message = "Email address should be a valid value")
	private String email;
	
	@NotEmpty(message = "Email can not be null or empty")
	@Pattern(regexp = "(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
	private String mobileNumber;
	
	@NotEmpty(message = "address can not be null or empty")
	private String address;
	
	@NotEmpty(message = "Date of birth can not be null or empty")
	private String dateOfBirth;
}
