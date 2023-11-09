package Server2;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;

import javax.naming.InitialContext;

import CentralServer.CentralServerInterface;
import Models.Server;
import Models.Service;

public class Server2 extends Thread {

	public void run() {
		
		try {
			
			Server s2 = new Server(2,"Server2",8082,2);
			boolean run = true;
			ServerSocket server2 = new ServerSocket(8082);
			Properties props = new Properties();
			props.put("java.naming.factory.url.pkgs","org.jboss.ejb.client.naming");
			props.put("jboss.naming.client.ejb.context",true);
			InitialContext context = new InitialContext(props);
			
			CentralServerInterface centralServer = (CentralServerInterface)context.lookup("ejb:/CentralServer/CentralServer!CentralServer.CentralServerInterface");
			centralServer.saveServers(s2);
			
			while(run) {
				Socket con = server2.accept();
				ObjectInputStream in = new ObjectInputStream(con.getInputStream());
				String ReceiveData = (String) in.readObject();
				
				centralServer = (CentralServerInterface)context.lookup("ejb:/CentralServer/CentralServer!CentralServer.CentralServerInterface");
				Service serviceinfo = centralServer.HandelServersAPIs(ReceiveData);
				
				Socket c = new Socket("localhost", 4001);
				ObjectOutputStream out = new ObjectOutputStream(c.getOutputStream());
				out. writeObject(serviceinfo);
				out.close();
				c.close();
			}
			
			server2.close();
			
		}catch(Exception e) {System.out.println("Exception"+e.toString());}

	}

}
