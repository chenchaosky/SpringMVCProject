package demo.Timer;

import java.util.Timer;
import java.util.TimerTask;

public class TimerTest_TimerTask {

	private static long start;
	
	public static void main(String[] args) throws Exception  
    {  
        TimerTask task1 = new TimerTask()  
        {  
            @Override  
            public void run()  
            {  
                System.out.println("task1 invoked ! " + (System.currentTimeMillis() - start));  
                try  {  
                    Thread.sleep(3000);  
                } catch (InterruptedException e)  {  
                    e.printStackTrace();  
                }  
  
            }  
        };  
        
        TimerTask task2 = new TimerTask()  
        {  
            @Override  
            public void run()  
            {  
                System.out.println("task2 invoked ! " + (System.currentTimeMillis() - start));  
            }  
        };  
        
        final TimerTask task3 = new TimerTask()  
        {  
            @Override  
            public void run()  
            {  
                throw new RuntimeException();  
            }  
        };  

        
        Timer timer = new Timer();  
        start = System.currentTimeMillis();
        //timer is a one-thread process, so the second timer will start after the first timer finished
        timer.schedule(task1, 1000);  
        //if task3 throw an exception, the following task could be halted.
//        timer.schedule(task3, 2000);  
        timer.schedule(task2, 3000);  
    }  

}
