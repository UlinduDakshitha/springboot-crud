package lk.acpt.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class EmployeeDto {
    private int id;
    private String name;
    private String nic;
    private int age;
    private double salary;
    private String avatarPath;

}
