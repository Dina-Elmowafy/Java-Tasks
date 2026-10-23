package streams;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class department_14 {
    public static void main(String[] args) {
        List<Student> students = Arrays.asList(
                new Student("Ali", "IT", 85),
                new Student("Mona", "CS", 92),
                new Student("Ahmed", "IT", 60),
                new Student("Sara", "CS", 70),
                new Student("Omar", "IS", 45),
                new Student("Laila", "IS", 78));


        Map<String, String> groupingByDep = students.stream().collect(Collectors.groupingBy(Student::getDepartment, Collectors.mapping(Student::getName, Collectors.joining(", "))));
        System.out.println(groupingByDep);



    }
    }

