package gov.dhs.uscis.odos.service;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import gov.dhs.uscis.odos.Odosiims2ServiceApp;
import gov.dhs.uscis.odos.dto.EmployeeDTO;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Odosiims2ServiceApp.class)
@ActiveProfiles("dev, no-liquibase")
public class EmployeeServiceImplIntTest {

	@Inject
	EmployeeService employeeService;
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	@Transactional
	@Rollback
	public void testSaveEmployee() {
		EmployeeDTO dto = new EmployeeDTO();
		dto.setFirstName("Test");
		dto.setLastName("User");
		dto.setDateOfBirth("01/01/1990");
		
		employeeService.saveEmployee(dto);
		
	}

}
