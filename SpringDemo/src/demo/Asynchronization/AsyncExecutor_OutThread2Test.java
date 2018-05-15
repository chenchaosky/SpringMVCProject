package demo.Asynchronization;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

public class AsyncExecutor_OutThread2Test {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		
		// TODO Auto-generated method stub
		AsyncTaskExecutor executor = new SimpleAsyncTaskExecutor("sys.out");  
        Future<String> future = executor.submit(new OutThread2());  
        //"future.get()" is a blocked processing
        System.out.println(future.get());  
        System.out.println("Hello World!");  
        Thread.sleep(10000 * 1000L);  
	}
}
