package gov.dhs.uscis.odos.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ActiveProfiles;

import gov.dhs.uscis.odos.domain.Employee;
import gov.dhs.uscis.odos.dto.EmployeeDTO;
import gov.dhs.uscis.odos.repository.EmployeeRepository;
import gov.dhs.uscis.odos.service.impl.EmployeeServiceImpl;

@RunWith(MockitoJUnitRunner.class)
@ActiveProfiles("no-liquibase")
public class EmployeeServiceImplTest {

	@InjectMocks
	EmployeeServiceImpl employeeService;
	
	@Mock
	EmployeeRepository employeeRepository;
	
	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	
	@Test
	public void testGetEmployeeListEmpty() {
		List<Employee> employees = new ArrayList<Employee>();
		when(employeeRepository.findAll()).thenReturn(employees);
		
		List<EmployeeDTO> employeeList = employeeService.getEmployeeList();
		
		assertNotNull(employeeList);
		assertEquals(employeeList.size(), 0);
		
		verify(employeeRepository).findAll();
	}
	
	@Test
	public void testGetEmployeeList() throws ParseException {
		List<Employee> employees = new ArrayList<Employee>();
		employees.add(new Employee(1, "John", "Smith", DateUtils.parseDate("01/01/1990", "MM/dd/yyyy")));
		employees.add(new Employee(2, "Jane", "Smith", null));
		
		when(employeeRepository.findAll()).thenReturn(employees);
		
		List<EmployeeDTO> employeeList = employeeService.getEmployeeList();
		
		assertNotNull(employeeList);
		assertEquals(employeeList.size(), 2);
		
		verify(employeeRepository).findAll();
	}
	
	@Test
	public void testSaveEmployeeEmptyFirstName() {
		EmployeeDTO dto = new EmployeeDTO();
		expectedException.expect(NullPointerException.class);
	    expectedException.expectMessage("First Name cannot be empty");
	    employeeService.saveEmployee(dto);
	    
	    dto.setFirstName("");
	    expectedException.expect(IllegalArgumentException.class);
	    expectedException.expectMessage("First Name cannot be empty");
	    employeeService.saveEmployee(dto);
	}
	
	@Test
	public void testSaveEmployeeEmptyLastName() {
		EmployeeDTO dto = new EmployeeDTO();
		dto.setFirstName("Test");
		expectedException.expect(NullPointerException.class);
	    expectedException.expectMessage("Last Name cannot be empty");
	    employeeService.saveEmployee(dto);
	    
	    dto.setLastName("");
	    expectedException.expect(IllegalArgumentException.class);
	    expectedException.expectMessage("Last Name cannot be empty");
	    employeeService.saveEmployee(dto);
	}
	
	@Test
	public void testSaveEmployeeEmptyDateOfBirth() {
		EmployeeDTO dto = new EmployeeDTO();
		dto.setFirstName("Test");
		dto.setLastName("User");
		
		expectedException.expect(NullPointerException.class);
	    expectedException.expectMessage("Date of Birth cannot be empty");
	    employeeService.saveEmployee(dto);
	    
	    dto.setDateOfBirth("");
	    expectedException.expect(IllegalArgumentException.class);
	    expectedException.expectMessage("Date of Birth cannot be empty");
	    employeeService.saveEmployee(dto);
	}
	
	@Test
	public void testSaveEmployeeNew() {
		EmployeeDTO dto = new EmployeeDTO(0, "Test", "User", "01/01/1990");

		when(employeeRepository.findOne(0)).thenReturn(null);
		
	    employeeService.saveEmployee(dto);
	    
	    verify(employeeRepository).findOne(0);
	}

	@Test
	public void testSaveEmployeeExisting() {
		EmployeeDTO dto = new EmployeeDTO(1, "Test", "User", "01/01/1990");
		
		Employee emp = new Employee();
		emp.setId(1);
		when(employeeRepository.findOne(1)).thenReturn(emp);
		
	    employeeService.saveEmployee(dto);
	    
	    verify(employeeRepository).findOne(1);
	}
	
	@Test
	public void testSaveEmployeeIncorrectDate() {
		EmployeeDTO dto = new EmployeeDTO(0, "Test", "User", "01/01");

		when(employeeRepository.findOne(0)).thenReturn(null);
		
		expectedException.expect(RuntimeException.class);
	    expectedException.expectMessage("Date of Birth cannot be parsed into a date. Please ensure it is in MM/DD/YYYY format.");
	    employeeService.saveEmployee(dto);
	    
	    verify(employeeRepository).findOne(0);
	}
}
