package demo.ProxyJDK;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;

public class ProxyJDKTest2 {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		
		//生成$Proxy0的class文件
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        //获取动态代理类
        Class proxyClazz = Proxy.getProxyClass(IUserService1.class.getClassLoader(),IUserService1.class);
        //获得代理类的构造函数，并传入参数类型InvocationHandler.class
        Constructor constructor = proxyClazz.getConstructor(InvocationHandler.class);
        //通过构造函数来创建动态代理对象，将自定义的InvocationHandler实例传入
        IUserService1 proxy1 = (IUserService1) constructor.newInstance(new JDKProxy_InvocationHandler(new UserService1Impl()));
        //通过代理对象调用目标方法
        proxy1.display();

        
        //获取动态代理类
        proxyClazz = Proxy.getProxyClass(IUserService2.class.getClassLoader(),IUserService2.class);
        //获得代理类的构造函数，并传入参数类型InvocationHandler.class
        constructor = proxyClazz.getConstructor(InvocationHandler.class);
        //通过构造函数来创建动态代理对象，将自定义的InvocationHandler实例传入
        IUserService2 proxy2 = (IUserService2) constructor.newInstance(new JDKProxy_InvocationHandler(new UserService2Impl()));
        //通过代理对象调用目标方法
        proxy2.display();
        
	}
}
