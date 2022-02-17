import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

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
        String expected = "Name: Adam Ant, Age: 1, Salary: 100.0";
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
        boolean raisedSalaryByTenPercentage = true;
        double expected = 110;

        for(int j = 0; j < employeeSystemObject.employeeList.size();j++) {
            if(employeeSystemObject.employeeList.get(j).getSalary() != expected) {
                raisedSalaryByTenPercentage = false;
            }

            assertTrue(raisedSalaryByTenPercentage);
        }
    }

    @ParameterizedTest
    @ValueSource (doubles = {-1,-10,-100.5,-150000,-999.98})

    public void checkRaiseSalaryByNegativeValue(double input) {

        Employee newEmployee = new Employee("A B",30, 100);
        employeeSystemObject.addNewEmployee(newEmployee);

        employeeSystemObject.raiseSalary(input);
        double expected = 100;
        double actual = employeeSystemObject.employeeList.get(0).getSalary();

        assertEquals(expected,actual);
    }

    @ParameterizedTest
    @ValueSource (doubles = {100.1,1000,9999999.999,115,Double.MAX_VALUE})

    public void checkRiseSalaryByMoreThanHundredPercentage(double input) {
        for(int i = 0; i<10;i++) {
            Employee newEmployee = new Employee("Adam Ant",i, 100);
            employeeSystemObject.addNewEmployee(newEmployee);
        }

        employeeSystemObject.raiseSalary(input);
        boolean salaryIsRaised = false;

        for(int j = 0; j < employeeSystemObject.employeeList.size();j++) {
            if(employeeSystemObject.employeeList.get(j).getSalary() != 100) {
                salaryIsRaised = true;
            }
        }

        assertFalse(salaryIsRaised);
    }

    @ParameterizedTest Test
    @ValueSource (doubles = {1,10,10.1,50,99})
    public void checkRaiseSalaryIndividualEmployee(double input) {

        for(int i = 0; i<10;i++) {
            Employee newEmployee = new Employee("Adam Ant",i, 100);
            employeeSystemObject.addNewEmployee(newEmployee);
        }
        employeeSystemObject.raiseIndividualSalary(10);

    }

}
