package Thread;

public class Task_2 implements Runnable{
    @Override
    public void run(){
     System.out.println("Hi Im In Task2 " + Thread.currentThread().getName());
    }
}
