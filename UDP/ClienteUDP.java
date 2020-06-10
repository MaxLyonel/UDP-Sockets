package UDP;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;


public class ClienteUDP {
	int puerto;
	InetAddress servidorDestino;
	DatagramSocket socketUDP;
	DatagramPacket paqueteRecibido;
	DatagramPacket paqueteAEnviar;
	private Scanner sc;
	
	public ClienteUDP(final String h, final int p) throws UnknownHostException {
		servidorDestino = InetAddress.getByName(h);
		puerto = p;
	}	

	public void CPalabras() {
		try {
			sc = new Scanner(System.in);
			socketUDP = new DatagramSocket();

			System.out.println("*********CONEXION INICIADA************");
			boolean sw=true;
			while (sw) {

				System.out.print("Escriba la palabra: ");
				final String a = sc.nextLine() + "#";

				if (a.toUpperCase().equals("ADIOS#")) {
					
					sw=false;
				}

				byte mensajeE[] = new byte[1024];
				mensajeE = a.getBytes();
				final DatagramPacket palabra = new DatagramPacket(mensajeE, mensajeE.length, servidorDestino, puerto);
				socketUDP.send(palabra);

			}
			System.out.println("-----------MENSAJE DEL SERVIDOR");
			final byte mensajeR[] = new byte[1024];
			paqueteRecibido = new DatagramPacket(mensajeR, mensajeR.length);
			socketUDP.receive(paqueteRecibido);
			final String mensajeRecibido = new String(paqueteRecibido.getData());

			System.out.println(Integer.parseInt(recorte(mensajeRecibido)));
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	public void finalizar() {
		try {
			socketUDP.close();
			System.out.println("Conexion Finalizada...!!!");
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	public String recorte(final String s) {
		String m = "";
		final char c[] = s.toCharArray();
		boolean sw=true;
		for(int i=0;i<s.length()&&sw;i++){
			if(c[i]!='#'){
				m=m+c[i];
			}else{
				sw=false;
			}
		}
		return m;
	}
}
