package ClientApplication;

import Client1.Client1;
import Client2.Client2;
import Client3.Client3;
import Client4.Client4;
import Client5.Client5;
import Client6.Client6;

public class main {

	public static void main(String[] args) {
		
		Client1 client1 = new Client1();
		Client2 client2 = new Client2();
		Client3 client3 = new Client3();
		Client4 client4 = new Client4();
		Client5 client5 = new Client5();
		Client6 client6 = new Client6();
		
		client6.start();
		client5.start();
		client4.start();
		client3.start();
		client2.start();
		client1.start();
		

	}

}