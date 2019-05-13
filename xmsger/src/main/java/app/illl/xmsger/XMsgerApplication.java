package app.illl.xmsger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class XMsgerApplication {

	public static void main(String[] args) {
		SpringApplication.run(XMsgerApplication.class, args);
	}

}
