package demo.MethodReplaceInjection;

public class Printer {
	  private int counter = 0;
	    
	  public String print(String type) {
	        System.out.println(type + " printer: " + counter++);
	        return "this is Eric code!";
	  }
}
