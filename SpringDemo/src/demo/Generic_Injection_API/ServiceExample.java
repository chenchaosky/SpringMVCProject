package demo.Generic_Injection_API;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ResolvableType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.ClassUtils;
import org.springframework.util.ReflectionUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = GenericConfig.class)
public class ServiceExample {

	 @Autowired
	 private Service<A, B> abService;
	 @Autowired
	 private Service<C, D> cdService;
	     
	 private List<List<String>> list;  
	 private Map<String, Map<String, Integer>> map;  
	 private Map<String,Integer>[] array; 

	 public ServiceExample() {  
	 } 
	 
//	 public ServiceExample(List<List<String>> list, Map<String, Map<String, Integer>> map) {  
//	 }  
 
	 private HashMap<String, List<String>> method() {  
		 return null;  
	 }  
	 
	 @Test
	 public void TestGererics(){
		//测试泛型注入
		abService.show();
		//获得类的泛型信息
		ResolvableType resolvableType1 = ResolvableType.forClass(CDService.class);
		resolvableType1.getInterfaces()[0].getGeneric(1).resolve();
		System.out.println(resolvableType1.getInterfaces()[0].getGeneric(1).resolve());
		
		//获得类中属性的泛型信息		
		ResolvableType resolvableType3 = ResolvableType.forField(ReflectionUtils.findField(ServiceExample.class, "list"));  
		resolvableType3.getGeneric(0).getGeneric(0).resolve();  
		//另一种写法
		resolvableType3.getGeneric(0,0).resolve();  
		System.out.println(resolvableType3.getGeneric(0,0).resolve());
		
		//获得类中方法返回值的泛型信息	
		ResolvableType resolvableType5 = ResolvableType.forMethodReturnType(ReflectionUtils.findMethod(ServiceExample.class, "method"));  
		resolvableType5.getGeneric(1, 0).resolve();  
		System.out.println(resolvableType5.getGeneric(1, 0).resolve());
		
		//获得类中构造函数参数的泛型信息	
//		ResolvableType resolvableType6 = ResolvableType.forConstructorParameter(ClassUtils.getConstructorIfAvailable(ServiceExample.class, List.class, Map.class), 1);  
//		resolvableType6.getGeneric(1, 0).resolve(); 
//		System.out.println(resolvableType6.getGeneric(1, 0).resolve());
		
		//获得类中数组属性的泛型信息	
		ResolvableType resolvableType7 = ResolvableType.forField(ReflectionUtils.findField(ServiceExample.class, "array"));  
		resolvableType7.isArray();//判断是否是数组  
		resolvableType7.getComponentType().getGeneric(1).resolve();  
		System.out.println(resolvableType7.getComponentType().getGeneric(1).resolve());
		
		//ResolvableType.forClassWithGenerics(List.class, String.class)相当于创建一个List<String>类型
		ResolvableType resolvableType8 = ResolvableType.forClassWithGenerics(List.class, String.class); 
		//ResolvableType.forArrayComponent(resolvableType8)相当于创建一个List<String>[]数组；
		ResolvableType resolvableType9 = ResolvableType.forArrayComponent(resolvableType8);  
		resolvableType9.getComponentType().getGeneric(0).resolve();  
		System.out.println(resolvableType9.getComponentType().getGeneric(0).resolve());

		//如下创建一个List<Integer>[]数组，与之前的List<String>[]数组比较，将返回false。
		ResolvableType resolvableType10 = ResolvableType.forClassWithGenerics(List.class, Integer.class);
		ResolvableType resolvableType11= ResolvableType.forArrayComponent(resolvableType10);  
		resolvableType11.getComponentType().getGeneric(0).resolve();  
		//通过isAssignableFrom泛型等价比较
		System.out.println(resolvableType7.isAssignableFrom(resolvableType11));  

	 }

}
