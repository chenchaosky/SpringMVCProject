package demo.Generic_Injection_API;

@org.springframework.stereotype.Service
public class CDService implements Service<C, D> {
	public void show(){
		System.out.println("I am in CDService Class.");
	}
}
