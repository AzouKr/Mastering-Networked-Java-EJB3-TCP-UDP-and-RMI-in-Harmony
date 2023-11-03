package Server2;

import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server2 extends Thread {

	public void run() {
		
		try {
			
			boolean run = true;
			ServerSocket server2 = new ServerSocket(8082);
			
			while(run) {
				Socket con = server2.accept();
				ObjectInputStream in = new ObjectInputStream(con.getInputStream());
				String ReceiveData = (String) in.readObject();
				System.out.println(ReceiveData);
			}
			
			server2.close();
			
		}catch(Exception e) {System.out.println("Exception"+e.toString());}

	}

}
