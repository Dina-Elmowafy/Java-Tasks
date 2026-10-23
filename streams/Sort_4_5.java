package streams;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Comparator;

public class Sort_4_5 {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(10, 5, 3, 7, 2, 10, 5, 8, 9, 0, -3, 4);
        List<Integer> sort =numbers.stream().distinct().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        System.out.println(sort);
}}
