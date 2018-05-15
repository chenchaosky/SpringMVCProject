package demo.ProxyCglib;

import java.lang.reflect.Method;

import org.springframework.cglib.proxy.Callback;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

public class CglibProxy implements MethodInterceptor{
	
	 private Enhancer enhancer = new Enhancer();  
	 
	 @SuppressWarnings("rawtypes")
	 public Object getProxy(Class clazz){  
		 
		 //设置需要创建子类的类  
		 enhancer.setSuperclass(clazz);  
		 enhancer.setCallback((Callback) this);  
		 //通过字节码技术动态创建子类实例  
		 return enhancer.create();  
	 }  
	 
	 //实现MethodInterceptor接口方法  
	 @Override
	 public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {  
		
		 System.out.println("--------before----------");  
		 //通过代理类调用父类中的方法  
		 Object result = proxy.invokeSuper(obj, args);  
		 System.out.println("--------after-----------");  
		 return result;  
	 }  
}
