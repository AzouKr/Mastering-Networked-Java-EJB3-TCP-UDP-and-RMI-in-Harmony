package CentralServer;

import Models.Server;
import Models.Service;
import jakarta.ejb.Remote;

@Remote
public interface CentralServerInterface {
	
	public Service HandelServersAPIs(String req);
	public void saveServers(Server server);
}
