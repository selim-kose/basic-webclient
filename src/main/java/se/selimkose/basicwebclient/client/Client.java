package se.selimkose.basicwebclient.client;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import se.selimkose.basicwebclient.manager.InputManager;
import java.util.Scanner;

@Component
@AllArgsConstructor
public class Client implements CommandLineRunner {

    InputManager inputManager;
    Scanner scanner;


    @Override
    public void run(String... args) throws Exception {
        WebClient webClient = WebClient.create("http://localhost:8080/api/");

        while(true) {
            System.out.println("""
                    
                    Welcome to the Person Registry, please make a choice!
                         
                    1. Get person by id
                    2. Get all persons
                    3. Add a new person
                    4. Update existing person by id
                    5. Delete person by id
                    
                    e. Exit program
                    """);

            System.out.print("Enter here   >>>");
            String input = scanner.nextLine();

            switch (input) {
                case "1" -> inputManager.getPersonByID();
                case "2" -> inputManager.getAllPersons();
                case "3" -> inputManager.addPerson();
                case "4" -> inputManager.updatePerson();
                case "5" -> inputManager.deletePerson();
                case "e" -> System.exit(0);
                default -> System.out.println("Invalid input");
            }

        }

    }
}
