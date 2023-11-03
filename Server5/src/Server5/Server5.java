package Server5;

import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server5 extends Thread {

	public void run() {
		
		try {
			
			boolean run = true;
			ServerSocket server5 = new ServerSocket(8085);
			
			while(run) {
				Socket con = server5.accept();
				ObjectInputStream in = new ObjectInputStream(con.getInputStream());
				String ReceiveData = (String) in.readObject();
				System.out.println(ReceiveData);
			}
			
			server5.close();
			
		}catch(Exception e) {System.out.println("Exception"+e.toString());}

	}
}
