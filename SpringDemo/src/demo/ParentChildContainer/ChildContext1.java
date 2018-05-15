package demo.ParentChildContainer;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ChildContext1 implements ApplicationContextAware {  
    
	ApplicationContext parentApplicationContext;  

	@SuppressWarnings("resource")
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {  
		//��������û�н�������������ϵ֮ǰ���ǻ�ȡ����parent�ģ�ֻ�и�����ִ��refresh�����󣬵ڶ��γ�ʹ���������Ż��ȡ�õ�  
		//Ҳ���ǵ�һ�εĳ�ʹ���Ͳ�ִ���ˣ��ȸ������н����ø���������ϵ���ٽ��г�ʹ������Ϊ��������Ҫ���ø������е�parentClass  
		if(applicationContext.getParent()==null){  
			return;  
		}  
		//Get parent application context  
		this.parentApplicationContext = applicationContext.getParent();  

		ConfigurableApplicationContext  childContext = new ClassPathXmlApplicationContext("resources/child1.xml");  
		childContext.setParent(this.parentApplicationContext);  
		childContext.refresh();  
	}  
}  
