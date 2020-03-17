package demo.controllers;

import java.util.List;

import demo.managers.EmployeeManager;
import demo.models.Employee;
import demo.models.exceptions.EmployeeNotFoundException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE, value = "/employees")
class EmployeeController {

    @Autowired
    private EmployeeManager employeeManager;

    @GetMapping("")
    @ApiOperation(value = "Get list of Employees in the System ", response = Iterable.class, tags = "employees")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!") })
    List<Employee> all() {
        return employeeManager.getAll();
    }

    @PostMapping("")
    Employee newEmployee(@RequestBody Employee newEmployee) {
        return employeeManager.create(newEmployee);
    }

    @GetMapping("/{id}")
    Employee getEmployee(@PathVariable Long id) throws EmployeeNotFoundException {
        return employeeManager.getEmployee(id);
    }

    @PutMapping("/{id}")
    Employee replaceOrCreateEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {
        return employeeManager.replaceOrCreateEmployee(id, newEmployee);
    }

    @DeleteMapping("/{id}")
    void deleteEmployee(@PathVariable Long id) {
        employeeManager.removeEmployee(id);
    }
}