package tramways.inbound;

import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.Collections;
import java.util.Map;

public class RestUtils {

    private RestUtils(){

    }

    public static Response ok(Object entity) {
        return ok(entity, Collections.emptyMap());
    }

    public static Response ok(Object entity, Map<String, String> headers) {
        Response.ResponseBuilder builder = Response.ok(entity);
        headers.forEach(builder::header);
        return builder.build();
    }

    public static Response unauthorized(String message) {
        return Response.status(401).entity(message).build();
    }

    public static Response created(String uri, Object entity){
        return Response.created(URI.create(uri))
                .entity(entity)
                .build();
    }

}
