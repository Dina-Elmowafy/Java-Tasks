package Thread;

public class Task_4 {
    public static void main(String[] args)  {
     Thread thread1 = new Thread(()-> { for(int i =1; i<=5; i++){
      System.out.println(i);
      try
      {
     Thread.sleep(5000);}
      catch (InterruptedException e)
      { e.printStackTrace();
     }}});
        Thread thread2 = new Thread(()-> { for(int i =5; i<=10; i++){
            System.out.println(i);
            try
            {
                Thread.sleep(5000);}
            catch (InterruptedException e)
            { e.printStackTrace();
            }}});

        thread1.start();
        thread2.start();

        try{
            thread1.join();
            thread2.join();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}
