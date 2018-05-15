package demo.ProxyJDK;

import java.lang.reflect.Proxy;

public class ProxyJDKTest3 {

	public static void main(String[] args) {
		
		//生成$Proxy0的class文件
	    System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
	    //加载接口的类加载器
	    IUserService1  Proxy1 = (IUserService1) Proxy.newProxyInstance(IUserService1.class.getClassLoader(),  
	               new Class[]{IUserService1.class},      //一组接口
	               new JDKProxy_InvocationHandler(new UserService1Impl())); //自定义的InvocationHandler
	    Proxy1.display();
	    
	    
	    //加载接口的类加载器
	    IUserService2  Proxy2 = (IUserService2) Proxy.newProxyInstance(IUserService2.class.getClassLoader(),  
	               new Class[]{IUserService2.class},      //一组接口
	               new JDKProxy_InvocationHandler(new UserService2Impl())); //自定义的InvocationHandler
	    Proxy2.display();
	}
}
