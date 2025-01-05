package com.teamdevroute.devroute;

import com.teamdevroute.devroute.video.QueryDslConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableJpaAuditing
@EnableScheduling
@SpringBootApplication
//@Import(QueryDslConfig.class)
public class DevRouteApplication {

	public static void main(String[] args) {
		SpringApplication.run(DevRouteApplication.class, args);
	}

}
