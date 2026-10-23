package streams;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Comparator;

public class Count_6 {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Ali", "Mona", "Ahmed", "Sara", "Amr", "Laila", "Kareem", "Nada", "Nour", "Samy", "", null);
        long count= names.stream().filter(n->n !=null &&n.length()>5).count();
        System.out.println(count);

    }
}