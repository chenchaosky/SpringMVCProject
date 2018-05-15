package demo.Generic_Injection_API;

@org.springframework.stereotype.Service
public class ABService implements Service<A, B> {
	public void show(){
		System.out.println("I am in ABService Class.");
	}
}
