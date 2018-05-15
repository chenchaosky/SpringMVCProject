package demo.AopAspectJ;

import java.util.Iterator;
import java.util.List;

public class AopDemoService implements IAopDemoService {

	@Override
	public boolean helloService(String para1, String para2) {
		// TODO Auto-generated method stub
		System.out.println("This is helloService method, para1 = "+para1+" para2 = "+para2);
		return true;
	}
	
	@Override
	public String listService(List<String> para){
		for (Iterator<String> i = para.iterator(); i.hasNext(); ){
			System.out.println((String)i.next());
		}
		return para.get(0);
	}
	
	@Override
	public void helloTest(String para)
	{
		System.out.println("This is the helloTest method, para = "+para);
	}
	
	@Override
	public void other(){
		System.out.println("This is the other method.");
	}
/*	
	public double min(double a, double b) {  
        double result = a<=b?a:b; 
        System.out.println("This is the min method in AopDemoService.");  
        return result;  
    }  
*/
}
