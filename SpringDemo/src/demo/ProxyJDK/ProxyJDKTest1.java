package demo.ProxyJDK;

public class ProxyJDKTest1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        // ʵ����Ŀ�����  
        IUserService1 userService1 = new UserService1Impl();  
        IUserService2 userService2 = new UserService2Impl();  
          
        // ʵ����InvocationHandler  
        JDKProxy_InvocationHandler invocationHandler = new JDKProxy_InvocationHandler(userService1);  
        // ����Ŀ��������ɴ������  
        IUserService1 proxy1 = (IUserService1)invocationHandler.getProxy();  
        // ���ô������ķ���  
        proxy1.display();         
        
        invocationHandler = new JDKProxy_InvocationHandler(userService2);  
        IUserService2 proxy2 = (IUserService2)invocationHandler.getProxy();  
        proxy2.display();  
	}
}
