package demo.SpelAnnotation;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpelAnnoatationTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("resources/SpelAnnotation.xml");
        SpelBean helloBean1 = ctx.getBean("helloBean1", SpelBean.class);
        System.out.println(helloBean1.getValue());
        SpelBean helloBean2 = ctx.getBean("helloBean2", SpelBean.class);
        System.out.println(helloBean2.getValue());
        ctx.close();
	}
}
