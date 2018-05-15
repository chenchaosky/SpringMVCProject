package demo.ZeroConfiguration;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration("ctx")
@ComponentScan
public class ApplicationContextConfig {
	
	@Bean(name="movieService", initMethod="InitMethod", destroyMethod="DestroyMethod")
	@Scope("singleton")
    public MovieService getMovieService() {
        return new MovieService();
    }
	
	@Bean
	@Scope("singleton")
	public String Displaying(){
		return new String("Hi Eric!");
	}
	
	@Bean(name="cinema", autowire=Autowire.BY_NAME)
	@Scope("singleton")
	public Cinema getCinema(){
		return new Cinema();
	}
}
