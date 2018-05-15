package demo.AopAspectJ;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AopAspectJTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("resources/AopAspect.xml");
		IAopDemoService aopDemoService = ctx.getBean("aopDemoService", IAopDemoService.class);
		aopDemoService.helloService("Eric","Chen");
/*        
		List<String> listqueue = new ArrayList<String>();
		listqueue.add("Eric");
		listqueue.add("Chen");
		String result = aopDemoService.listService(listqueue);
		System.out.println(result);
		aopDemoService.helloTest("Eric");
*/
		
		IIntroduction1 MinCaculator = (IIntroduction1)aopDemoService;
		//the min method in Introduction1 is relaced with the same name method in IAopDemoService
		MinCaculator.min(2.3,4.5); 
		
		IIntroduction2 MaxCaculator = (IIntroduction2)aopDemoService;
		MaxCaculator.max(3,5);  

        ctx.close();
	}
}
