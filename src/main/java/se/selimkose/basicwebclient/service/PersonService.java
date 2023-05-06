package se.selimkose.basicwebclient.service;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import se.selimkose.basicwebclient.entity.Person;

@Service
@AllArgsConstructor
public class PersonService {
   WebClient webClient;

    public Mono<Person> getPerson( String id) {
        return webClient.get()
                .uri(id)
                .retrieve()
                .onStatus(httpStatusCode -> HttpStatus.NOT_FOUND.equals(httpStatusCode),clientResponse -> Mono.empty())
                .bodyToMono(Person.class);
    }
    public Flux<Person> getAllPersons(){
        return webClient.get()
                .uri("all")
                .retrieve()
                .bodyToFlux(Person.class);
    }

    public Mono<Person> postPerson(Person person){
        return webClient.post()
                .uri("")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(person), Person.class)
                .retrieve()
                .bodyToMono(Person.class);
    }

    public void deletePerson(String id) {

         webClient.delete()
                .uri(id)
                .retrieve()
                .bodyToMono(Person.class);
    }

    public Mono<Person> putPerson(Person person, String id) {
        Person personToChange = getPerson(id).block();

        personToChange.setFirstName(person.getFirstName());
        personToChange.setLastName(person.getLastName());
        personToChange.setAge(person.getAge());

        return webClient.put()
                .uri(id)
                .body(Mono.just(person), Person.class)
                .retrieve()
                .bodyToMono(Person.class);

    }
}
