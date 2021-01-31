package cl.gpsCar.persistence;

import java.util.ArrayList;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import cl.gpsCar.model.FirebaseResponse;
import cl.gpsCar.model.HeaderRequestInterceptor;

@Service
public class ServicioMensajeriaFirebase extends HttpComponentsClientHttpRequestFactory {

	private static final String FIREBASE_SERVER_KEY = "AAAAoNYf5Zw:APA91bHh7uoVGEg8X5VUDGaL2o6FxhtFq6gYwB79wbf7POGbK2SXymPIz3I00inoGzjjZKd6cdED8UZjlxQ89Whp2nZnopNIDa0rsBHOnUIW8Vl_Z_Fc0hzHX9bgTtBNsuvnfctYQmI1";

	private static final String FIREBASE_API_URL = "https://fcm.googleapis.com/fcm/send";

	Logger logger = LoggerFactory.getLogger(ServicioMensajeriaFirebase.class);	

	
	public ResponseEntity<String> enviarMensajePush(String token) {
		logger.info("[ServicioMensajeriaFirebase][enviarMensajePush] Inicio, token: " + token);
		ArrayList<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
		RestTemplate restTemplate = new RestTemplate();
		interceptors.add(new HeaderRequestInterceptor("Authorization", "key=" + FIREBASE_SERVER_KEY));
		interceptors.add(new HeaderRequestInterceptor("Content-Type", "application/json"));
		restTemplate.setInterceptors(interceptors);

		JSONObject body = new JSONObject();
		body.put("to", token);
		body.put("priority", "high");

		JSONObject notification = new JSONObject();
		notification.put("title", "Alerta de robo de vehiculo");
		notification.put("body", "Hemos detectado que tu vehiculo estacionado esta en movimiento, favor revisa");
		notification.put("sound", "default");
		notification.put("icon", "fcm_push_icon");

		JSONObject data = new JSONObject();
		data.put("key1", "value1");
		data.put("key2", "value2");

		body.put("notification", notification);
		body.put("data", data);

		HttpEntity<String> request = new HttpEntity<>(body.toString());

		FirebaseResponse firebaseResponse = restTemplate.postForObject(FIREBASE_API_URL, request,
				FirebaseResponse.class);
		
		logger.info("[ServicioMensajeriaFirebase][enviarMensajePush] Fin : firebaseResponse " + firebaseResponse.toString());

		return new ResponseEntity<>(firebaseResponse.toString(), HttpStatus.OK);
	}

}
