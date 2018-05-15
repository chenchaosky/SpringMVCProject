package demo.ProxyJDK;

import java.lang.reflect.Proxy;

public class ProxyJDKTest3 {

	public static void main(String[] args) {
		
		//����$Proxy0��class�ļ�
	    System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
	    //���ؽӿڵ��������
	    IUserService1  Proxy1 = (IUserService1) Proxy.newProxyInstance(IUserService1.class.getClassLoader(),  
	               new Class[]{IUserService1.class},      //һ��ӿ�
	               new JDKProxy_InvocationHandler(new UserService1Impl())); //�Զ����InvocationHandler
	    Proxy1.display();
	    
	    
	    //���ؽӿڵ��������
	    IUserService2  Proxy2 = (IUserService2) Proxy.newProxyInstance(IUserService2.class.getClassLoader(),  
	               new Class[]{IUserService2.class},      //һ��ӿ�
	               new JDKProxy_InvocationHandler(new UserService2Impl())); //�Զ����InvocationHandler
	    Proxy2.display();
	}
}
