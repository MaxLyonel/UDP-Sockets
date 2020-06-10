package UDP;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ServidorUDP {
	DatagramSocket socketUDP;
	int puerto;
	public ServidorUDP(final int p) {
		puerto = p;
	}

	public void Cpalabras() {
		try {
			socketUDP = new DatagramSocket(puerto);
			System.out.println(" ----- SERVIDOR UDP INICIADO -----");
			System.out.println("---- Esperando Solicitudes ----");

			int c = 0;
			boolean sw = true;
			while (sw) {

				///////////////////// RECIBIMOS EL DATAGRAMA DEL CLIENTE////////////////////////
				 DatagramPacket paqueteRecibido = new DatagramPacket(new byte[1024], 1024); 
				socketUDP.receive(paqueteRecibido); 
				 String mensajeRecibido = recorte(new String(paqueteRecibido.getData())); 

				if (!mensajeRecibido.toUpperCase().equals("ADIOS")) {
					mensajeRecibido=mensajeRecibido+" ";

					for (int i = 0; i < mensajeRecibido.length(); i++) {
						if ( mensajeRecibido.charAt(i) == ' ') {
							c++;
						}
					}

				} else {
					byte mensajeEnviar[] = new byte[5];
					 String mens = c + "#";
					mensajeEnviar = mens.getBytes();
					 DatagramPacket paqueteAEnviar = new DatagramPacket(mensajeEnviar, mensajeEnviar.length,
							paqueteRecibido.getAddress(), paqueteRecibido.getPort());
					socketUDP.send(paqueteAEnviar);
				}

			
			}
			finalizar();
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
