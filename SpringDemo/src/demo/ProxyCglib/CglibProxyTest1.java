package demo.ProxyCglib;

public class CglibProxyTest1 {

	public static void main(String[] args) {
		
		CglibProxy proxy = new CglibProxy();  
		
		//通过生成子类的方式创建代理类  
		UserService1 proxy1 = (UserService1)proxy.getProxy(UserService1.class);  
		proxy1.display();  
		
		UserService2 proxy2 = (UserService2)proxy.getProxy(UserService2.class);  
		proxy2.display();  
	}
}
