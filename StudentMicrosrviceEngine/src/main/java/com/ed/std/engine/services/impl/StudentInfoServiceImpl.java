package com.ed.std.engine.services.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ed.std.engine.dtos.StudentPersonalInfoDto;
import com.ed.std.engine.entity.StudentPersonalInfo;
import com.ed.std.engine.exception.StudentAlreadyExistException;
import com.ed.std.engine.exception.StudentNotFoundException;
import com.ed.std.engine.mapper.StudentPersonalInfoMapper;
import com.ed.std.engine.repository.StudentPersonalInfoRepository;
import com.ed.std.engine.services.iStudentInfoService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class StudentInfoServiceImpl implements iStudentInfoService {
	
	
	StudentPersonalInfoRepository studentPersonalInfoRepository;

	/**
	 * Create New Student Into Student DB
	 * @param studentDto
	 */
	@Override
	public void createNewStudent(StudentPersonalInfoDto studentDto) {
		
		StudentPersonalInfo studentInfo=StudentPersonalInfoMapper.toStudentPersonalInfo(studentDto, new StudentPersonalInfo());
		Optional<StudentPersonalInfo> checkStudentInfo=studentPersonalInfoRepository.findByMobileNumber(studentInfo.getMobileNumber());
		
		checkStudentInfo.ifPresentOrElse(s->{
			throw new StudentAlreadyExistException("Contact number already register, Student Already Existed");
		},
				()->studentPersonalInfoRepository.save(studentInfo));	
	}

	@Override
	public StudentPersonalInfoDto findStudentByContact(String contact) {
	
		StudentPersonalInfo studentInfo=studentPersonalInfoRepository.findByMobileNumber(contact)
				.orElseThrow(()-> new StudentNotFoundException("Student not found with requested contact number"));
		StudentPersonalInfoDto studentPersonalInfoDto=StudentPersonalInfoMapper.toStudentPersonalInfoDto(studentInfo, new StudentPersonalInfoDto());
		return studentPersonalInfoDto;
	}

	@Override
	public boolean deleteStudentByContact(String contact) {

		 StudentPersonalInfo studentInfo=studentPersonalInfoRepository.findByMobileNumber(contact)
				 .orElseThrow(()->new StudentNotFoundException("No student register with contact number "+contact));
		
		 studentPersonalInfoRepository.deleteById(studentInfo.getId());
		return true;
	}

	@Override
	public boolean updateStudentDetail(StudentPersonalInfoDto studentInfoDto) {
		
		StudentPersonalInfo studentInfo=studentPersonalInfoRepository.findByMobileNumber(studentInfoDto.getMobileNumber())
		.orElseThrow(()->new StudentNotFoundException("Student not found with register contact"+studentInfoDto.getMobileNumber()));
		
		long studentId=studentInfo.getId();
		studentInfo=StudentPersonalInfoMapper.toStudentPersonalInfo(studentInfoDto, studentInfo);
		studentInfo.setId(studentId);
		
		studentPersonalInfoRepository.save(studentInfo);
		
		return true;
	}

}
