package cl.gpsCar.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.client.ClientProtocolException;

public interface IGpsService {

	public boolean obtenerDistanciaEntrePuntos(String coordenadas);
	
	public String enviarMensajePush(String token) throws UnsupportedEncodingException, ClientProtocolException, IOException;
}
