package exception_handler;
import java.util.Scanner;
public class Task2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        try {
            System.out.print("Enter a string number : ");
            String numberS = input.nextLine();

            int number = Integer.parseInt(numberS);
            System.out.println("Converted number = " + number);

        } catch (NumberFormatException e) {
            System.out.println("Error: Please enter a valid integer number!");


        }
    }
}

