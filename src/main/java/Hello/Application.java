package Hello;

/**
 * Created by quent on 02/12/2016.
 */
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        System.out.println(Facebook.Client.getAuthLink());
        System.out.println(Yammer.Client.getAuthLink());
        SpringApplication.run(Application.class, args);
    }
}