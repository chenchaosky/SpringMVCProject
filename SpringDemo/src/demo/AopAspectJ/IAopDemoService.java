package demo.AopAspectJ;

import java.util.List;

public interface IAopDemoService {
	public boolean helloService(String para1, String para2);
	public String listService(List<String> para);
	public void helloTest(String para);
//	public double min(double a, double b);
	public void other();
}
