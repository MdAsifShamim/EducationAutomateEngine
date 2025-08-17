package com.ed.std.engine.mapper;

import com.ed.std.engine.dtos.StudentPersonalInfoDto;
import com.ed.std.engine.entity.StudentPersonalInfo;

public class StudentPersonalInfoMapper {
	
	/**
	 * Convert Student Data Transfer Object to JPA Entity POJO object
	 * @param stdDto 
	 * @param stdInfo
	 * @return StudentPersonalInfo
	 */
	
	public static StudentPersonalInfo toStudentPersonalInfo(StudentPersonalInfoDto stdDto,StudentPersonalInfo stdInfo) {
		
		stdInfo.setFirstName(stdDto.getFirstName());
		stdInfo.setLastName(stdDto.getLastName());
		stdInfo.setEmail(stdDto.getEmail());
		stdInfo.setMobileNumber(stdDto.getMobileNumber());
		stdInfo.setAddress(stdDto.getAddress());
		stdInfo.setDateOfBirth(stdDto.getDateOfBirth());
		
		return stdInfo;
	}
	
	/**
	 *  Convert Student JPA Entity  object to Data Transfer Object
	 * @param stdInfo
	 * @param stdDto
	 * @return StudentPersonalInfoDto
	 */
	
	public static StudentPersonalInfoDto toStudentPersonalInfoDto(StudentPersonalInfo stdInfo,StudentPersonalInfoDto stdDto) {
		
		stdDto.setFirstName(stdInfo.getFirstName());
		stdDto.setLastName(stdInfo.getLastName());
		stdDto.setEmail(stdInfo.getEmail());
		stdDto.setMobileNumber(stdInfo.getMobileNumber());
		stdDto.setAddress(stdInfo.getAddress());
		stdDto.setDateOfBirth(stdInfo.getDateOfBirth());
		
		return stdDto;
	}

}