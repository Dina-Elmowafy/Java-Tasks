package Thread;

public class Task_1_1 {
    public static void main(String[] args) {
        Task_1 thread= new Task_1();
        thread.start();

        Thread runnable = new Thread(new Task_1_2());
        runnable.start();
    }

}