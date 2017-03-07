package eu.jeroenweijers.data;

import eu.jeroenweijers.model.Person;

import javax.annotation.PostConstruct;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Singleton
class PersonDatabase implements PersonRepository {

    private final Map<UUID, Person> persons = new HashMap<>();

    @PostConstruct
    public void init() {
        this.addPerson("Clark", "Kent");
        this.addPerson("Bruce", "Wayne");
        this.addPerson("Peter", "Parker");
        this.addPerson("Alfred", "Pennyworth");
    }

    @Override
    public List<Person> listPersons() {
        List<Person> list = new ArrayList<>(persons.size());
        persons.entrySet().forEach(entry -> list.add(entry.getValue()));
        return list;
    }

    @Override
    public Optional<Person> getPerson(UUID id) {
        if (persons.containsKey(id)) {
            return Optional.of(persons.get(id));
        }
        return Optional.empty();
    }

    @Override
    public Person addPerson(String firstName, String lastName) {
        UUID uuid = UUID.randomUUID();
        Person person = new Person(uuid, firstName, lastName);
        persons.put(uuid, person);
        return person;
    }

    @Override
    public boolean deletePerson(UUID id) {
        return persons.remove(id) != null;
    }

    @Override
    public Optional<Person> updatePerson(Person person) {
        Person persistedPerson = persons.get(person.getId());
        if (persistedPerson == null){
            return Optional.empty();
        }
        persistedPerson.setFirstName(person.getFirstName());
        persistedPerson.setLastName(person.getLastName());
        return Optional.of(persistedPerson);
    }
}
