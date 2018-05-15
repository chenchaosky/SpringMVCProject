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
		//���Է���ע��
		abService.show();
		//�����ķ�����Ϣ
		ResolvableType resolvableType1 = ResolvableType.forClass(CDService.class);
		resolvableType1.getInterfaces()[0].getGeneric(1).resolve();
		System.out.println(resolvableType1.getInterfaces()[0].getGeneric(1).resolve());
		
		//����������Եķ�����Ϣ		
		ResolvableType resolvableType3 = ResolvableType.forField(ReflectionUtils.findField(ServiceExample.class, "list"));  
		resolvableType3.getGeneric(0).getGeneric(0).resolve();  
		//��һ��д��
		resolvableType3.getGeneric(0,0).resolve();  
		System.out.println(resolvableType3.getGeneric(0,0).resolve());
		
		//������з�������ֵ�ķ�����Ϣ	
		ResolvableType resolvableType5 = ResolvableType.forMethodReturnType(ReflectionUtils.findMethod(ServiceExample.class, "method"));  
		resolvableType5.getGeneric(1, 0).resolve();  
		System.out.println(resolvableType5.getGeneric(1, 0).resolve());
		
		//������й��캯�������ķ�����Ϣ	
//		ResolvableType resolvableType6 = ResolvableType.forConstructorParameter(ClassUtils.getConstructorIfAvailable(ServiceExample.class, List.class, Map.class), 1);  
//		resolvableType6.getGeneric(1, 0).resolve(); 
//		System.out.println(resolvableType6.getGeneric(1, 0).resolve());
		
		//��������������Եķ�����Ϣ	
		ResolvableType resolvableType7 = ResolvableType.forField(ReflectionUtils.findField(ServiceExample.class, "array"));  
		resolvableType7.isArray();//�ж��Ƿ�������  
		resolvableType7.getComponentType().getGeneric(1).resolve();  
		System.out.println(resolvableType7.getComponentType().getGeneric(1).resolve());
		
		//ResolvableType.forClassWithGenerics(List.class, String.class)�൱�ڴ���һ��List<String>����
		ResolvableType resolvableType8 = ResolvableType.forClassWithGenerics(List.class, String.class); 
		//ResolvableType.forArrayComponent(resolvableType8)�൱�ڴ���һ��List<String>[]���飻
		ResolvableType resolvableType9 = ResolvableType.forArrayComponent(resolvableType8);  
		resolvableType9.getComponentType().getGeneric(0).resolve();  
		System.out.println(resolvableType9.getComponentType().getGeneric(0).resolve());

		//���´���һ��List<Integer>[]���飬��֮ǰ��List<String>[]����Ƚϣ�������false��
		ResolvableType resolvableType10 = ResolvableType.forClassWithGenerics(List.class, Integer.class);
		ResolvableType resolvableType11= ResolvableType.forArrayComponent(resolvableType10);  
		resolvableType11.getComponentType().getGeneric(0).resolve();  
		//ͨ��isAssignableFrom���͵ȼ۱Ƚ�
		System.out.println(resolvableType7.isAssignableFrom(resolvableType11));  

	 }

}
