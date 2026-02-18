package streams;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Check_8 {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(10, 5, 3, 7, 2, 10, 5, 8, 9, 0, -3, 4);
        boolean chick= numbers.stream().anyMatch(n -> n % 5 == 0);
        System.out.println(chick);
    }
}