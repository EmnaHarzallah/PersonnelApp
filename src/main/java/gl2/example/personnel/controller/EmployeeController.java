package gl2.example.personnel.controller;



import gl2.example.personnel.model.Departement;
import gl2.example.personnel.model.Employee;
import gl2.example.personnel.repository.DepartementRepository;
import gl2.example.personnel.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.springframework.util.ReflectionUtils;



import java.util.Map;
import java.lang.reflect.Field;


@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private DepartementRepository departementRepository;

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        Optional<Employee> employee = employeeService.getEmployeeById(id);
        if (employee.isPresent()) {
            return ResponseEntity.ok(employee.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping
    public ResponseEntity<?> addEmployee(@RequestBody EmployeeDTO dto) {
        Optional<Departement> deptOpt = departementRepository.findById(dto.getDepartmentId());

        if (deptOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Département avec l'ID " + dto.getDepartmentId() + " introuvable.");
        }

        Departement departement = deptOpt.get();

        Employee emp = new Employee();
        emp.setName(dto.getName());
        emp.setPosition(dto.getPosition());
        emp.setDepartement(departement);

        //  Ajout bidirectionnel
        departement.getEmployees().add(emp);
        departementRepository.save(departement); // Save the department with the newly added employee


        Employee saved = employeeService.saveEmployee(emp);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }


    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(
            @PathVariable Long id,
            @RequestBody Employee updatedEmployee) {

        Optional<Employee> existingEmployee = employeeService.getEmployeeById(id);
        if (existingEmployee.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        // S'assurer que l'ID de l'objet correspond à l'ID fourni dans l'URL
        updatedEmployee.setId(id);
        Employee savedEmployee = employeeService.updateEmployee(updatedEmployee);

        return ResponseEntity.ok(savedEmployee);
    }




    @PatchMapping("/{id}")
    public ResponseEntity<Employee> updateEmployeePartially(
            @PathVariable Long id,
            @RequestBody Map<String, Object> updates) {

        Optional<Employee> existingEmployee = employeeService.getEmployeeById(id);
        if (existingEmployee.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Employee employee = existingEmployee.get();

        updates.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Employee.class, key);
            if (field != null) {
                field.setAccessible(true);
                ReflectionUtils.setField(field, employee, value);
            }
        });

        Employee updatedEmployee = employeeService.updateEmployee(employee);
        return ResponseEntity.ok(updatedEmployee);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        // Log de l'exception
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Une erreur interne s'est produite : " + e.getMessage());
    }


}

