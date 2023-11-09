package Server5;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;

import javax.naming.InitialContext;

import CentralServer.CentralServerInterface;
import Models.Server;
import Models.Service;

public class Server5 extends Thread {

	public void run() {
		
		try {
			Server s5 = new Server(5,"Server5",8085,5);
			boolean run = true;
			ServerSocket server5 = new ServerSocket(8085);
			Properties props = new Properties();
			props.put("java.naming.factory.url.pkgs","org.jboss.ejb.client.naming");
			props.put("jboss.naming.client.ejb.context",true);
			InitialContext context = new InitialContext(props);
			
			CentralServerInterface centralServer = (CentralServerInterface)context.lookup("ejb:/CentralServer/CentralServer!CentralServer.CentralServerInterface");
			centralServer.saveServers(s5);
			
			while(run) {
				Socket con = server5.accept();
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
			
			server5.close();
			
		}catch(Exception e) {System.out.println("Exception"+e.toString());}

	}
}
