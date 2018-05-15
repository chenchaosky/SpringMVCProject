package demo.Asynchronization;

class OutThread1 implements Runnable {  
	  
    public void run() {  
        for (int i = 0; i < 100; i++) {  
            System.out.println(i + " start ...");  
            try {  
                Thread.sleep(2 * 1000L);  
            } catch (InterruptedException e) {  
                // TODO Auto-generated catch block  
                e.printStackTrace();  
            }  
        }  
    }  
      
}  

