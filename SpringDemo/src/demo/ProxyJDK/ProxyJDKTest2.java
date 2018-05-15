package demo.ProxyJDK;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;

public class ProxyJDKTest2 {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		
		//����$Proxy0��class�ļ�
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        //��ȡ��̬������
        Class proxyClazz = Proxy.getProxyClass(IUserService1.class.getClassLoader(),IUserService1.class);
        //��ô�����Ĺ��캯�����������������InvocationHandler.class
        Constructor constructor = proxyClazz.getConstructor(InvocationHandler.class);
        //ͨ�����캯����������̬������󣬽��Զ����InvocationHandlerʵ������
        IUserService1 proxy1 = (IUserService1) constructor.newInstance(new JDKProxy_InvocationHandler(new UserService1Impl()));
        //ͨ������������Ŀ�귽��
        proxy1.display();

        
        //��ȡ��̬������
        proxyClazz = Proxy.getProxyClass(IUserService2.class.getClassLoader(),IUserService2.class);
        //��ô�����Ĺ��캯�����������������InvocationHandler.class
        constructor = proxyClazz.getConstructor(InvocationHandler.class);
        //ͨ�����캯����������̬������󣬽��Զ����InvocationHandlerʵ������
        IUserService2 proxy2 = (IUserService2) constructor.newInstance(new JDKProxy_InvocationHandler(new UserService2Impl()));
        //ͨ������������Ŀ�귽��
        proxy2.display();
        
	}
}
