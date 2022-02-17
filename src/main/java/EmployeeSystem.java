import java.util.ArrayList;

public class EmployeeSystem {

    ArrayList<Employee> employeeList = new ArrayList<>();


    public void addNewEmployee(Employee newEmployee) {
        employeeList.add(newEmployee);
    }

    public void removeEmployee(int id) {
        for (int i = 0; i < employeeList.size(); i++) {
            if (employeeList.get(i).getId() == id) {
                employeeList.remove(i);
                break;
            }
        }
    }

    public void raiseSalary(double salaryIncreasePercentage) {

        if (salaryIncreasePercentage > 0 && salaryIncreasePercentage <= 100) {
            for (int i = 0; i < employeeList.size(); i++) {
                double newSalary = employeeList.get(i).getSalary() + (employeeList.get(i).getSalary() * (salaryIncreasePercentage / 100));
                employeeList.get(i).setSalary(newSalary);
            }
        }
    }

    public void raiseIndividualSalary(double salaryIncreasePercentage, int employeeId) {

        if (salaryIncreasePercentage <= 100 && salaryIncreasePercentage > 0) {
            for (int i = 0; i < employeeList.size(); i++) {
                if (employeeList.get(i).getId() == employeeId) {
                    double newSalary = employeeList.get(i).getSalary() + (employeeList.get(i).getSalary() * (salaryIncreasePercentage / 100));
                    employeeList.get(i).setSalary(newSalary);
                }
            }
        }
    }
}
