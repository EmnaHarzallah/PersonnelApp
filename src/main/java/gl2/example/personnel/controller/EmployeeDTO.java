package gl2.example.personnel.controller;

public class EmployeeDTO {
    private String name;
    private String position;
    private int departmentId;

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }
}
