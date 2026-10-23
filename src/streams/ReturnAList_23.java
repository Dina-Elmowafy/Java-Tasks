package streams;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ReturnAList_23 {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Ali", "Mona", "Ahmed", "Sara", "Amr", "Laila", "Kareem", "Nada", "Nour", "Samy", "", null);
        List<String> uppercaseA =names.stream().filter(Objects::nonNull).filter(n->n.startsWith("A")).map(String::toUpperCase).collect(Collectors.toList());
        System.out.println(uppercaseA);
    }
}