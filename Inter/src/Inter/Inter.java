package Inter;

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
		
		System.out.println("Goes to Server number :"+ServerNum);
		
		
	}
	
	public static void main(String[] args) {
		try {
			Inter inter = new Inter();
			Registry reg = LocateRegistry.createRegistry(8080);
			reg.rebind("Refp", inter);
			
			
		}catch(Exception e) {System.out.println("Exception"+e.toString());}
	}

}
