import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EmployeeSystemTest {

    EmployeeSystem employeeSystemObject;

    @BeforeEach
    public void setUpEmployeeSystem() {

        employeeSystemObject = new EmployeeSystem();
    }
    @Test
    public void checkCreateEmployeeSystem() {
        EmployeeSystem testSystem;
        testSystem = new EmployeeSystem();

        assertInstanceOf(EmployeeSystem.class,testSystem);
    }

    @Test
    public void checkAddEmployeeToEmployeeList() {
        Employee newEmployee = new Employee("Adam Ant", 1, 100);
        employeeSystemObject.addNewEmployee(newEmployee);
        String expected = "Name: Adam Ant, Age: 1, Salary: 100.0, ID: 1";
        String actual = employeeSystemObject.employeeList.get(0).toString();

        assertEquals(expected, actual);
    }
    @Test
    public void checkUniqueEmployeeId() {
        for(int i = 0; i<10;i++) {
            Employee newEmployee = new Employee("Adam Ant",i, 100);
            employeeSystemObject.addNewEmployee(newEmployee);
        }

        boolean idIsNotUnique = false;
        for (int i = 0; i < employeeSystemObject.employeeList.size() - 1; i++) {
            int checkId = employeeSystemObject.employeeList.get(i).getId();
            for (int j = i + 1; j < employeeSystemObject.employeeList.size(); j++) {
                if (checkId == employeeSystemObject.employeeList.get(j).getId()) {
                    idIsNotUnique = true;
                    break;
                }
            }
        }
        assertFalse(idIsNotUnique);
    }

    @Test
    public void checkRemoveEmployee() {
        for(int i = 0; i<10;i++) {
            Employee newEmployee = new Employee("Adam Ant",i, 100);
            employeeSystemObject.addNewEmployee(newEmployee);
        }

        employeeSystemObject.removeEmployee(5);
        boolean iDFiveExists = false;
        for(int i = 0; i<employeeSystemObject.employeeList.size();i++) {
            if(employeeSystemObject.employeeList.get(i).getId() == 5) {
                iDFiveExists = true;
                break;
            }
        }
        assertFalse(iDFiveExists);
    }

    @Test
    public void checkRaiseSalary() {
        for(int i = 0; i<10;i++) {
            Employee newEmployee = new Employee("Adam Ant",i, 100);
            employeeSystemObject.addNewEmployee(newEmployee);
        }

        employeeSystemObject.raiseSalary(10);
    }

}
