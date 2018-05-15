package demo.AopSchema;

import java.util.List;

public interface IAopDemoService {
	public boolean helloService(String para1, String para2);
	public String listService(List<String> para);
	public void helloTest(String para);
}
