package tramways.rs;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.slf4j.LoggerFactory;

@Provider
public class TramwaysExceptionMapper implements ExceptionMapper<Throwable>{

	@Override
	public Response toResponse(Throwable exception) {
		LoggerFactory.getLogger(getClass()).error("Error", exception);
		return Response.status(400).entity(exception.getMessage()).build();
	}

}
