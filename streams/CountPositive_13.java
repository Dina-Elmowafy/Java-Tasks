package streams;

import java.util.Arrays;
import java.util.List;

public class CountPositive_13 {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(10, 5, 3, 7, 2, 10, 5, 8, 9, 0, -3, 4);
        long count =numbers.stream().filter(n->n>0).count();
        System.out.println(count);
    }
}