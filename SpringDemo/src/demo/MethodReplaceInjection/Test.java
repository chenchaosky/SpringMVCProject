package demo.MethodReplaceInjection;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("resources/MethodReplaceInjection.xml");
		Printer printer = context.getBean("printer",Printer.class);
		//the method "print" will be replaced with method "PrinterReplacer.reimplement"
		String result = printer.print("This method will be replaced!");
		System.out.println(result);
		context.close();
	}
}
