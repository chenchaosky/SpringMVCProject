package demo.ZeroConfiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

//@Component
//@Scope("singleton")
public class Cinema {
	
//	@Autowired
//	@Qualifier(value="service")
    private MovieService movieService;

    public void printMovieName() {
        System.out.println(movieService.getMovieName());
    }

    public MovieService getMovieService() {
        return movieService;
    }

    public void setMovieService(MovieService movieService) {
        this.movieService = movieService;
    }
}
