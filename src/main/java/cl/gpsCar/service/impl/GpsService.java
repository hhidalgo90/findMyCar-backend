package cl.gpsCar.service.impl;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessagingException;

import cl.gpsCar.persistence.ServicioMensajeriaFirebase;
import cl.gpsCar.service.IGpsService;

@Service
public class GpsService implements IGpsService {
	private static final int LIMITE_DISTANCIA_PERMITIDO = 50;
	Logger logger = LoggerFactory.getLogger(GpsService.class);
	
	@Autowired
	CronCoordenadasAuto serviceCron;
	
	@Autowired
	ServicioMensajeriaFirebase serviceMensajeria;

	@Override
	public boolean obtenerDistanciaEntrePuntos(String coordenadas) {
		logger.info("[GpsService][obtenerDistanciaEntrePuntos] Inicio: " + coordenadas);
		boolean respuesta = false;
		
		int distanciaEntrePuntos = 0;
		double latitud = 0;
		double longitud = 0;
		double latitudAuto = 0;
		double longitudAuto = 0;
		String[] coordenadasAuto = serviceCron.obtenerCoordenadasAuto();
		
		if(coordenadas != null) {
			String[] ltnLgn = coordenadas.split(",");
			latitud = Double.parseDouble(ltnLgn[0]);
			longitud = Double.parseDouble(ltnLgn[1]);
		}
		
		if (coordenadasAuto != null) {
			latitudAuto = Double.parseDouble(coordenadasAuto[0]);
			longitudAuto = Double.parseDouble(coordenadasAuto[1]);
		}
		
		distanciaEntrePuntos = (int) getDistanceFromLatLonInKm(latitud, longitud, latitudAuto, longitudAuto);
		logger.info("[GpsService][obtenerDistanciaEntrePuntos] fin: " + distanciaEntrePuntos);
		
		if(distanciaEntrePuntos > LIMITE_DISTANCIA_PERMITIDO) {
			respuesta = true;
		}
		return respuesta;
	}

	private String[] obtenerCoordenadasAuto() {
		// TODO Auto-generated method stub
		return null;
	}

	private double getDistanceFromLatLonInKm(double lat1, double lon1, double lat2, double lon2) {
		var R = 6371; // Radius of the earth in km
		var dLat = deg2rad(lat2 - lat1); // deg2rad below
		var dLon = deg2rad(lon2 - lon1);
		var a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
				+ Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.sin(dLon / 2) * Math.sin(dLon / 2);
		var c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		var d = R * c; // Distance in km
		return d;
	}

	private double deg2rad(double deg) {
		return deg * (Math.PI / 180);
	}

	@Override
	public String enviarMensajePush(String token) {
		logger.info("[GpsService][enviarMensajePush] Inicio, token: " + token);
		ResponseEntity<String> respuestaServicio = null;
		
		respuestaServicio = serviceMensajeria.enviarMensajePush(token);
		
		logger.info("[GpsService][enviarMensajePush] Fin, respuesta servicio : " + respuestaServicio.toString());

	    return respuestaServicio.toString();
	}

}
