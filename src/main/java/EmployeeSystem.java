import java.util.ArrayList;

public class EmployeeSystem {

    ArrayList<Employee> employeeList = new ArrayList<>();


    public void addNewEmployee(Employee newEmployee) {
        employeeList.add(newEmployee);
    }

    public void removeEmployee(int id) {
        for(int i = 0; i < employeeList.size();i++) {
            if(employeeList.get(i).getId() == id) {
                employeeList.remove(i);
                break;
            }
        }
    }

    public void raiseSalary(double i) {
        for(int j = 0; j < employeeList.size();j++){
            double newSalary = employeeList.get(j).getSalary() + (employeeList.get(j).getSalary() * (i/100));
            employeeList.get(j).setSalary(newSalary);
        }
    }
}
