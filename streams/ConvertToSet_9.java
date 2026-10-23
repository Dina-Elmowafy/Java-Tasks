package streams;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Set;


public class ConvertToSet_9 {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(10, 5, 3, 7, 2, 10, 5, 8, 9, 0, -3, 4);
        Set<Integer> mySet = numbers.stream().collect(Collectors.toSet());
        System.out.println(mySet);

    }
}