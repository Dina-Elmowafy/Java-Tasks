package exception_handler;
import java.util.Scanner;
public class Task12 {
    public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);

            try {
                System.out.print("Enter a number to divide 100 by: ");
                int number = scanner.nextInt();

                try {
                    int result = 100 / number;
                    System.out.println("Result: " + result);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("ArrayIndexOutOfBoundsException ");
                }

            } catch (ArithmeticException e) {

                System.out.println(" ArithmeticException " + e.getMessage());
            } finally {
                System.out.println("Finished");
            }


        }
}
