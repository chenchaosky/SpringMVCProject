package demo.ParentChildContainer;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class ChildClass1 implements InitializingBean, ApplicationContextAware {
	 
    ParentClass parentClass;  
   
    ChildClass2 childClass2;  
  
	ApplicationContext applicationContext;  
	 
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		// TODO Auto-generated method stub
		this.applicationContext = applicationContext;
	}
	
    public void print() {  
        if (parentClass != null) {  
            parentClass.print();  
        }  
        System.out.println("This is child class 1");  
        
        if (childClass2 != null) {  
            childClass2.print();  
        }  
    }  

	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		if(this.applicationContext.getParent()==null){  
			return;  
		}  
		parentClass = applicationContext.getParent().getBean(ParentClass.class);
//		childClass2 = applicationContext.getParent().getBean(ChildClass2.class);
		print();
	}
}
