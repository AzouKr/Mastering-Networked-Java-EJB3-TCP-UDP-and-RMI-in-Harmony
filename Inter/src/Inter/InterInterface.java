package Inter;

import java.rmi.Remote;

public interface InterInterface extends Remote {

	void redirectAPI(String reference) throws Exception;
}
