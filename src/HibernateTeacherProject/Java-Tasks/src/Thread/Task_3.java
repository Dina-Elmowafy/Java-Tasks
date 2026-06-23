package Thread;

public class Task_3 {
    public static void main(String[] args) throws InterruptedException {
    for(int i=1; i<=5; i++)
    {
       Thread.sleep(1000);
       System.out.println(i);
    }
}}
