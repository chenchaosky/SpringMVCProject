package demo.ProxyCglib;

public class CglibProxyTest1 {

	public static void main(String[] args) {
		
		CglibProxy proxy = new CglibProxy();  
		
		//ͨ����������ķ�ʽ����������  
		UserService1 proxy1 = (UserService1)proxy.getProxy(UserService1.class);  
		proxy1.display();  
		
		UserService2 proxy2 = (UserService2)proxy.getProxy(UserService2.class);  
		proxy2.display();  
	}
}
