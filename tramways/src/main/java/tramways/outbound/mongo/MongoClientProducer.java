package tramways.outbound.mongo;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import static tramways.outbound.mongo.Configuration.MONGO_URI;

public class MongoClientProducer {

    @Inject
    @Named(MONGO_URI)
    private String mongoURI;

    @Produces
    @ApplicationScoped
    public MongoClient createMongoClient() {
        LoggerFactory.getLogger(getClass()).info("Creating mongo client {}", mongoURI);
        CodecRegistry pojoCodecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));
        return new MongoClient(mongoURI, MongoClientOptions.builder()
                .codecRegistry(pojoCodecRegistry).build());
    }

}
