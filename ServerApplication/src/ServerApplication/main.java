package ServerApplication;

import Server1.Server1;
import Server2.Server2;
import Server3.Server3;
import Server4.Server4;
import Server5.Server5;

public class main {

	public static void main(String[] args) {
		
		Server1 server1 = new Server1();
		Server2 server2 = new Server2();
		Server3 server3 = new Server3();
		Server4 server4 = new Server4();
		Server5 server5 = new Server5();
		
		server1.start();
		server2.start();
		server3.start();
		server4.start();
		server5.start();
		
	}

}
