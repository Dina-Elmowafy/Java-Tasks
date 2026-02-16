package streams;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class comma_separated_16 {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Ali", "Mona", "Ahmed", "Sara", "Amr", "Laila", "Kareem", "Nada", "Nour", "Samy", "", null);
        String separated = names.stream().filter(n->n !=null && !n.isEmpty())
                .collect(Collectors.joining(", "));
        System.out.println(separated);
    }
}