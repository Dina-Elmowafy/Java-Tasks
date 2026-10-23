package streams;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class UniqueCharacters_20 {
    public static void main(String[] args) {
        List<List<String>> nestedWords = Arrays.asList(
                Arrays.asList("Java", "Stream"),
                Arrays.asList("API", "Lambda"),
                Arrays.asList("FlatMap", "Map")
        );
        List<Character> flatWords = nestedWords.stream().flatMap(List::stream)
                .flatMap(word -> word.chars().mapToObj(c -> (char) c))
                .distinct()
                .collect(Collectors.toList());
        System.out.println(flatWords);
    }
}