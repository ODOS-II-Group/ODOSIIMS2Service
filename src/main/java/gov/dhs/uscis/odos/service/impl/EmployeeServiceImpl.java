package gov.dhs.uscis.odos.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gov.dhs.uscis.odos.domain.Employee;
import gov.dhs.uscis.odos.dto.EmployeeDTO;
import gov.dhs.uscis.odos.repository.EmployeeRepository;
import gov.dhs.uscis.odos.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private static final Logger log = LoggerFactory.getLogger(EmployeeServiceImpl.class);
			
	@Inject
	EmployeeRepository employeeRepository;
	
	@Override
	@Transactional
	public List<EmployeeDTO> getEmployeeList() {
		List<EmployeeDTO> employeeList = new ArrayList<EmployeeDTO>();
		employeeRepository.findAll().forEach(emp -> employeeList.add(convert(emp)));
		return employeeList;
	}
	
	@Override
	@Transactional
	public void saveEmployee(EmployeeDTO empDTO) {
		
		Validate.notBlank(empDTO.getFirstName(), "First Name cannot be empty");
		Validate.notBlank(empDTO.getLastName(), "Last Name cannot be empty");
		Validate.notBlank(empDTO.getDateOfBirth(), "Date of Birth cannot be empty");
		
		Employee emp = employeeRepository.findOne(empDTO.getId());
		if (emp == null) {
			emp = new Employee();
		}
		emp.setFirstName(empDTO.getFirstName().toUpperCase());
		emp.setLastName(empDTO.getLastName().toUpperCase());
		try {
			emp.setDateOfBirth(DateUtils.parseDate(empDTO.getDateOfBirth(), "MM/dd/yyyy"));
		} catch (ParseException e) {
			throw new RuntimeException("Date of Birth cannot be parsed into a date. Please ensure it is in MM/DD/YYYY format.");
		}
		employeeRepository.save(emp);

	}
	
	private EmployeeDTO convert(Employee emp) {
		EmployeeDTO dto = new EmployeeDTO();
		dto.setId(emp.getId());
		dto.setFirstName(emp.getFirstName());
		dto.setLastName(emp.getLastName());
		if (emp.getDateOfBirth() != null) {
			dto.setDateOfBirth(DateFormatUtils.format(emp.getDateOfBirth(), "MM/dd/yyyy"));
		}
		return dto;
	}

}
