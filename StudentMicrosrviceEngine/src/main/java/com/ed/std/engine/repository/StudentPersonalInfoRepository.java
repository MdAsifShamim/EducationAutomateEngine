
package com.ed.std.engine.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.ed.std.engine.entity.StudentPersonalInfo;

/**
 * @author MD ASIF SHAMIM 
 * Created Repository interface to performCRUD operation on Student Personal Data 
 */

public interface StudentPersonalInfoRepository extends JpaRepository<StudentPersonalInfo,Long> {
	
	Optional<StudentPersonalInfo> findByMobileNumber(String mobileNumber);
	
	

}
