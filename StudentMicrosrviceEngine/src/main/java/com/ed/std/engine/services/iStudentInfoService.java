package com.ed.std.engine.services;

import com.ed.std.engine.dtos.StudentPersonalInfoDto;

public interface iStudentInfoService {
	
	/**
	 * Create New Student Into Student DB
	 * @param studentDto
	 */
	void createNewStudent(StudentPersonalInfoDto studentDto);
	
	/**
	 * Fetch Student Record based on contact Number
	 * @param contact
	 * @return StudentPersonalInfoDto
	 * 
	 */
	StudentPersonalInfoDto findStudentByContact(String contact);
	
	/**
	 * Deleting student personal record by mobile number
	 * @param contact
	 * @return boolean
	 */
	boolean deleteStudentByContact(String contact);
	
	/**
	 * Update Student info and return flag 
	 * @param studentInfo
	 * @return boolean
	 */
	boolean updateStudentDetail(StudentPersonalInfoDto studentInfo);

}
