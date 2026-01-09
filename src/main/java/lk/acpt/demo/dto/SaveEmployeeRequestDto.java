package lk.acpt.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class SaveEmployeeRequestDto {
    private int id;
    private String name;
    private String nic;
    private int age;
    private double salary;
    private MultipartFile avatarFile;

}
