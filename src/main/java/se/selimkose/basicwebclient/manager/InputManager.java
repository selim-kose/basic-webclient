package se.selimkose.basicwebclient.manager;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import se.selimkose.basicwebclient.entity.Person;
import se.selimkose.basicwebclient.service.PersonService;
import java.util.List;
import java.util.Scanner;

@Component
@AllArgsConstructor
public class InputManager {
    PersonService personService;
    Scanner scanner;

    public void getPersonByID() {
        System.out.print("Please enter Id for the person to get >>>");
        String id = scanner.nextLine();

        System.out.println(personService.getPerson(id).block());
    }

    public void getAllPersons() {
        List<Person> allPersons = personService.getAllPersons().collectList().block();

        for (Person person: allPersons) {
            System.out.println(person);
        }
    }

    public void addPerson(){
        Person person = new Person();

        System.out.print("Enter firstname   >>>");
        person.setFirstName(scanner.nextLine());

        System.out.print("Enter lastname   >>>");
        person.setLastName(scanner.nextLine());

        System.out.print("Enter age   >>>");
        person.setAge(Integer.valueOf(scanner.nextLine()));

        personService.postPerson(person).block();

        System.out.println(person.getFirstName() + " added");

    }

    public void updatePerson() {
        System.out.println("Enter id for the person you want to change");
        String id = scanner.nextLine();
        Person person = personService.getPerson(id).block();

        System.out.print("Enter new firstname   >>>");
        person.setFirstName(scanner.nextLine());

        System.out.println("Enter new lastname   >>>");
        person.setLastName(scanner.nextLine());

        System.out.println("Enter new age   >>>");
        person.setAge(Integer.valueOf(scanner.nextLine()));

        personService.putPerson(person,id);
    }

    public void deletePerson() {

        System.out.println("Enter id for the person you want to delete");
        String id = scanner.nextLine();

        personService.deletePerson(id);

    }
}
