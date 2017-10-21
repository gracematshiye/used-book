package za.ac.tut.usedbook.usedbook;

//import org.slf4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.logging.Logger;

@SpringBootApplication
public class UsedBookApplication {
	private static final Logger logger = Logger.getLogger(UsedBookApplication.class.getName());


	public static void main(String[] args) {
		SpringApplication.run(UsedBookApplication.class, args);
		logger.info("--Application Started--");
	}
}
