package Inter;

import java.rmi.Remote;

import Models.Service;

public interface InterInterface extends Remote {

	Service redirectAPI(String reference) throws Exception;
}
