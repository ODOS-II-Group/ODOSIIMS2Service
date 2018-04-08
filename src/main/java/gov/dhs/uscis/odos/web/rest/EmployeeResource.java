package gov.dhs.uscis.odos.web.rest;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;

import gov.dhs.uscis.odos.dto.EmployeeDTO;
import gov.dhs.uscis.odos.service.EmployeeService;

/**
 * Controller for retrieving and saving employee information
 */
@RestController
@RequestMapping("/employee")
public class EmployeeResource {

	@Inject
	EmployeeService employeeService;
	
    @GetMapping("/getAll")
    @Timed
    public List<EmployeeDTO> getList() {
        return employeeService.getEmployeeList();
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Timed
    public void saveEmployee(@RequestBody EmployeeDTO empDTO) {
        employeeService.saveEmployee(empDTO);
    }
}
