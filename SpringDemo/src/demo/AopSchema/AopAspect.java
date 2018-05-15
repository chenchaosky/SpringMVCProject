package demo.AopSchema;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.aspectj.lang.ProceedingJoinPoint;

public class AopAspect {
	public void beforeInstance(String para1, String para2){
		System.out.println("this is beforeInstance method, before advice: para1 = "+para1+" para2 = "+para2);
	}
	
	public void afterReturningInstance(boolean retVal) {
        System.out.println("this is afterReturnInstance method, after advice: "+retVal);
    }
	
	public String aroundService(ProceedingJoinPoint pip, List<String> para) throws Throwable{
		System.out.println("entering the aroundService method");
		for (Iterator<String> i = para.iterator(); i.hasNext(); ){
			System.out.println((String)i.next());
		}
		System.out.println("around before advice");
		List<String> listqueue = new ArrayList<String>();
		listqueue.add("Sarala");
		listqueue.add("Candy");
		//to use the new parameter instead of the initial one
		String result = (String)pip.proceed(new Object[]{listqueue});
		//to use the initial parameter
		//pip.proceed();
		System.out.println("around after advice");
		System.out.println("quitting the aroundService method");
		return result;
	}
	
	public void afterFinallyAdvice(String para){
		System.out.println("this is the afterFinallyAdvice method, para = "+para);
	}
}
