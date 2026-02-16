package streams;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class SecondHighestNumber_24 {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(10, 5, 3, 7, 2, 10, 5, 8, 9, 0, -3, 4);
        Optional<Integer> secondHighes=numbers.stream().distinct().sorted(Comparator.reverseOrder()).skip(1).findFirst();
       // System.out.println(secondHighes);
        secondHighes.ifPresent(System.out::println);
    }
    }

