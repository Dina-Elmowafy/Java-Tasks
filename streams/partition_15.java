package streams;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class partition_15 {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(10, 5, 3, 7, 2, 10, 5, 8, 9, 0, -3, 4);
        Map<Boolean, List<Integer>> partition =numbers.stream().collect(Collectors.partitioningBy(n->n%2==0));

        System.out.println("odd :"+ partition.get(true));
        System.out.println("even :"+ partition.get(false));
    }
}