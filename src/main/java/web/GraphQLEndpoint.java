package web;

import com.coxautodev.graphql.tools.SchemaParser;
import com.mongodb.DBCollection;
import db.MongoDB;
import exception.SanitizedError;
import graphql.ExceptionWhileDataFetching;
import graphql.GraphQLError;
import graphql.GraphQLException;
import graphql.schema.GraphQLSchema;
import graphql.servlet.SimpleGraphQLServlet;
import lombok.extern.slf4j.Slf4j;
import repository.LinkRepository;
import repository.PersonRepository;
import resolver.Mutation;
import resolver.Query;

import javax.servlet.annotation.WebServlet;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = "/graphql")
@Slf4j
public class GraphQLEndpoint extends SimpleGraphQLServlet {

    private static final LinkRepository linkRepository;
    private static final PersonRepository personRepository;

    static {
        DBCollection collectionLink = MongoDB.connect("link");
        DBCollection collectionPersons = MongoDB.connect("person");
        linkRepository = new LinkRepository(collectionLink);
        personRepository = new PersonRepository(collectionPersons);
    }

    public GraphQLEndpoint() {
        super(buildSchema());
    }

    private static GraphQLSchema buildSchema() {
        return SchemaParser.newParser().file("schema.graphqls")
                .resolvers(new Query(linkRepository, personRepository), new Mutation(linkRepository, personRepository))
                .build().makeExecutableSchema();
    }

    @Override
    protected List<GraphQLError> filterGraphQLErrors(List<GraphQLError> errors) {
        log.info("filterGraphQLErrors");
        return errors.stream()
                // .filter(e -> e instanceof ExceptionWhileDataFetching ||
                // super.isClientError(e))
                .map(e -> e instanceof GraphQLException ? new SanitizedError((ExceptionWhileDataFetching) e) : e)
                .collect(Collectors.toList());
    }
}
