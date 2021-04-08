package covid;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebApp {
    
    public static void main(String[] args) {
        SpringApplication.run(WebApp.class, args);
        
        Downloading dlOWD = new Downloading();
        dlOWD.runTimer();
        
    }
}