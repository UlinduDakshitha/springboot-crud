package lk.acpt.demo.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Employee {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String nic;
    private int age;
    private double salary;
    private String avatarPath;

    public Employee(String name, String nic, int age, double salary, String avatarPath) {
        this.name = name;
        this.nic = nic;
        this.age = age;
        this.salary = salary;
        this.avatarPath=avatarPath;
    }
}
