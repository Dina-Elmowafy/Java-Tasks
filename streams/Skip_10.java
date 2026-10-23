package streams;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Skip_10 {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(10, 5, 3, 7, 2, 10, 5, 8, 9, 0, -3, 4);
        List<Integer>skip= numbers.stream().skip(3).collect(Collectors.toList());
        System.out.println(skip);
    }
}