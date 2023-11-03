package Client4;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Client4.RandomTokenGenerator;
import Inter.InterInterface;

public class Client4 extends Thread {

	public void run() {
		
		        
		
		try {
			
			MyTimer timer = new MyTimer();
			boolean run = true;
			DatagramSocket client4 = new DatagramSocket(3003);
			RandomTokenGenerator RTG = new RandomTokenGenerator();
			int tokenLength = 8; // Change this to the desired length of your token
			String Token = RTG.generateRandomToken(tokenLength);
			byte[] sendToken = new byte[10];
			byte[] receiveToken = new byte[10];
			List<String> Client4Ref = new ArrayList<>(Arrays.asList("S13", "S2", "S1", "S7", "S0", "S4", "S6", "S5", "S11", "S0", "S14", "S3", "S9", "S1","S0","S3","S6", "S7", "S12", "S11", "FIN"));
			
			// Sending API to Inter
			Registry reg = LocateRegistry.getRegistry("127.0.0.1", 4000);
			InterInterface inter = (InterInterface) reg.lookup("Refp");
			
			
			timer.start(120000, null);
			
			while(run) {
				DatagramPacket Packet = new DatagramPacket(receiveToken, receiveToken.length);
				client4.receive(Packet);
				Token = new String(Packet.getData());
				timer.reset();

				
				if(Token != "reset") {
					
					if(Client4Ref.get(0).equalsIgnoreCase("FIN")) {
						//run = false;
						System.out.println("Client 4 already terminated his job");
					}else {
						Thread.sleep(2000);
						System.out.println("3003 sent API to Inter");
						inter.redirectAPI(Client4Ref.get(0));
						Client4Ref.remove(0);
						
						sendToken = Token.getBytes();
						InetAddress ip = InetAddress.getByName("localhost");
						Packet = new DatagramPacket(sendToken, sendToken.length, ip, 3004);
						client4.send(Packet);
					}
				}else {
					timer.start(120000, null);
				}
				
			}
			
			client4.close();
			System.out.println("Client 4 Terminate his job");
			
		}catch(Exception e) {System.out.println("Exception"+e.toString());}

	}

}
