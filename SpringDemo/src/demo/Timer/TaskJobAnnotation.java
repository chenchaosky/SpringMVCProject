package demo.Timer;

import org.springframework.scheduling.annotation.Scheduled;    
import org.springframework.stereotype.Component;  

@Component
public class TaskJobAnnotation {
    @Scheduled(cron = "0-59 * * * * ?")  
    public void job() {  
        System.out.println("Annotation task is going on");  
    }  
}
