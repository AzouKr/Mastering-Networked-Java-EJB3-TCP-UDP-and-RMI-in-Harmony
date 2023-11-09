package Server1;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;

import javax.naming.InitialContext;

import CentralServer.CentralServerInterface;
import Models.Server;
import Models.Service;

public class Server1 extends Thread {

	public void run() {
		
		try {
			
			Server s1 = new Server(1,"Server1",8081,1);
			boolean run = true;
			ServerSocket server1 = new ServerSocket(8081);
			Properties props = new Properties();
			props.put("java.naming.factory.url.pkgs","org.jboss.ejb.client.naming");
			props.put("jboss.naming.client.ejb.context",true);
			InitialContext context = new InitialContext(props);
			
			CentralServerInterface centralServer = (CentralServerInterface)context.lookup("ejb:/CentralServer/CentralServer!CentralServer.CentralServerInterface");
			centralServer.saveServers(s1);
			
			while(run) {
				Socket con = server1.accept();
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
			server1.close();
			
		}catch(Exception e){e.printStackTrace();}

	}

}
