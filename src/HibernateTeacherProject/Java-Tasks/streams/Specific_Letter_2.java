package streams;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Specific_Letter_2 {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Ali", "Mona", "Ahmed", "Sara", "Amr", "Laila", "Kareem", "Nada", "Nour", "Samy", "", null);
       List<String> specificLetters = names.stream().filter(n->n != null&& n.startsWith("A")).collect(Collectors.toList());
       System.out.println(specificLetters);
    }
}