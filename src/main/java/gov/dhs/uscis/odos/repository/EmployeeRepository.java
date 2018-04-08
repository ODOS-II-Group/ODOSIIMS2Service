package gov.dhs.uscis.odos.repository;

import gov.dhs.uscis.odos.domain.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

	Employee findByLastName(String lastName);
	
}
