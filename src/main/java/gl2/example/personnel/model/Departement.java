package gl2.example.personnel.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Departement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name = "";

    @OneToMany(mappedBy = "departement")
    private List<Employee> employees;

    public Departement() {}

    public Departement(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getters et Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        return "Departement [id=" + id + ", name=" + name + "]";
    }
}
