package tramways.inbound;

import tramways.inbound.model.Action;
import tramways.inbound.model.ActionMethod;

import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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

    public static Action action(String name, String uri) {
        Action action = new Action();
        action.setName(name);
        action.setUri(uri);
        return action;
    }

    public static Action action(String name, String description, ActionMethod method, String uri) {
        Action action = new Action();
        action.setName(name);
        action.setDescription(description);
        action.setMethod(method);
        action.setUri(uri);
        return action;
    }

    public static ActionsBuilder actions(String base, String id){
        return new ActionsBuilder(base, id);
    }

    public static class ActionsBuilder {

        private String base;
        private String id;

        private List<Action> actions;

        public ActionsBuilder(String base, String id) {
            this.base = base;
            this.id = id;
            actions = new ArrayList<>();
        }

        public ActionsBuilder action(String name, String action){
            Action result = new Action();
            result.setName(name);
            result.setUri(base + "/" + id + "/" + action);
            actions.add(result);
            return this;
        }

        public List<Action> getActions() {
            return actions;
        }
    }
}
