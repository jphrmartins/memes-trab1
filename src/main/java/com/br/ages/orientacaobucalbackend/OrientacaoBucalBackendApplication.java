package com.br.ages.orientacaobucalbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

@SpringBootApplication
public class OrientacaoBucalBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrientacaoBucalBackendApplication.class, args);
	}

}
