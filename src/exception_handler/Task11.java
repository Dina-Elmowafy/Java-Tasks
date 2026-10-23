package exception_handler;
import java.util.Scanner;
public class Task11 {
    public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);

            try {
                System.out.println("Enter a number to divide 100 by:");
                int number = scanner.nextInt();

                int result = 100 / number;
                System.out.println("Result: " + result);

            } catch (ArithmeticException e) {
                System.out.println("Exception caught: " + e.getMessage());
            } finally {
                System.out.println("Finally block: This always executes!");
            }


        }
    }

