package demo.ParentChildContainer;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

public class ChildClass2 implements InitializingBean {

	//����required����false������Ϊ��û�н�������������ϵ֮ǰ�����parentClass��ע�벻���˵�  
    @Autowired(required = false)  
    ParentClass parentClass;  
  
    //����required����false������Ϊ������֮ǰ�ǲ��ܹ��໥���õģ�ֻ�ǲ���ʹ�á���ע�������û�зŵ������������2�У����ﲻ��������  
    @Autowired(required = false)  
    ChildClass1 childClass1;  
  
    public void print() {  
        if (parentClass != null) {  
            parentClass.print();  
        }  
        System.out.println("This is child class 2");  
        
        if (childClass1 != null) {  
            childClass1.print();  
        }  
    }  

	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
//		print();
	}

}
