package streams;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
public class FindFirst7 {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Ali", "Mona", "Ahmed", "Sara", "Amr", "Laila", "Kareem", "Nada", "Nour", "Samy", "", null);
        Optional<String>first=names.stream().filter(n->n !=null&&n.startsWith("A")).findFirst();
        System.out.println(first);
    }
}