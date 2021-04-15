package covid;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class WebApp {
    
    public static void main(String[] args) {
        /*
        SpringApplication.run(WebApp.class, args);
        System.out.println("Initilized");
*/
        SpringApplication.run(WebApp.class);
    }
}