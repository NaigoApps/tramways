package tramways.rs;

import javax.ws.rs.OPTIONS;
import javax.ws.rs.Path;

@Path("*")
public class ApplicationRS {

	@OPTIONS
	public void options() {
		//Nothing to do
	}

}
