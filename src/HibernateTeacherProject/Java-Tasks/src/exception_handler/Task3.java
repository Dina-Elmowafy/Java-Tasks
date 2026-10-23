package exception_handler;

public class Task3 {
    public static void main(String[] args) {
        String text = null;

        try {
            convertToUpper(text);
        } catch (NullPointerException e) {
            System.out.println("Error: String is null!");
        }
    }
    public static void convertToUpper(String str) {
        System.out.println(str.toUpperCase());
    }
}
