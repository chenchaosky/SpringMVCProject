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
		//父容器中没有建立父子容器关系之前，是获取不到parent的，只有父容器执行refresh方法后，第二次初使化子容器才会获取得到  
		//也就是第一次的初使化就不执行了，等父容器中建立好父子容器关系后再进行初使化，因为子容器需要引用父容器中的parentClass  
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
