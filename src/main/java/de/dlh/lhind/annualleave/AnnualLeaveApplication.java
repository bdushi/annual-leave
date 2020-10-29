package de.dlh.lhind.annualleave;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
 * https://docs.spring.io/spring-boot/docs/current/reference/html/howto.html#howto-database-initialization
 * https://www.callicoder.com/spring-boot-log4j-2-example/
 * https://www.baeldung.com/spring-boot-logging
 */

@SpringBootApplication
public class AnnualLeaveApplication implements ApplicationRunner {
	// private static final Logger logger = LogManager.getLogger(AnnualLeaveApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(AnnualLeaveApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) {
//		logger.debug("Debugging log");
//		logger.info("Info log");
//		logger.warn("Hey, This is a warning!");
//		logger.error("Oops! We have an Error. OK");
//		logger.fatal("Damn! Fatal error. Please fix me.");
	}
}
