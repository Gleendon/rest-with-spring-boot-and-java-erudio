package br.com.glendon.services;

import br.com.glendon.model.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonServices {

    private final AtomicLong counter = new AtomicLong();

    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    public Person findById(String id){
        logger.info("Find one person!");

        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("Glendon");
        person.setLastName("Souza");
        person.setAddress("Feira de Santana - Bahia");
        person.setGender("Male");
        return person;
    }

    public List<Person> findAll() {
        logger.info("Finding all person!!");
        List<Person> persons = new ArrayList<>();

        for(int i=0; i<8; i++){
            Person person = mockPerson(i);
            persons.add(person);
        }

        return persons;
    }

    public Person create(Person person) {
        logger.info("Creating all person!!");

        return person;
    }

    public Person update(Person person) {
        logger.info("Updating all person!!");

        return person;
    }

    public void delete(String id) {
        logger.info("Deleting all person!!");
    }

    private Person mockPerson(int i) {

        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("Person name " + i);
        person.setLastName("Last name " + i);
        person.setAddress("Person address " + i);
        person.setGender("Female");

        return  person;
    }
}
