package demo.ZeroConfiguration;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ZeroTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		 AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
//		 applicationContext.scan("demo.ZeroConfiguration");
//		 applicationContext.refresh();
		 
		 //if ApplicationContextConfig.java has @ComponentScan annotation, then the previous three lines should be replaced with the following one.
		 AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(ApplicationContextConfig.class);
		 
	     Cinema cinema = applicationContext.getBean(Cinema.class);
	     cinema.printMovieName();
	     applicationContext.close();
/*	     
	     ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("resources/ZeroConfigTest.xml");
	     Cinema cinema_alt = ctx.getBean("cinema",Cinema.class);
	     cinema_alt.printMovieName();
	     ctx.close();
	     */
	}
}
