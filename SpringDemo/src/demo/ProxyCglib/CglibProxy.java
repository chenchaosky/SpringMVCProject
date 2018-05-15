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
		 
		 //������Ҫ�����������  
		 enhancer.setSuperclass(clazz);  
		 enhancer.setCallback((Callback) this);  
		 //ͨ���ֽ��뼼����̬��������ʵ��  
		 return enhancer.create();  
	 }  
	 
	 //ʵ��MethodInterceptor�ӿڷ���  
	 @Override
	 public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {  
		
		 System.out.println("--------before----------");  
		 //ͨ����������ø����еķ���  
		 Object result = proxy.invokeSuper(obj, args);  
		 System.out.println("--------after-----------");  
		 return result;  
	 }  
}
