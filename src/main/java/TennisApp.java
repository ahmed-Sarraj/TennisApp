import adapter.in.cli.ConsoleTennisGameRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

/**
 * Point d'entrée principal de l'application TennisApp.
 * <p>
 * Cette classe configure et lance l'application Spring Boot,
 * initialise le contexte Spring et démarre le runner CLI pour permettre à l'utilisateur
 * de jouer une partie de tennis via la console.
 */

@SpringBootApplication
@ComponentScan(basePackages = {"domain", "adapter", "application"})
public class TennisApp {
    public static void main(String[] args) {
        // Example usage of the Tennis application

        SpringApplication.run(TennisApp.class, args);

    }


    @Bean
    CommandLineRunner run(ConsoleTennisGameRunner consoleTennisGameRunner) {

        return args -> consoleTennisGameRunner.run();

    }

    ;
}
