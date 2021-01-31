package cl.gpsCar.service.impl;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.net.*;
import java.io.*;

@Service
@Configuration
@EnableScheduling
public class CronCoordenadasAuto {
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
	
	@Scheduled(fixedRate = 120000)
	public String[] obtenerCoordenadasAuto() {
		String[] coordenadas = null;
		String url = "gps.dyegoo.net";
		int port = 6100;
		try {
			startConnection(url, port);
			String resp = sendMessage("hola server");
			System.out.println(resp);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return coordenadas;
	}
	
	
    public void startConnection(String ip, int port) throws UnknownHostException, IOException {
        clientSocket = new Socket(ip, port);
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    public String sendMessage(String msg) throws IOException {
        out.println(msg);
        String resp = in.readLine();
        return resp;
    }

    public void stopConnection() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
    }

}
