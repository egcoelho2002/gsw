package br.com.gsw.egctest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import br.com.gsw.egctest.configuration.JpaConfiguration;

@Import(JpaConfiguration.class)
@SpringBootApplication // (scanBasePackages={"br.com.gsw.egctest"})// same as @Configuration
		       // @EnableAutoConfiguration @ComponentScan
public class GSWSpringBootAngular {

    public static void main(String[] args) {
	SpringApplication.run(GSWSpringBootAngular.class, args);
    }
}
