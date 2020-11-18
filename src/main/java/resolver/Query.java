package resolver;

import com.coxautodev.graphql.tools.GraphQLRootResolver;
import model.Link;
import model.Person;
import repository.LinkRepository;
import repository.PersonRepository;

import java.util.List;

public class Query implements GraphQLRootResolver {

    private final LinkRepository linkRepository;
    private final PersonRepository personRepository;

    public Query(LinkRepository linkRepository, PersonRepository personRepository) {
        this.linkRepository = linkRepository;
        this.personRepository = personRepository;
    }

    public List<Link> allLinks() {
        return linkRepository.getAllLinks();
    }

    public List<Person> allPersons(String name) {
        return personRepository.getAllPersons(name);
    }
}
