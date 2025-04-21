package gl2.example.personnel.service;

import gl2.example.personnel.model.Employee;
import gl2.example.personnel.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee with ID " + id + " not found."));
    }

    public Employee addEmployee(Employee employee) {
        try {
            return employeeRepository.save(employee);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Database constraint error: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new RuntimeException("Internal server error while adding the employee", e);
        }
    }

    public void deleteEmployee(Long id) {
        if (!employeeRepository.existsById(id)) {
            throw new RuntimeException("Employee with ID " + id + " not found.");
        }
        employeeRepository.deleteById(id);
    }

    public Employee updateEmployee(Employee employee) {
        // Check if the employee exists before saving
        Optional<Employee> existingEmployee = employeeRepository.findById(employee.getId());
        if (existingEmployee.isEmpty()) {
            throw new RuntimeException("Employee with ID " + employee.getId() + " not found.");
        }
        return employeeRepository.save(employee);
    }
}
