package streams;

import java.util.Arrays;
import java.util.List;

public class Sum_11 {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(10, 5, 3, 7, 2, 10, 5, 8, 9, 0, -3, 4);
        int sum= numbers.stream().reduce(0,Integer::sum);
        System.out.println(sum);
    }
}