package tramways.outbound.mongo;

import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@ApplicationScoped
public class Configuration {

    public static final String MONGO_URI = "mongoURI";

    private Properties properties;

    public Configuration(){
        properties = new Properties();
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("config.properties");
            if(inputStream != null){
                properties.load(inputStream);
            }
        } catch (IOException e) {
            LoggerFactory.getLogger(getClass()).error("Error", e);
        }
    }

    @Produces
    @Named(MONGO_URI)
    public String getMongoURI(){
        return properties.getProperty(MONGO_URI);
    }

}
