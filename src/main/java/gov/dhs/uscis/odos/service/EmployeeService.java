package gov.dhs.uscis.odos.service;

import java.util.List;

import gov.dhs.uscis.odos.dto.EmployeeDTO;

public interface EmployeeService {

	List<EmployeeDTO> getEmployeeList();
	
	void saveEmployee(EmployeeDTO emp);
	
}
