package app.vrabia.musiccollectorservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "app.vrabia")
public class MusicCollectorServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MusicCollectorServiceApplication.class, args);
    }

}
