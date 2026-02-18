package streams;

import java.util.Arrays;
import java.util.List;
import java.util.OptionalDouble;

public class Average_12 {
    public static void main(String[] args) {
        List<Double> numbers = Arrays.asList(10.0, 5.0, 3.0, 7.0, 2.0, 10.0, 5.0, 8.0, 9.0, 0.0, -3.0, 4.0);
        OptionalDouble average = numbers.stream().mapToDouble(Double::doubleValue).average();
        System.out.println(average);
    }
}