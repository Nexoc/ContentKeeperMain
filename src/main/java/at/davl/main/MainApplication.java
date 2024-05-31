package at.davl.main;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.AbstractEnvironment;

@SpringBootApplication(scanBasePackages = "at.davl.main")
public class MainApplication {

	public static void main(String[] args) {
		System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, "multipledatasources");
		SpringApplication.run(MainApplication.class, args);
	}
}
