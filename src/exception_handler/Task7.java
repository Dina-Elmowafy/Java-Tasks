package exception_handler;
import java.util.Scanner;
public class Task7 {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        try {
            System.out.print("Enter your age: ");
            int age = input.nextInt();
            if (age < 18) {
                throw new InvalidAgeException("Age must be 18 or older!");
            }
            System.out.println("Your age is: " + age);

        } catch (InvalidAgeException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
