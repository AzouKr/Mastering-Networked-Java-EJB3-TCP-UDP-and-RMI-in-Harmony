package Client2;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Client2.RandomTokenGenerator;
import Inter.InterInterface;

public class Client2 extends Thread {

	public void run() {
		
		        
		
		try {
			
			MyTimer timer = new MyTimer();
			boolean run = true;
			DatagramSocket client2 = new DatagramSocket(3001);
			RandomTokenGenerator RTG = new RandomTokenGenerator();
			int tokenLength = 8; // Change this to the desired length of your token
			String Token = RTG.generateRandomToken(tokenLength);
			byte[] sendToken = new byte[10];
			byte[] receiveToken = new byte[10];
			List<String> Client2Ref = new ArrayList<>(Arrays.asList("S15", "S1", "S4", "S2", "S3", "S6", "S7", "S10", "S1", "S13", "S6", "S14", "S2", "S0","S1","S3","S2", "S7", "S0", "S1", "FIN"));
			
			// Sending API to Inter
			Registry reg = LocateRegistry.getRegistry("127.0.0.1", 4000);
			InterInterface inter = (InterInterface) reg.lookup("Refp");
			
			timer.start(120000, null);
			
			while(run) {
				DatagramPacket Packet = new DatagramPacket(receiveToken, receiveToken.length);
				client2.receive(Packet);
				Token = new String(Packet.getData());
				timer.reset();

				
				if(Token != "reset") {
					
					if(Client2Ref.get(0).equalsIgnoreCase("FIN")) {
						//run = false;
						System.out.println("Client 2 already terminated his job");
					}else {
						Thread.sleep(2000);
						System.out.println("3001 sent API to Inter");
						inter.redirectAPI(Client2Ref.get(0));
						Client2Ref.remove(0);
						
						sendToken = Token.getBytes();
						InetAddress ip = InetAddress.getByName("localhost");
						Packet = new DatagramPacket(sendToken, sendToken.length, ip, 3002);
						client2.send(Packet);
					}
				}else {
					timer.start(120000, null);
				}
				
			}
			
			client2.close();
			System.out.println("Client 2 Terminate his job");
		}catch(Exception e) {System.out.println("Exception"+e.toString());}

	}

}
