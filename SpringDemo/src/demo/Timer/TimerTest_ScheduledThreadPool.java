package demo.Timer;

import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TimerTest_ScheduledThreadPool {

	private static long start;  
	 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ScheduledExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(2);
        
        TimerTask task1 = new TimerTask()  
        {  
            @Override  
            public void run()  
            {  
                try{  
                    System.out.println("task1 invoked ! " + (System.currentTimeMillis() - start));  
                    Thread.sleep(5000);  
                } 
                catch (Exception e){  
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
        
        start = System.currentTimeMillis();  
        //ScheduledExecutorService is a multi-thread process, so newScheduledThreadPool could run concurrently.
        newScheduledThreadPool.schedule(task1, 1000, TimeUnit.MILLISECONDS); 
        //if task3 throw an exception, the following task still run properly.
        newScheduledThreadPool.schedule(task3, 2000, TimeUnit.MILLISECONDS);  
        newScheduledThreadPool.schedule(task2, 3000, TimeUnit.MILLISECONDS);  
	}
}
