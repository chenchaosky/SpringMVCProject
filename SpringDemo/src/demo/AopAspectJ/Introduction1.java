package demo.AopAspectJ;

public class Introduction1 implements IIntroduction1 {
	@Override  
    public double min(double a, double b) {  
        double result = a<=b?a:b;  
        System.out.println("min("+a+","+b+")="+result);  
        return result;  
    }  
}
