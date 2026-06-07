package streams;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SortEmp_23 {
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
                new Employee("Ali", 30, "HR", 5000),
                new Employee("Mona", 25, "IT", 7000),
                new Employee("Ahmed", 30, "HR", 5500),
                new Employee("Sara", 27, "IT", 7200),
                new Employee("Omar", 40, "Finance", 8000),
                new Employee("Laila", 35, "Finance", 8200)
        );

        List<Employee> sort = employees.stream().sorted(Comparator.comparingDouble(Employee::getSalary).thenComparing(Employee::getName)).collect(Collectors.toList());
        sort.forEach(e ->
                System.out.println(e.getName() + " - " + e.getSalary())
        );
    }
}
