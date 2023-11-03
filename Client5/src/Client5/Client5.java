package Client5;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Client5.RandomTokenGenerator;
import Inter.InterInterface;

public class Client5 extends Thread {

	public void run() {
		
		        
		
		try {
			
			MyTimer timer = new MyTimer();
			boolean run = true;
			DatagramSocket client5 = new DatagramSocket(3004);
			RandomTokenGenerator RTG = new RandomTokenGenerator();
			int tokenLength = 8; // Change this to the desired length of your token
			String Token = RTG.generateRandomToken(tokenLength);
			byte[] sendToken = new byte[10];
			byte[] receiveToken = new byte[10];
			List<String> Client5Ref = new ArrayList<>(Arrays.asList("S2", "S14", "S1", "S7", "S6", "S3", "S0", "S5", "S2", "S15", "S7", "S0", "S4", "S12","S3","S6","S7", "S2", "S1", "S13", "FIN"));
			
			// Sending API to Inter
			Registry reg = LocateRegistry.getRegistry("127.0.0.1", 4000);
			InterInterface inter = (InterInterface) reg.lookup("Refp");
			
			timer.start(120000, null);
			
			while(run) {
				DatagramPacket Packet = new DatagramPacket(receiveToken, receiveToken.length);
				client5.receive(Packet);
				Token = new String(Packet.getData());
				timer.reset();

				
				if(Token != "reset") {
					
					if(Client5Ref.get(0).equalsIgnoreCase("FIN")) {
						//run = false;
						System.out.println("Client 5 already terminated his job");
					}else {
						Thread.sleep(2000);
						System.out.println("3004 sent API to Inter");
						inter.redirectAPI(Client5Ref.get(0));
						Client5Ref.remove(0);
						
						sendToken = Token.getBytes();
						InetAddress ip = InetAddress.getByName("localhost");
						Packet = new DatagramPacket(sendToken, sendToken.length, ip, 3005);
						client5.send(Packet);
					}
				}else {
					timer.start(120000, null);
				}
				
			}
			
			client5.close();
			System.out.println("Client 5 Terminate his job");
			
		}catch(Exception e) {System.out.println("Exception"+e.toString());}

	}

}
