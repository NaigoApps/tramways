package tramways;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.junit4.WeldInitiator;
import org.junit.Rule;

public abstract class CDITest {
	
	@Rule
	public WeldInitiator weld = WeldInitiator.from(new Weld()).inject(this).build();

}
