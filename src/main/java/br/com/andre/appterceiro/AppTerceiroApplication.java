package br.com.andre.appterceiro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class AppTerceiroApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppTerceiroApplication.class, args);
	}

}
