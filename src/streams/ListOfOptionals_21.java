package streams;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ListOfOptionals_21 {
    public static void main(String[] args ){
        List<String> names = Arrays.asList("Ali", "Mona", "Ahmed", "Sara", "Amr", "Laila", "Kareem", "Nada", "Nour", "Samy", "", null);
        List<String> optional =  names.stream().map(Optional::ofNullable).flatMap(Optional::stream).filter(name -> !name.isEmpty()).collect(Collectors.toList());
        System.out.println(optional);
    }
}
