package com.ed.std.engine.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ed.std.engine.constants.StudentConstants;
import com.ed.std.engine.dtos.ErrorResponseDto;
import com.ed.std.engine.dtos.ResponseDto;
import com.ed.std.engine.dtos.StudentPersonalInfoDto;
import com.ed.std.engine.dtos.studentContactInfoDto;
import com.ed.std.engine.services.iStudentInfoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;


@RestController

@Validated
@RequestMapping(path="/api/v1/student" ,  produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "CRUD REST APIs for Student in Education Automate Engine project",
description = "CRUD REST APIs in  Education Automate Engine to CREATE,UPDATE,FETCH,DELETE Student Details")
public class StudentInformationController {
	
	@Value("${build.version}")
    private String buildVersion;
	

	private final iStudentInfoService studentInfoService;
	
	public StudentInformationController(iStudentInfoService studentInfoService) {
		this.studentInfoService=studentInfoService;
	}
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private studentContactInfoDto contactinfoDto;
	

	@Operation(summary = "Create new Student REST API",
			description = "REST API to create new Student inside Student Microservice Engine")
	@ApiResponses({
		@ApiResponse(responseCode = "201",
				description = "HTTP Status Created"
		),
		@ApiResponse(responseCode = "500", description = "Internal Server Error",
		content=@Content(schema=@Schema(implementation = ErrorResponseDto.class))
		)
	})
	
	@PostMapping( "/add")
	public ResponseEntity<ResponseDto> addNewStudentInfo(@Valid @RequestBody StudentPersonalInfoDto stdInfo) {
		studentInfoService.createNewStudent(stdInfo);
		return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(StudentConstants.STATUS_201,StudentConstants.MESSAGE_201));
	}

	
	@Operation(summary = "Find Student REST API",
			description = "REST API to search Student based on register mobile number")
	
	@ApiResponses({
			@ApiResponse(responseCode = "302",
					description = "HTTP Status Student Found"),
			@ApiResponse(responseCode = "500",
				description = "Internal Server Error",
				content=@Content(schema = @Schema(implementation = ErrorResponseDto.class)))
			})
	@GetMapping("/view/{contactNumber}") 
	public ResponseEntity<StudentPersonalInfoDto> findStudentInfoByContact(@PathVariable(name ="contactNumber" )
			 @Pattern(regexp = "^$|[0-9]{10}",message = "Mobile number must be 10 digit")
			String contactNumber) {
		
		StudentPersonalInfoDto studentDto=studentInfoService.findStudentByContact(String.valueOf(contactNumber));
		
		return ResponseEntity.status(HttpStatus.FOUND).body(studentDto);
	}
	
	@Operation(summary = "Delete Student REST API",
			description = "REST API to delete Student record based on register mobile number")
	
	@ApiResponses({
			@ApiResponse(responseCode = "200",
					description = "HTTP Status OK"),
			@ApiResponse(responseCode = "404",
					description = "Expectation Failed "),
			@ApiResponse(responseCode = "500",
				description = "Internal Server Error",
				content=@Content(schema = @Schema(implementation = ErrorResponseDto.class)))
			})
	@DeleteMapping("/delete")
	public ResponseEntity<ResponseDto> deleteStudentByStudentContact(@RequestParam(name = "contactNumber")
	 	@Pattern(regexp = "^$|[0-9]{10}",message = "Mobile number must be 10 digit")
		String contactNumber
			){
		boolean isDeleted=studentInfoService.deleteStudentByContact(contactNumber);
		return isDeleted?
				ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseDto(StudentConstants.STATUS_200, StudentConstants.MESSAGE_200))
				:
				ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(new ResponseDto(StudentConstants.MESSAGE_417_DELETE, StudentConstants.MESSAGE_417_DELETE));
	}
	
	
	@Operation(summary = "Update Student REST API",
			description = "REST API to update Student record based on register mobile number")
	
	@ApiResponses({
			@ApiResponse(responseCode = "200",
					description = "HTTP Status OK"),
			@ApiResponse(responseCode = "404",
					description = "Expectation Failed "),
			@ApiResponse(responseCode = "500",
				description = "Internal Server Error",
				content=@Content(schema = @Schema(implementation = ErrorResponseDto.class)))
			})
	
	@PutMapping("/update")
	public ResponseEntity<ResponseDto> updateStudentInfo(@RequestBody @Valid StudentPersonalInfoDto studentDto){
		
		boolean isUpdated=studentInfoService.updateStudentDetail(studentDto);
		
		return isUpdated?
				ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseDto(StudentConstants.STATUS_200, StudentConstants.MESSAGE_200))
				:
				ResponseEntity.status(HttpStatus.ACCEPTED)
				.body(new ResponseDto(StudentConstants.MESSAGE_417_UPDATE, StudentConstants.MESSAGE_417_UPDATE));
	}
	
	
	
	@Operation(summary="Build Version Info",
			description = "Fetch current version of build using get api")
	
	@ApiResponses({
		@ApiResponse(responseCode = "200" , description = "HTTP Status OK "),
		@ApiResponse(responseCode ="500" ,description = "Internal Server error",
		content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
	})
	
	@GetMapping("/version-info")
	public ResponseEntity<String> getBuildVersionInfo(){
		return ResponseEntity.status(HttpStatus.OK).body(buildVersion);
	}
	
	
	  @Operation(
	            summary = "Get Java version",
	            description = "Get Java versions details that is installed into accounts microservice"
	    )
	    @ApiResponses({
	            @ApiResponse(
	                    responseCode = "200",
	                    description = "HTTP Status OK"
	            ),
	            @ApiResponse(
	                    responseCode = "500",
	                    description = "HTTP Status Internal Server Error",
	                    content = @Content(
	                            schema = @Schema(implementation = ErrorResponseDto.class)
	                    )
	            )
	    }
	    )
	    @GetMapping("/java-version")
	    public ResponseEntity<String> getJavaVersion(){
	        return ResponseEntity.status(HttpStatus.OK).body(environment.getProperty("JAVA_HOME"));
	    }
	  
	  	
	  @Operation(
	            summary = "Get Contact Info",
	            description = "Contact Info details that can be reached out in case of any issues"
	    )
	    @ApiResponses({
	            @ApiResponse(
	                    responseCode = "200",
	                    description = "HTTP Status OK"
	            ),
	            @ApiResponse(
	                    responseCode = "500",
	                    description = "HTTP Status Internal Server Error",
	                    content = @Content(
	                            schema = @Schema(implementation = ErrorResponseDto.class)
	                    )
	            )
	    }
	    )
	    @GetMapping("/contact-info")
	    public ResponseEntity<studentContactInfoDto> getContactInfo(){
	        return ResponseEntity.status(HttpStatus.OK).body(contactinfoDto);
	    }
	  	
		
}
