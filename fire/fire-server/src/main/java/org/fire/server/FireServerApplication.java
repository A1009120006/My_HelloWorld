package org.fire.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * Hello world!
 *
 */
//@EnableAutoConfiguration
//@ComponentScan(basePackages={"org.fire.server.model1"})
@SpringBootApplication
public class FireServerApplication{

	public static void main(String[] args) throws Exception {
		SpringApplication.run(FireServerApplication.class, args);
	}

}

