package streams;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GroupEmployee_17 {
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
                new Employee("Ali", 30, "HR", 5000),
                new Employee("Mona", 25, "IT", 7000),
                new Employee("Ahmed", 30, "HR", 5500),
                new Employee("Sara", 27, "IT", 7200),
                new Employee("Omar", 40, "Finance", 8000),
                new Employee("Laila", 35, "Finance", 8200)
        );
        Map<Integer, Long> countByAge = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getAge,
                        Collectors.counting()
                ));
        System.out.println(countByAge);
    }
    }

