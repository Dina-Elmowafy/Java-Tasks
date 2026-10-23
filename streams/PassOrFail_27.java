package streams;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PassOrFail_27 {
    public static void main(String[] args) {
        List<Student> students = Arrays.asList(
                new Student("Ali", "IT", 85),
                new Student("Mona", "CS", 92),
                new Student("Ahmed", "IT", 60),
                new Student("Sara", "CS", 70),
                new Student("Omar", "IS", 45),
                new Student("Laila", "IS", 78)
        );
        Map<Boolean, List<Student>> result = students.stream().collect(Collectors.partitioningBy(s -> s.getGrade() >= 60));
        System.out.println("Pass:");
        result.get(true).forEach(s -> System.out.println(s.getName()));

        System.out.println("\nFail:");
        result.get(false).forEach(s -> System.out.println(s.getName()));

    }
}
