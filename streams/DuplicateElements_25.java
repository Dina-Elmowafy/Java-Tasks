package streams;

import java.util.*;
import java.util.stream.Collectors;

public class DuplicateElements_25 {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(10, 5, 3, 7, 2, 10, 5, 8, 9, 0, -3, 4);

        List<Integer> duplicates = numbers.stream().filter(n -> Collections.frequency(numbers, n) > 1).distinct().toList();

        System.out.println(duplicates);
    }
}