package exception_handler;
import java.util.Scanner;
public class Task6
{
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        try {
            System.out.print("Enter a text: ");
            String text = input.nextLine();
            System.out.print("Enter a number: ");
            int x = input.nextInt();


            int result = 10 / x;
            System.out.println("Result = " + result);

            System.out.println("Text length = " + text.length());

        } catch (ArithmeticException | NullPointerException e) {
            System.out.println("Exception happened: " + e);

        }

    }
}
