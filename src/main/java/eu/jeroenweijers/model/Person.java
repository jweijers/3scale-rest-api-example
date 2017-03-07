package eu.jeroenweijers.model;

import eu.jeroenweijers.adapter.UUIDAdapter;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.UUID;

@XmlRootElement
public class Person {

    @XmlJavaTypeAdapter(UUIDAdapter.class)
    private UUID id;
    private String firstName;
    private String lastName;

    public Person() {
    }

    public Person(UUID id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public UUID getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}