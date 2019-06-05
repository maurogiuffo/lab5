package utn.laboratorio5.parcial;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ParcialApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParcialApplication.class, args);
	}

}
