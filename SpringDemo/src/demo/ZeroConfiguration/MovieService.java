package demo.ZeroConfiguration;
import org.springframework.beans.factory.annotation.Autowired;

public class MovieService implements IMovieService {

	@Autowired
	String displaying;
	
	public MovieService(){
		
	}
	
	public MovieService(String displaying){
		this.displaying = displaying;
	}
	
	public String getDisplaying() {
		return displaying;
	}

	public void setDisplaying(String displaying) {
		this.displaying = displaying;
	}

	@Override
	public String getMovieName() {
		// TODO Auto-generated method stub
		return displaying;
	}

	public void InitMethod(){
		System.out.println("this is the inital method!");
	}
	
	public void DestroyMethod(){
		System.out.println("this is the destroy method!");
	}
	
}
