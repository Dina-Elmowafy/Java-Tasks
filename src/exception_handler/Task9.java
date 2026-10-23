package exception_handler;

public class Task9 {

    public static void main(String[] args) {
        try {
            calltheMethod(-5);
        } catch (ExceptionPropagation e) {
            System.out.println("Error " + e.getMessage());
        }

    }
    static void theMethod(int number) throws ExceptionPropagation {
        System.out.println("theMethod i'm hear");
        if(number < 0) {
            throw new ExceptionPropagation("The number is negative! " + number);
        }
        System.out.println("number is " + number);
    }

    static void calltheMethod(int number) throws ExceptionPropagation {
        System.out.println(" calltheMethod i'm hear");
        theMethod(number);
    }
}
