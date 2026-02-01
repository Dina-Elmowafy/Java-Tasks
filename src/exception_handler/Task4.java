package exception_handler;
import java.util.Scanner;
public class Task4 {
        public static void main(String[] args) {

            int[] arr = {10, 20, 30, 40, 50};

            Scanner input = new Scanner(System.in);

            System.out.print("Enter index (0 to 4): ");
            int index = input.nextInt();

            try {
                System.out.println("Value = " + arr[index]);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Error: Index out of range!");
            }

        }
    }


