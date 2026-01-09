package lk.acpt.demo.service;
import lk.acpt.demo.dto.EmployeeDto;
import lk.acpt.demo.dto.SaveEmployeeRequestDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface EmployeeService {
    EmployeeDto saveEmployee(SaveEmployeeRequestDto employeeDto) throws IOException;

    boolean deleteEmployee(int id);

    List<EmployeeDto> getAllEmployee();

    EmployeeDto searchEmployee(int id);

    boolean updateEmployee(SaveEmployeeRequestDto employeeDto) throws IOException;

    EmployeeDto searchEmployeeByNic(String nic);
}
