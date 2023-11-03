package Client5;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import Client5.RandomTokenGenerator;

public class MyTimer {
	
	 	private Timer timer;
	    private TimerTask task;
	    private long delay;
	    private boolean isRunning;
	    

	    // ****************************************************************************************************
	    public MyTimer() {
	        timer = new Timer();
	        isRunning = false;
	    }

	    
	 // ****************************************************************************************************
	    
	    public void start(long delayInMillis, Runnable action) {
	        if (isRunning) {
	            stop();
	        }

	        delay = delayInMillis;
	        task = new TimerTask() {
	            @Override
	            public void run() {
	            	List<Integer> list = new ArrayList<>();
	            	list.add(3000);list.add(3001);list.add(3002);list.add(3003);list.add(3005);
	            	byte[] sendToken = new byte[10];
	            	RandomTokenGenerator RTG = new RandomTokenGenerator();
	    			int tokenLength = 8; // Change this to the desired length of your token
	    			String Token = RTG.generateRandomToken(tokenLength);
	    			
	    			try {
	    				
	    				Random random = new Random();
		            	int randomIndex = random.nextInt(list.size());
		                int randomItem = list.get(randomIndex);
		    			DatagramSocket client1 = new DatagramSocket();
		                sendToken = Token.getBytes();
		    			InetAddress ip = InetAddress.getByName("localhost");
		    			DatagramPacket Packet = new DatagramPacket(sendToken, sendToken.length, ip, randomItem);
		    			client1.send(Packet);
		    			
		    			for(int i=0; i<list.size(); i++) {
		    				if(i != randomIndex) {
		    					Token = "reset";
			    				sendToken = Token.getBytes();
				    			ip = InetAddress.getByName("localhost");
				    			Packet = new DatagramPacket(sendToken, sendToken.length, ip, list.get(i));
				    			client1.send(Packet);
		    				}
		    			}
		    			
		    			
		    			client1.close();
		    			
	    			}catch(Exception e) {System.out.println("Exception"+e.toString());}
	            }
	        };

	        timer.schedule(task, delay);
	        isRunning = true;
	    }

	    
	 // ****************************************************************************************************
	    
	    
	    public void reset() {
	        if (isRunning) {
	            stop();
	            start(delay, task);
	        }
	    }

	    
	 // ****************************************************************************************************
	    
	    public void stop() {
	        if (isRunning) {
	            task.cancel();
	            isRunning = false;
	        }
	    }

	    
	 // ****************************************************************************************************
	    
	    public void close() {
	        stop();
	        timer.cancel();
	    }

	    
	 // ****************************************************************************************************
	    
	    public boolean isRunning() {
	        return isRunning;
	    }

}
