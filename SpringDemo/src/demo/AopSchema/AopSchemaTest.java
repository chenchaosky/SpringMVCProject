package demo.AopSchema;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AopSchemaTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("resources/AopSchema.xml");
		IAopDemoService helloworldService = ctx.getBean("aopDemoService", IAopDemoService.class);
		/*
		helloworldService.helloService("Eric","Chen");
		
		List<String> listqueue = new ArrayList<String>();
		listqueue.add("Eric");
		listqueue.add("Chen");
		String result = helloworldService.listService(listqueue);
		System.out.println(result);
		*/
		helloworldService.helloTest("Eric");
        ctx.close();
	}
}
