package covid;

import covid.task.SchedulingTasksApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebApp {
    
    public static void main(String[] args) {
        SpringApplication.run(WebApp.class, args);
        System.out.println("Initilized");
        SpringApplication.run(SchedulingTasksApplication.class);
    }
}