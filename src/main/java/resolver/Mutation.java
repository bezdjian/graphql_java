package resolver;

import com.coxautodev.graphql.tools.GraphQLRootResolver;
import pojo.Link;
import repository.LinkRepository;

public class Mutation implements GraphQLRootResolver {

    private final LinkRepository linkRepository;

    public Mutation(LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }

    public Link createLink(String url, String desc){
        Link link = new Link("99", url, desc);
        //linkRepository.saveLink(link);
        return link;
    }
}
