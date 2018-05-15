package demo.AopAspectJ;

public class Introduction2 implements IIntroduction2 {

	@Override
	public double max(double a, double b) {
		// TODO Auto-generated method stub
		double result = a<=b?b:a;  
        System.out.println("max("+a+","+b+")="+result);  
        return result;  
	}
}
