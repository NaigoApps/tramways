package tramways.rs;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

@Path("/hello")
public class HelloRestService {

	@GET
	@Path("/hello")
	public String hello(@QueryParam("name") String name) {
		return "Hello " + name;
	}
}
