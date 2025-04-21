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

    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    public Employee addEmployee(Employee employee) {
        try {
            return employeeRepository.save(employee);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Erreur de contrainte de base de données", e);
        } catch (Exception e) {
            throw new RuntimeException("Erreur interne serveur lors de l'ajout de l'employé", e);
        }
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
    public Employee updateEmployee(Employee employee) {

        return employeeRepository.save(employee);
    }
}
