package eu.jeroenweijers.rest;

import eu.jeroenweijers.data.PersonRepository;
import eu.jeroenweijers.model.Person;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Path("/person")
public class PersonController {

    @Inject
    private PersonRepository persons;

    @GET
    @Path("/list")
    @Produces({"application/xml", "application/json"})
    public List<Person> listPersons() {
        return persons.listPersons();
    }

    @GET
    @Path("/{id}")
    @Produces({"application/xml", "application/json"})
    public Response getPerson(@PathParam("id") UUID id) {
        Optional<Person> person = persons.getPerson(id);
        if (person.isPresent()) {
            return Response.ok(person.get()).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    public Person addPerson(Person person){
        return persons.addPerson(person.getFirstName(), person.getLastName());
    }

    @DELETE
    @Path("/{id}")
    public Response deletePerson(@PathParam("id") UUID id){
        if (persons.deletePerson(id)){
            return Response.ok().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @PUT
    public Response updatePerson(Person person){
        Optional<Person> optionalPerson = persons.updatePerson(person);
        if (optionalPerson.isPresent()){
            return Response.ok(optionalPerson.get()).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }


}
