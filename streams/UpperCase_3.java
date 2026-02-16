package streams;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class UpperCase_3 {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Ali", "Mona", "Ahmed", "Sara", "Amr", "Laila", "Kareem", "Nada", "Nour", "Samy", "", null);
        List<String> upperCase = names.stream().filter(n -> n != null).map(n ->n.toUpperCase()).collect(Collectors.toList());
        System.out.println(upperCase);
    }
}