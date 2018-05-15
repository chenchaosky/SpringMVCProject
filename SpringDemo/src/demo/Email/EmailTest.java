package demo.Email;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class EmailTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("resources/Email.xml");
		EmailService emailService = ctx.getBean("emailService",EmailService.class);
		emailService.emailManage();
		ctx.close();
	}

}
