package gl2.example.personnel.repository;

import gl2.example.personnel.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findByDepartement_Id(int departementId);  // Find employees by their department ID
    List<Employee> findByPosition(String position);  // Find employees by position
}


