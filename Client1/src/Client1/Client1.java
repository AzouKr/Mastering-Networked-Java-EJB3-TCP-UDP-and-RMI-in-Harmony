package Client1;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Inter.InterInterface;
import Models.Service;

public class Client1 extends Thread {

	public void run() {
		
        
		
		try {
			
			MyTimer timer = new MyTimer();
			boolean run = true;
			String[] serviceInfo = new String[3];
			DatagramSocket client1 = new DatagramSocket(3000);
			RandomTokenGenerator RTG = new RandomTokenGenerator();
			int tokenLength = 8; // Change this to the desired length of your token
			String Token = RTG.generateRandomToken(tokenLength);
			byte[] sendToken = new byte[10];
			byte[] receiveToken = new byte[10];
			List<String> Client1Ref = new ArrayList<>(Arrays.asList("S7", "S10", "S1", "S2", "S4", "S3", "S0", "S6", "S12", "S11", "S8", "S5", "S2", "S1","S0","S5","S1", "S7", "S9", "S2", "FIN"));

			
			// Sending API to Inter
			Registry reg = LocateRegistry.getRegistry("127.0.0.1", 4000);
			InterInterface inter = (InterInterface) reg.lookup("Refp");
			System.out.println("3000 sent API to Inter");
			Service service = inter.redirectAPI(Client1Ref.get(0));
			System.out.println("Information received regarding the service: "+Client1Ref.get(0));
			System.out.println("Number: " + service.getNumber() + ", Name: " + service.getName() + ", Description: "+ service.getDescription());
			Client1Ref.remove(0);
			
			
			
			// Start Sending Token
			sendToken = Token.getBytes();
			InetAddress ip = InetAddress.getByName("localhost");
			DatagramPacket Packet = new DatagramPacket(sendToken, sendToken.length, ip, 3001);
			client1.send(Packet);
	        timer.start(120000, null);
			
			while(run) {
				
				Packet = new DatagramPacket(receiveToken, receiveToken.length);
				client1.receive(Packet);
				Token = new String(Packet.getData());
				timer.reset();
				
				if(Token != "reset") {

					if(Client1Ref.get(0).equalsIgnoreCase("FIN")) {
						//run = false;
						System.out.println("Client 1 already terminated his job");
					}else {
						Thread.sleep(2000);
						System.out.println("3000 sent API to Inter");
						service = inter.redirectAPI(Client1Ref.get(0));
						System.out.println("Information received regarding the service: "+Client1Ref.get(0));
						System.out.println("Number: " + service.getNumber() + ", Name: " + service.getName() + ", Description: "+ service.getDescription());
						Client1Ref.remove(0);
						
						sendToken = Token.getBytes();
						ip = InetAddress.getByName("localhost");
						Packet = new DatagramPacket(sendToken, sendToken.length, ip, 3001);
						client1.send(Packet);
					}
				}else {
					timer.start(120000, null);
				}
				
			}
			
			client1.close();
			System.out.println("Client 1 Terminate his job");
		        
		}catch(Exception e) {System.out.println("Exception"+e.toString());}

	}

}
