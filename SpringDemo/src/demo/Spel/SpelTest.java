package demo.Spel;

import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import demo.LookupMethodInjection.*;

@SuppressWarnings("unchecked")
public class SpelTest {
	
    public void testClassTypeExpression() throws InstantiationException, IllegalAccessException {
        ExpressionParser parser = new SpelExpressionParser();
        String expression = "T(demo.Spel.InfoPrinting)"; 
		Class<InfoPrinting> result = parser.parseExpression(expression).getValue(Class.class);
        result.newInstance().testMethod();
    }

    //invoke static method
    public void testFunctionExpression() throws SecurityException, NoSuchMethodException {
        ExpressionParser parser = new SpelExpressionParser();
        StandardEvaluationContext context = new StandardEvaluationContext();
       
        Method parseTestStaticMethod = InfoPrinting.class.getDeclaredMethod("testStaticMethod");    
       
        context.registerFunction("parseInt1", parseTestStaticMethod);
        context.setVariable("parseInt2", parseTestStaticMethod);
        String expression1 = "#parseInt1()";
        String expression2 = "#parseInt2()";
       
        parser.parseExpression(expression1).getValue(context);  
        parser.parseExpression(expression2).getValue(context);  
    }

    //SPEL value assignment
    public void testAssignExpression() {
        ExpressionParser parser = new SpelExpressionParser();
        EvaluationContext context = new StandardEvaluationContext("aaaa");
       
        String result1 = parser.parseExpression("#root='bbbb'").getValue(context, String.class);
        System.out.println("result1="+result1);
       
        String result2 = parser.parseExpression("#this='aaaa'").getValue(context, String.class);
        System.out.println("result2="+result2);

        context.setVariable("variable", "kkkk");
        String result3 = parser.parseExpression("#variable=#this").getValue(context, String.class);
        System.out.println("result3="+result3);
    }

    
    public void testPropertyExpression() {
        ExpressionParser parser = new SpelExpressionParser();
        Date date = new Date();
        StandardEvaluationContext context = new StandardEvaluationContext(date);
        context.setVariable("DateImpl", date);
        
        int result1 = parser.parseExpression("#DateImpl.Year").getValue(context, int.class);
        System.out.println("Year="+result1);

        Object result3 = parser.parseExpression("#root?.year").getValue(context, Object.class);
        System.out.println("Year="+result3);
        
        context.setRootObject(date);
        int result4 = parser.parseExpression("Year = 4").getValue(context, int.class);
        System.out.println("Year="+result4);
        
        parser.parseExpression("Year").setValue(context, 5);
        int result5 = parser.parseExpression("Year").getValue(context, int.class);
        System.out.println("Year="+result5);
    }
 

    public void testBeanExpression() {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("LookupMethodInjection.xml");
        ctx.refresh();
        ExpressionParser parser = new SpelExpressionParser();
        StandardEvaluationContext context = new StandardEvaluationContext();
        context.setBeanResolver(new BeanFactoryResolver(ctx));
        PrinterAlt printer = parser.parseExpression("@printerAlt").getValue(context,PrinterAlt.class);
        printer.print("aaa");
    }

    public void testSelectExpression() {
    	List<Integer> collection = new ArrayList<Integer>();
        collection.add(4);
        collection.add(5);
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("a", 1);
        map.put("b", 2);
        Set<Integer> setTemp = new HashSet<Integer>();
        setTemp.add(9);
        setTemp.add(10);
        
        SpelExpressionParser parser = new SpelExpressionParser();
        
        EvaluationContext context1 = new StandardEvaluationContext();
        context1.setVariable("collection", collection);
        List<Integer> result1 = parser.parseExpression("#collection.?[#this>4]").getValue(context1, List.class);
        for(Iterator<Integer> it = result1.iterator(); it.hasNext();) 
        	System.out.println(it.next());
        	
        	
        EvaluationContext context2 = new StandardEvaluationContext();
        context2.setVariable("map", map);
        Map<String, Integer> result2 = parser.parseExpression("#map.?[#this.key != 'a']").getValue(context2, Map.class);
        for (Map.Entry<String, Integer> entry : result2.entrySet())  
            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
        
        List<Integer> result3 = parser.parseExpression("#map.?[key != 'a'].![value+1]").getValue(context2, List.class);
        for(Iterator<Integer> it = result3.iterator(); it.hasNext();) 
        	System.out.println(it.next());
        
        EvaluationContext context4 = new StandardEvaluationContext();
        context4.setVariable("set", setTemp);
        Set<Integer> result4 = parser.parseExpression("#set").getValue(context4, Set.class);
        for(Iterator<Integer> it = result4.iterator(); it.hasNext();) 
        	System.out.println(it.next());
    }    

	public static void main(String[] args) throws InstantiationException, IllegalAccessException, SecurityException, NoSuchMethodException {
		// TODO Auto-generated method stub
		SpelTest spelTest = new SpelTest();
//		spelTest.testClassTypeExpression();
//		spelTest.testFunctionExpression();
		spelTest.testAssignExpression();
//		spelTest.testPropertyExpression();
//		spelTest.testBeanExpression();
//		spelTest.testSelectExpression();
	}
}
