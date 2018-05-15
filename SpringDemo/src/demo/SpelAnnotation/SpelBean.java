package demo.SpelAnnotation;

import org.springframework.beans.factory.annotation.Value;

public class SpelBean {
    @Value("#{'Hello ' + @world}")
    private String value; 
        
    public void setValue(String value) {
        this.value = value;
    }
        
    public String getValue() {
        return value;
    }
}
