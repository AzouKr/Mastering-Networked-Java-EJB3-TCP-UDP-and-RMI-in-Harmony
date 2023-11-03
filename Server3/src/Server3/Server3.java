package Server3;

import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server3 extends Thread {

	public void run() {
		
		try {
			
			boolean run = true;
			ServerSocket server3 = new ServerSocket(8083);
			
			while(run) {
				Socket con = server3.accept();
				ObjectInputStream in = new ObjectInputStream(con.getInputStream());
				String ReceiveData = (String) in.readObject();
				System.out.println(ReceiveData);
			}
			
			server3.close();
			
		}catch(Exception e) {System.out.println("Exception"+e.toString());}

	}

}
