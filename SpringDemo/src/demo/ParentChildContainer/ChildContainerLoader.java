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
	    
		//扫描所有classpath下面的以spring_child开头spring的配置文件进行装配，这里约定所有的子容器插件都必须有一个以spring_child开头的配置文件，并通过这个文件被父容器加载  
	    childContext = new ClassPathXmlApplicationContext("resources/spring_child*.xml");  
	    childContext.setParent(parentApplicationContext);  
	    childContext.refresh();  
	}  
}
