package demo.ParentChildContainer;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ChildContainerLoader implements ApplicationContextAware {

    ApplicationContext parentApplicationContext;  
    ConfigurableApplicationContext childContext;  

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		// TODO Auto-generated method stub
		this.parentApplicationContext=applicationContext;
	}
	
	public void load() {  
	    
		//ɨ������classpath�������spring_child��ͷspring�������ļ�����װ�䣬����Լ�����е������������������һ����spring_child��ͷ�������ļ�����ͨ������ļ�������������  
	    childContext = new ClassPathXmlApplicationContext("resources/spring_child*.xml");  
	    childContext.setParent(parentApplicationContext);  
	    childContext.refresh();  
	}  
}
