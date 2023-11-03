package Inter;

import java.io.ObjectOutputStream;
import java.net.Socket;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Arrays;
import java.util.List;

public class Inter extends java.rmi.server.UnicastRemoteObject implements InterInterface {

	public Inter() throws Exception {}
	
	private List<String> Server1 = Arrays.asList("S0", "S1", "S2", "S3");
	private List<String> Server2 = Arrays.asList("S4", "S5", "S6");
	private List<String> Server3 = Arrays.asList("S7", "S8", "S9");
	private List<String> Server4 = Arrays.asList("S10", "S11", "S12");
	private List<String> Server5 = Arrays.asList("S13", "S14", "S15");

	@Override
	public void redirectAPI(String reference) throws Exception {
		
		int ServerNum = 0;
		int port = 0;
		
		if(Server1.contains(reference)) {
			ServerNum = 1;
		}else {
			if(Server2.contains(reference)) {
				ServerNum = 2;
			}else {
				if(Server3.contains(reference)) {
					ServerNum = 3;
				}else {
					if(Server4.contains(reference)) {
						ServerNum = 4;
					}else {
						if(Server5.contains(reference)) {
							ServerNum = 5;
						}
					}
				}
			}
		}
		
		switch (ServerNum) {
        case 1:
            port = 8081;
            break;

        case 2:
        	port = 8082;
            break;

        case 3:
        	port = 8083;
            break;

        case 4:
        	port = 8084;
            break;

        case 5:
        	port = 8085;
            break;
		}
		
		System.out.println("API directed to Server "+ServerNum);
		
		Socket c = new Socket("127.0.0.1", port);
		ObjectOutputStream out = new ObjectOutputStream(c.getOutputStream());
		out. writeObject("Server "+ServerNum+": Reference "+reference+" has been sent ");
		out.close();
		c.close();
	}
	
	public static void main(String[] args) {
		try {
			Inter inter = new Inter();
			Registry reg = LocateRegistry.createRegistry(4000);
			reg.rebind("Refp", inter);
			
			
		}catch(Exception e) {System.out.println("Exception"+e.toString());}
	}

}
