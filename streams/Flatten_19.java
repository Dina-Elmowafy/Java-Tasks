package streams;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Flatten_19 {
    public static void main(String[] args) {
        List<List<String>> nestedWords = Arrays.asList(
                Arrays.asList("Java", "Stream"),
                Arrays.asList("API", "Lambda"),
                Arrays.asList("FlatMap", "Map")
        );
        List<String> flatWords =nestedWords.stream().flatMap(List::stream).collect(Collectors.toList());
        System.out.println(flatWords);
    }
}