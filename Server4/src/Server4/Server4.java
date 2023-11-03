package Server4;

import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server4 extends Thread {

	public void run() {
		
		try {
			
			boolean run = true;
			ServerSocket server4 = new ServerSocket(8084);
			
			while(run) {
				Socket con = server4.accept();
				ObjectInputStream in = new ObjectInputStream(con.getInputStream());
				String ReceiveData = (String) in.readObject();
				System.out.println(ReceiveData);
			}
			
			server4.close();
			
		}catch(Exception e) {System.out.println("Exception"+e.toString());}

	}

}
