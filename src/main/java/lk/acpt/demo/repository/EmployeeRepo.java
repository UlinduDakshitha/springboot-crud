package lk.acpt.demo.repository;

import lk.acpt.demo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepo extends JpaRepository<Employee,Integer> {
    Optional<Employee> findByNic(String nic);

}