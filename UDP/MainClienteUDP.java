package UDP;

import java.io.IOException;

public class MainClienteUDP {
	
	public static void main(String[] args) throws IOException {
		ClienteUDP C = new ClienteUDP("127.0.0.1",7777);
		
		C.CPalabras();
		
	}
}
