package demo.Asynchronization;

import java.util.concurrent.Callable;

class OutThread2 implements Callable<String> {  

    @Override  
    public String call() throws Exception {  
        String ret = " I test callable";  
        for (int i = 0; i < 10; i++) {  
                try {  
                        Thread.sleep(2 * 1000L);  
                        System.out.println(i+" sleep 1s");  
                } catch (InterruptedException e) {  
                     // TODO Auto-generated catch block  
                     e.printStackTrace();  
                }  
         }  
        return ret;  
    }    
} 
