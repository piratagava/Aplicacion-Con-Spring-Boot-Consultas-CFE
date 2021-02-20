package com.cfe.hernan.sistema;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class CfeApplication {

	 @PostConstruct
     void started() {
       TimeZone.setDefault(TimeZone.getTimeZone("TimeZone"));
     }
	 
    public static void main(String[] args) {
        SpringApplication.run(CfeApplication.class, args);
    }
  
}

