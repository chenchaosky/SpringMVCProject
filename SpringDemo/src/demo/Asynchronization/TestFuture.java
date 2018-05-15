package demo.Asynchronization;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TestFuture {

    //create thread pool
    final static ExecutorService service = Executors.newCachedThreadPool();

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		// TODO Auto-generated method stub
        Long t1 = System.currentTimeMillis();

        //task1
        Future<Boolean> booleanTask = service.submit(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return true;
            }
        });
        while (true) {
            if (booleanTask.isDone() && !booleanTask.isCancelled()) {
 //               Thread.sleep(3000L);
                Boolean result = booleanTask.get();
                System.err.println("BooleanTask: " + result);
                break;
            }
        }

        //task2
        Future<String> stringTask = service.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "Hello World";
            }
        });
        while (true) {
            if (stringTask.isDone() && !stringTask.isCancelled()) {
                String result = stringTask.get();
                System.err.println("StringTask: " + result);
                break;
            }
        }

        //task3
        Future<Integer> integerTask = service.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return new Random().nextInt(100);
            }
        });
        while (true) {
            if (integerTask.isDone() && !integerTask.isCancelled()) {
                Integer result = integerTask.get();
                System.err.println("IntegerTask: " + result);
                break;
            }
        }

        System.err.println("time: " + (System.currentTimeMillis() - t1));
	}
}