import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Collections;

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

        assertInstanceOf(EmployeeSystem.class, testSystem);
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
        for (int i = 0; i < 10; i++) {
            Employee newEmployee = new Employee("Adam Ant", i, 100);
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
        for (int i = 0; i < 10; i++) {
            Employee newEmployee = new Employee("Adam Ant", i, 100);
            employeeSystemObject.addNewEmployee(newEmployee);
        }
        System.out.println(employeeSystemObject.employeeList.get(employeeSystemObject.employeeList.size() - 1).getId());
        int idToRemove = employeeSystemObject.employeeList.get(employeeSystemObject.employeeList.size() - 1).getId();
        employeeSystemObject.removeEmployee(idToRemove);

        boolean iDRemovedExists = false;
        for (int i = 0; i < employeeSystemObject.employeeList.size(); i++) {
            if (employeeSystemObject.employeeList.get(i).getId() == idToRemove) {
                iDRemovedExists = true;
                break;
            }
        }
        assertFalse(iDRemovedExists);
    }

    @Test
    public void checkRaiseSalary() {
        for (int i = 0; i < 10; i++) {
            Employee newEmployee = new Employee("Adam Ant", i, 100);
            employeeSystemObject.addNewEmployee(newEmployee);
        }

        employeeSystemObject.raiseSalary(10);
        boolean raisedSalaryByTenPercentage = true;
        double expected = 110;

        for (int j = 0; j < employeeSystemObject.employeeList.size(); j++) {
            if (employeeSystemObject.employeeList.get(j).getSalary() != expected) {
                raisedSalaryByTenPercentage = false;
            }

            assertTrue(raisedSalaryByTenPercentage);
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {-1, -10, -100.5, -150000, -999.98})

    public void checkRaiseSalaryByNegativeValue(double input) {

        Employee newEmployee = new Employee("A B", 30, 100);
        employeeSystemObject.addNewEmployee(newEmployee);

        employeeSystemObject.raiseSalary(input);
        double expected = 100;
        double actual = employeeSystemObject.employeeList.get(0).getSalary();

        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @ValueSource(doubles = {100.1, 1000, 9999999.999, 115, Double.MAX_VALUE})

    public void checkRiseSalaryByMoreThanHundredPercentage(double input) {
        for (int i = 0; i < 10; i++) {
            Employee newEmployee = new Employee("Adam Ant", i, 100);
            employeeSystemObject.addNewEmployee(newEmployee);
        }

        employeeSystemObject.raiseSalary(input);
        boolean salaryIsRaised = false;

        for (int j = 0; j < employeeSystemObject.employeeList.size(); j++) {
            if (employeeSystemObject.employeeList.get(j).getSalary() != 100) {
                salaryIsRaised = true;
            }
        }

        assertFalse(salaryIsRaised);
    }

    @ParameterizedTest
    @ValueSource(doubles = {1, 10, 10.1, 50, 99})
    public void checkRaiseSalaryIndividualEmployee(double input) {

        Employee newEmployee = new Employee("Adam Ant", 25, 100);
        employeeSystemObject.addNewEmployee(newEmployee);

        employeeSystemObject.raiseIndividualSalary(input, employeeSystemObject.employeeList.get(employeeSystemObject.employeeList.size() - 1).getId());
        double expected = 100 + (100 * (input / 100));
        double actual = employeeSystemObject.employeeList.get(employeeSystemObject.employeeList.size() - 1).getSalary();

        assertEquals(expected, actual);
    }

    @Test
    public void checkRaiseSalaryIndividualEmployeeOverHundredPercentage() {
        Employee newEmployee = new Employee("Adam Ant", 30, 100);
        employeeSystemObject.addNewEmployee(newEmployee);

        employeeSystemObject.raiseIndividualSalary(101, employeeSystemObject.employeeList.get(employeeSystemObject.employeeList.size() - 1).getId());
        double expected = 100;
        double actual = employeeSystemObject.employeeList.get(employeeSystemObject.employeeList.size() - 1).getSalary();

        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @ValueSource(doubles = {-0.1, -10, -15.5, -90.9987, -10000.57})
    public void checkRaiseSalaryIndividualEmployeeNegativeValue(double input) {

        for (int i = 0; i < 10; i++) {
            Employee newEmployee = new Employee("Adam Ant", i, 100);
            employeeSystemObject.addNewEmployee(newEmployee);
            employeeSystemObject.raiseIndividualSalary(input, employeeSystemObject.employeeList.get(i).getId());
        }

        boolean salaryStaySame = true;

        for (int i = 0; i < employeeSystemObject.employeeList.size(); i++) {
            if (employeeSystemObject.employeeList.get(i).getSalary() != 100) {
                salaryStaySame = false;
            }
        }

        assertTrue(salaryStaySame);

    }

    @Test
    public void removeEmployeeAdditionalTests() {

        Employee newEmployee = new Employee("Joakim", 30, 100);
        employeeSystemObject.addNewEmployee(newEmployee);

        employeeSystemObject.removeEmployee(employeeSystemObject.employeeList.get(0).getId());
        assertEquals(Collections.EMPTY_LIST, employeeSystemObject.employeeList);
    }

}
