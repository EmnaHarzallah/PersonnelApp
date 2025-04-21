package gl2.example.personnel.model;

import jakarta.persistence.*;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String position;
    private double salary;

    @ManyToOne
    @JoinColumn(name = "departement_id", nullable = false)
    private Departement departement;


    public Employee(String name, String position, double salary, Departement departement) {
        this.name = name;
        this.position = position;
        this.salary = salary;
        this.departement = departement;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Departement getDepartement() {
        return departement;
    }

    public void setDepartement(Departement departement) {
        this.departement = departement;
    }

    @Override
    public String toString() {
        return "Employee [id=" + id + ", name=" + name + ", position=" + position +
                ", salary=" + salary + ", departement=" +
                (departement != null ? departement.getName() : "null") + "]";
    }
}
