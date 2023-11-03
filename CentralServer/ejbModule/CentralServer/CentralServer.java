package CentralServer;

import jakarta.ejb.Stateless;

@Stateless
public class CentralServer implements CentralServerInterface {

	
	public void HandelServersAPIs(String req) {
		System.out.println(req);
	}

}
