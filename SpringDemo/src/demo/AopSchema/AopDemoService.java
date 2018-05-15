package demo.AopSchema;

import java.util.Iterator;
import java.util.List;

public class AopDemoService implements IAopDemoService {

	@Override
	public boolean helloService(String para1, String para2) {
		// TODO Auto-generated method stub
		System.out.println("Hello Service! para1 = "+para1+" para2 = "+para2);
		return true;
	}
	
	public String listService(List<String> para){
		for (Iterator<String> i = para.iterator(); i.hasNext(); ){
			System.out.println((String)i.next());
		}
		return para.get(0);
	}
	
	public void helloTest(String para)
	{
		System.out.println("This is the helloTest method, para = "+para);
	}
}
