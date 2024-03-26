package com.ilovegogi.VoiceFinder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class VoiceFinderApplication {

	public static void main(String[] args) {
		SpringApplication.run(VoiceFinderApplication.class, args);
	}

}
