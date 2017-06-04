package aww.yeah;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Random;

@SpringBootApplication
@Configuration
public class TwoHundredApplication {
    public static void main(String[] args) {
        SpringApplication.run(TwoHundredApplication.class, args);
    }

    @Bean
    public Random getRandom(){
        return new Random();
    }
}
