package com.kinger.iohackathon;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Slf4j
@EnableSwagger2
@SpringBootApplication
public class IoHackathonApplication  {
    public static void main(String[] args) {
        SpringApplication.run(IoHackathonApplication.class, args);
        System.out.println("2323232" +  log.isInfoEnabled());
    }

}
