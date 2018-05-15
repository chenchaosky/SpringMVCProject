package demo.ProxyJDK;

public class ProxyJDKTest1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        // 实例化目标对象  
        IUserService1 userService1 = new UserService1Impl();  
        IUserService2 userService2 = new UserService2Impl();  
          
        // 实例化InvocationHandler  
        JDKProxy_InvocationHandler invocationHandler = new JDKProxy_InvocationHandler(userService1);  
        // 根据目标对象生成代理对象  
        IUserService1 proxy1 = (IUserService1)invocationHandler.getProxy();  
        // 调用代理对象的方法  
        proxy1.display();         
        
        invocationHandler = new JDKProxy_InvocationHandler(userService2);  
        IUserService2 proxy2 = (IUserService2)invocationHandler.getProxy();  
        proxy2.display();  
	}
}
