package com.kinger.iohackathon;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.io.UnsupportedEncodingException;


@EnableSwagger2
@SpringBootApplication
public class IoHackathonApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(IoHackathonApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }


}
