package lk.acpt.demo.controller;
import lk.acpt.demo.dto.EmployeeDto;
import lk.acpt.demo.dto.SaveEmployeeRequestDto;
import lk.acpt.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @PostMapping(value = "/save", consumes = "multipart/form-data")
    public ResponseEntity<EmployeeDto> saveEmployee(@ModelAttribute SaveEmployeeRequestDto employeeDto) throws IOException {
        EmployeeDto saveEmployee = employeeService.saveEmployee(employeeDto);
        return new ResponseEntity<>(saveEmployee, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable int id) {
        boolean deleteEmployee = employeeService.deleteEmployee(id);
        if (deleteEmployee) {
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<EmployeeDto>> getAllEmployees() {
        List<EmployeeDto> employees = employeeService.getAllEmployee();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/getNic/{nic}")
    public ResponseEntity<EmployeeDto> searchEmployeeByNic(@PathVariable String nic) {
        EmployeeDto employee = employeeService.searchEmployeeByNic(nic);

        if (employee != null) {
            return new ResponseEntity<>(employee, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/update/{id}", consumes = "multipart/form-data")
    public ResponseEntity<Void> updateEmployee(@PathVariable int id, @ModelAttribute SaveEmployeeRequestDto employeeDto) throws IOException {
        employeeDto.setId(id);

        boolean updated = employeeService.updateEmployee(employeeDto);
        if (updated) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}