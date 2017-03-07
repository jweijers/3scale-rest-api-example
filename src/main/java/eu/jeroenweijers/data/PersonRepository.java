package eu.jeroenweijers.data;

import eu.jeroenweijers.model.Person;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PersonRepository {

    List<Person> listPersons();

    Optional<Person> getPerson(UUID id);

    Person addPerson(String firstName, String lastName);

    boolean deletePerson(UUID id);

    Optional<Person> updatePerson(Person person);
}
