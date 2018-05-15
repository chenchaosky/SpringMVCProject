package demo.Asynchronization;

import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

public class AsyncExecutor_OutThread1Test {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
        AsyncTaskExecutor executor = new SimpleAsyncTaskExecutor("sys.out");  
        executor.execute(new OutThread1(), 50000L); 
        Thread.sleep(10000L);  
        System.out.println("Hello, World!");  
        Thread.sleep(10000 * 1000L);  
	}
}
