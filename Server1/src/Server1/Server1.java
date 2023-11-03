package Server1;

import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server1 extends Thread {

	public void run() {
		
		try {
			
			boolean run = true;
			ServerSocket server1 = new ServerSocket(8081);
			
			while(run) {
				Socket con = server1.accept();
				ObjectInputStream in = new ObjectInputStream(con.getInputStream());
				String ReceiveData = (String) in.readObject();
				System.out.println(ReceiveData);
			}
			
			server1.close();
			
		}catch(Exception e) {System.out.println("Exception"+e.toString());}

	}

}
