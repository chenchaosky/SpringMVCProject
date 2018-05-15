package demo.ProxyCglib;

import org.springframework.cglib.proxy.Enhancer;

public class CglibProxyTest2 {

	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
		Enhancer enhancer = new Enhancer();  
		
		enhancer.setSuperclass(UserService1.class);  
		enhancer.setCallback(new CglibProxy());  
		UserService1 proxy1 = (UserService1)enhancer.create();
		proxy1.display();  
		
		
		enhancer.setSuperclass(UserService2.class);  
		enhancer.setCallback(new CglibProxy());  
		UserService2 proxy2 = (UserService2)enhancer.create();
		proxy2.display();  
	}

}
