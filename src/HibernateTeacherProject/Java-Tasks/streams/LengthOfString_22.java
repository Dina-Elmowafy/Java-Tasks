package streams;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class LengthOfString_22 {
    public static void main(String[] args ) {
        List<String> names = Arrays.asList("Ali", "Mona", "Ahmed", "Sara", "Amr", "Laila", "Kareem", "Nada", "Nour", "Samy", "", null);
        List<Integer> length = names.stream().filter(Objects::nonNull).map(String::length).collect(Collectors.toList());
        System.out.println(length);
    }
    }
