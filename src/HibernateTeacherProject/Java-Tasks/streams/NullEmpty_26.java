package streams;

import java.util.Arrays;
import java.util.List;

public class NullEmpty_26 {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Ali", "Mona", "Ahmed", "Sara", "Amr", "Laila", "Kareem", "Nada", "Nour", "Samy", "", null);
        List<String> nullEmp = names.stream().filter(s -> s != null && !s.isEmpty()).toList();
        System.out.println(nullEmp);
    }
}
