package Server2;

import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;

import javax.naming.InitialContext;

import CentralServer.CentralServerInterface;

public class Server2 extends Thread {

	public void run() {
		
		try {
			
			boolean run = true;
			ServerSocket server2 = new ServerSocket(8082);
			Properties props = new Properties();
			props.put("java.naming.factory.url.pkgs","org.jboss.ejb.client.naming");
			props.put("jboss.naming.client.ejb.context",true);
			InitialContext context = new InitialContext(props);
			
			while(run) {
				Socket con = server2.accept();
				ObjectInputStream in = new ObjectInputStream(con.getInputStream());
				String ReceiveData = (String) in.readObject();
				System.out.println(ReceiveData);
				
				CentralServerInterface centralServer = (CentralServerInterface)context.lookup("ejb:/CentralServer/CentralServer!CentralServer.CentralServerInterface");
				centralServer.HandelServersAPIs("Server 2 has accessed to the Database");
			}
			
			server2.close();
			
		}catch(Exception e) {System.out.println("Exception"+e.toString());}

	}

}
