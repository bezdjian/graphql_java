import com.coxautodev.graphql.tools.SchemaParser;
import com.mongodb.DBCollection;
import db.MongoDB;
import graphql.schema.GraphQLSchema;
import graphql.servlet.SimpleGraphQLServlet;
import repository.LinkRepository;
import repository.PersonRepository;
import resolver.Mutation;
import resolver.Query;

import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns = "/graphql")
public class GraphQLEndpoint extends SimpleGraphQLServlet {

    private static LinkRepository linkRepository;
    private static PersonRepository personRepository;

    static {
        DBCollection collectionLink = MongoDB.connect("link");
        DBCollection collectionPersons = MongoDB.connect("person");
        linkRepository = new LinkRepository(collectionLink);
        personRepository  = new PersonRepository(collectionPersons);
    }

    public GraphQLEndpoint() {
        super(buildSchema());
    }

    private static GraphQLSchema buildSchema() {
        return SchemaParser.newParser()
                .file("schema.graphqls")
                .resolvers(new Query(linkRepository, personRepository),
                        new Mutation(linkRepository, personRepository))
                .build()
                .makeExecutableSchema();
    }
}
