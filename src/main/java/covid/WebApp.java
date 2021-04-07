package covid;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebApp {

	public static void main(String[] args) {
		SpringApplication.run(WebApp.class, args);
                
                // Timer
                Timer timer = new Timer();
                TimerTask dailyTask = new TimerTask() {
                    // On télécharge le fichier toutes les 24h
                    @Override
                    public void run() {
                        // Code
                        try {
                            InputStream input = new URL("https://covid.ourworldindata.org/data/owid-covid-data.csv").openStream();
                            Reader reader = new InputStreamReader(input, "UTF-8");
                        } catch (Exception ex) {
                            Logger.getLogger(WebApp.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                };
                // Timer de 24h
                timer.schedule(dailyTask, 01, 24*60*60*1000);
	}
}
