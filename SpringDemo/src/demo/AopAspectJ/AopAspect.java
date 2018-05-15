package demo.AopAspectJ;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.DeclareParents;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.annotation.After;

@Aspect()
public class AopAspect {
/* 	
	@Pointcut(value="execution(* demo.AopAspectJ.*.helloService(..)) && args(para1,para2)", argNames = "para1,para2")
    public void beforePointcut(String para3,String para4) {}

	@Before(value = "beforePointcut(para3,para4)", argNames = "para3,para4")
	public void beforeInstance(JoinPoint jp, String para5, String para6){
		System.out.println("This is the beforeInstance method, para1 = "+para5+" para2 = "+para6);
	}
	
	//same function as the previous one
	@Before(value = "execution(* demo.AopAspectJ.*.helloService(..)) && args(para1,para2)", argNames = "para1,para2")
	public void beforeInstance(String para1, String para2){
		System.out.println("before advice: para1 = "+para1+" para2 = "+para2);
	}
	
	@Before(value="args(para1,para2)", argNames = "para1,para2")
	public void beforeAllParameter(String para1, String para2){
		System.out.println("this is the beforeAllParameter method, para1 = "+para1+" para2 = "+para2);
	}
*/	
	
	@Before(value="args(pa1,pa2) && target(bean)", argNames = "pa1,pa2,bean")
	public void beforeAllTypes(JoinPoint jp, String para1, String para2, IAopDemoService aopDemoService){
		System.out.println("this is the beforeAllTypes method, para1 = "+para1+" para2 = "+para2);
		aopDemoService.other();
//		System.out.println("Aim object is "+jp.getTarget());
//		System.out.println("AOP object is "+jp.getThis());
	}
	
	@AfterReturning(value="execution(* demo.AopAspectJ.*.helloService(..))", argNames="retVal", returning="retVal")
	public void afterReturningInstance(boolean retVal) {
        System.out.println("This is the afterReturningInstance method, after advice: "+retVal);
    }
	
	@After(value="execution(* demo.AopAspectJ.*.helloTest(*)) and args(para)", argNames="para")
	public void afterFinallyAdvice(String para){
		System.out.println("this is the afterFinallyAdvice method, para = "+para);
	}	
	
	//The current AOP object(IAopDemoService object) has introduced the IIntroduction1 interface, 
	//so any method in IAopDemoService object can match this informed method below.
	//If use "target" to replace "this", only the method in a class which extends IIntroduction1 interface can match this informed method below.
	@After(value="this(demo.AopAspectJ.IIntroduction1)")
	public void afterFinallyAdvice_alt(){
		System.out.println("this is the afterFinallyAdvice_alt method.");
	}	
	
	@Around(value="execution(* demo.AopAspectJ.*.listService(..)) and args(para)", argNames="para")
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
	
	//AopIntroduction
	@DeclareParents(value="demo.AopAspectJ.AopDemoService", defaultImpl=demo.AopAspectJ.Introduction1.class)  
    public IIntroduction1 introduction1;  
    @DeclareParents(value="demo.AopAspectJ.IAopDemoService+", defaultImpl=demo.AopAspectJ.Introduction2.class)  
    public IIntroduction2 introduction2;  

}
