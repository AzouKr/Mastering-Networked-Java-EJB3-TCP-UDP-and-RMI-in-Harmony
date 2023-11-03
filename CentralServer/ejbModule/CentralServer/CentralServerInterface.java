package CentralServer;

import jakarta.ejb.Remote;

@Remote
public interface CentralServerInterface {
	
	void HandelServersAPIs(String req);
}
