package se.selimkose.basicwebclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Scanner;

@SpringBootApplication
public class BasicWebclientApplication {

    public static void main(String[] args) {
        SpringApplication.run(BasicWebclientApplication.class, args);
    }

    @Bean
    public WebClient webClient() {
        return WebClient.create("http://localhost:8080/api/");
    }

    @Bean
    public Scanner scanner() {
        return new Scanner(System.in);
    }

    ;


}
