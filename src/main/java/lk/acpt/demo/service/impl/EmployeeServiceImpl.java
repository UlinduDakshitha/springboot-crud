package lk.acpt.demo.service.impl;
import lk.acpt.demo.dto.EmployeeDto;
import lk.acpt.demo.dto.SaveEmployeeRequestDto;
import lk.acpt.demo.entity.Employee;
import lk.acpt.demo.repository.EmployeeRepo;
import lk.acpt.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepo employeeRepo;


    @Override
    public EmployeeDto saveEmployee(SaveEmployeeRequestDto employeeDto) throws IOException {
        String imagePath = null;
        if (employeeDto.getAvatarFile() != null && !employeeDto.getAvatarFile().isEmpty()) {
            imagePath = saveImage(employeeDto.getAvatarFile());
        }

        Employee save = employeeRepo.save(new Employee(employeeDto.getName(), employeeDto.getNic(), employeeDto.getAge(), employeeDto.getSalary(), imagePath));
        return new EmployeeDto(save.getId(), save.getName(), save.getNic(), save.getAge(), save.getSalary(), save.getAvatarPath());
    }

    @Override
    public boolean deleteEmployee(int id) {
        if (employeeRepo.existsById(id)) {
            employeeRepo.deleteById(id);
            return true;
        }
        return false;
    }


    @Override
    public List<EmployeeDto> getAllEmployee() {
        return employeeRepo.findAll()
                .stream()
                .map(e -> new EmployeeDto(
                        e.getId(),
                        e.getName(),
                        e.getNic(),
                        e.getAge(),
                        e.getSalary(),
                        e.getAvatarPath()
                ))
                .toList();
    }


    @Override
    public EmployeeDto searchEmployee(int id) {
        Optional<Employee> byId = employeeRepo.findById(id);

        if (byId.isPresent()) {
            Employee employee = byId.get();
            return new EmployeeDto(employee.getId(), employee.getName(), employee.getNic(), employee.getAge(), employee.getSalary(),employee.getAvatarPath());
        }
        return null;
    }

    @Override
    public boolean updateEmployee(SaveEmployeeRequestDto employeeDto) throws IOException {
        Optional<Employee> optionalEmployee = employeeRepo.findById(employeeDto.getId());

        if (optionalEmployee.isPresent()) {
            Employee employee = optionalEmployee.get();
            employee.setName(employeeDto.getName());
            employee.setNic(employeeDto.getNic());
            employee.setAge(employeeDto.getAge());
            employee.setSalary(employeeDto.getSalary());

            if (employeeDto.getAvatarFile() != null && !employeeDto.getAvatarFile().isEmpty()) {
                String imagePath = saveImage(employeeDto.getAvatarFile());
                employee.setAvatarPath(imagePath);
            }

            employeeRepo.save(employee);
            return true;
        }
        return false;
    }

    @Override
    public EmployeeDto searchEmployeeByNic(String nic) {
        Optional<Employee> employee = employeeRepo.findByNic(nic);

        if (employee.isPresent()) {
            Employee e = employee.get();
            return new EmployeeDto(e.getId(), e.getName(), e.getNic(), e.getAge(), e.getSalary(),e.getAvatarPath());
        }
        return null;
    }

    private String saveImage(MultipartFile file) throws IOException {
        byte[] imageBytes = file.getBytes();
        String imageName = file.getOriginalFilename();

        ByteArrayInputStream bis = new ByteArrayInputStream(imageBytes);
        BufferedImage bufferedImage = ImageIO.read(bis);

        if (bufferedImage == null) {
            throw new IOException("Could not decode image format. Ensure it's a valid image (e.g., PNG, JPEG).");
        }

        bis.close();

        File outputFile = new File("src/main/resources/uploads/" + imageName);

        outputFile.getParentFile().mkdirs();

        boolean success = ImageIO.write(bufferedImage, "png", outputFile);

        if (!success) {
            throw new IOException("Failed to write image to file.");
        }

        String imagePath = outputFile.getAbsolutePath();
        System.out.println("Image successfully saved to: " + imagePath);
        return imagePath;
    }
}
