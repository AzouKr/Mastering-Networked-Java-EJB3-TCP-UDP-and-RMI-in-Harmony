package Client6;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Client6.RandomTokenGenerator;
import Inter.InterInterface;
import Models.Service;

public class Client6 extends Thread {

	public void run() {
		
		        
		
		try {
			
			MyTimer timer = new MyTimer();
			boolean run = true;
			String[] serviceInfo = new String[3];
			DatagramSocket client6 = new DatagramSocket(3005);
			RandomTokenGenerator RTG = new RandomTokenGenerator();
			int tokenLength = 8; // Change this to the desired length of your token
			String Token = RTG.generateRandomToken(tokenLength);
			byte[] sendToken = new byte[10];
			byte[] receiveToken = new byte[10];
			List<String> Client6Ref = new ArrayList<>(Arrays.asList("S5", "S4", "S1", "S3", "S10", "S7", "S6", "S0", "S13", "S2", "S1", "S7", "S6", "S3","S15","S2","S1", "S14", "S5", "S6", "FIN"));
			
			// Sending API to Inter
			Registry reg = LocateRegistry.getRegistry("127.0.0.1", 4000);
			InterInterface inter = (InterInterface) reg.lookup("Refp");
			
			timer.start(120000, null);
			
			while(run) {
				DatagramPacket Packet = new DatagramPacket(receiveToken, receiveToken.length);
				client6.receive(Packet);
				Token = new String(Packet.getData());
				timer.reset();

				
				if(Token != "reset") {
					
					if(Client6Ref.get(0).equalsIgnoreCase("FIN")) {
						//run = false;
						System.out.println("Client 1 already terminated his job");
					}else {
						Thread.sleep(2000);
						System.out.println("3005 sent API to Inter");
						Service service = inter.redirectAPI(Client6Ref.get(0));
						System.out.println("Information received regarding the service: "+Client6Ref.get(0));
						System.out.println("Number: " + service.getNumber() + ", Name: " + service.getName() + ", Description: "+ service.getDescription());
						Client6Ref.remove(0);
						
						sendToken = Token.getBytes();
						InetAddress ip = InetAddress.getByName("localhost");
						Packet = new DatagramPacket(sendToken, sendToken.length, ip, 3000);
						client6.send(Packet);
					}
				}else {
					timer.start(120000, null);
				}
				
			}
			
			client6.close();
			System.out.println("Client 5 Terminate his job");
			
		}catch(Exception e) {System.out.println("Exception"+e.toString());}

	}

}
