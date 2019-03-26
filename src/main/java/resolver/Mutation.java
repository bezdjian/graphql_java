package resolver;

import com.coxautodev.graphql.tools.GraphQLRootResolver;
import pojo.Link;
import pojo.Person;
import repository.LinkRepository;
import repository.PersonRepository;

import java.util.List;

public class Mutation implements GraphQLRootResolver {

    private final LinkRepository linkRepository;
    private final PersonRepository personRepository;

    public Mutation(LinkRepository linkRepository, PersonRepository personRepository) {
        this.linkRepository = linkRepository;
        this.personRepository = personRepository;
    }

    public Link createLink(String url, String desc){
        Link link = new Link(url, desc);
        linkRepository.saveLink(link);
        return link;
    }

    public Person createPerson(String name, String job, String address){
        Person person = new Person(name, job, address);
        personRepository.savePerson(person);
        return person;
    }

    public List<Link> deleteLink(String url){
        linkRepository.deleteLink(url);
        return linkRepository.getAllLinks();
    }
}
