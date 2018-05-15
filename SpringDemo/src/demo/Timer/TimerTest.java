package demo.Timer;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TimerTest {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("resources/AnnotationTimer.xml");
//		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("resources/QuartzTimer.xml");
	}
}
